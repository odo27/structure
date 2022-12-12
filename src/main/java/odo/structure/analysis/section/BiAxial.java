package odo.structure.analysis.section;

import odo.structure.column.Column;
import odo.structure.column.material.Concrete;
import odo.structure.column.material.Steel;
import odo.structure.column.pmcurve.*;
import odo.structure.column.reinforcement.Rebar;

import java.util.ArrayList;
import java.util.List;

public class BiAxial {

    private static final int n = 10000;
    private final Column column;

    public BiAxial(Column column) {
        this.column = column;
    }

    public List<Point3D> PMCurve(double theta) {
        List<Point3D> points3D = new ArrayList<>();
        double furthestCompressionZoneDepth = furthestCompressionZoneDepth(theta);

        for (int c = 1; c < furthestCompressionZoneDepth; c++) {
            NeutralAxis neutralAxis = new NeutralAxis(c, theta, column.getSection());

            double Pn = axialStrength(neutralAxis, c, theta);
            Point Mn = flexuralStrength(neutralAxis, c, theta);
            double Mnx = Mn.x;
            double Mny = Mn.y;

            Point3D point3D = new Point3D(Pn, Mnx, Mny);
            points3D.add(point3D);
        }

        return points3D;
    }

    public List<Point3D> reducedPMCurve(double theta) {
        List<Point3D> points3D = new ArrayList<>();
        double furthestCompressionZoneDepth = furthestCompressionZoneDepth(theta);

        for (int c = 1; c < furthestCompressionZoneDepth; c++) {
            NeutralAxis neutralAxis = new NeutralAxis(c, theta, column.getSection());

            Rebar furthestRebar = neutralAxis.furthestRebar(column.getRebars());
            double phi = phi(neutralAxis, furthestRebar, c);

            double Pn = phi * axialStrength(neutralAxis, c, theta);
            Point Mn = flexuralStrength(neutralAxis, c, theta);
            double Mnx = phi * Mn.x;
            double Mny = phi * Mn.y;

            Point3D point3D = new Point3D(Pn, Mnx, Mny);
            points3D.add(point3D);
        }

        return points3D;
    }

    private double phi(NeutralAxis neutralAxis, Rebar furthestRebar, double c) {
        if (furthestRebar == null) {
            return Phi.COMPRESSION_DOMINANT_SECTION;
        }

        double distanceToFurthestRebar = neutralAxis.distanceToPoint(furthestRebar.x, furthestRebar.y);
        double strainOfFurthestRebar = Curvature.calculate(c) * distanceToFurthestRebar;

        return Phi.calculate(strainOfFurthestRebar, furthestRebar.fy);
    }

    private double furthestCompressionZoneDepth(double theta) {
        NeutralAxis beginLine = new NeutralAxis(0, theta, column.getSection());

        List<Integer> pair = Quadrant.getPair(theta);
        double xOfEndPoint = -pair.get(0) * column.getSection().b / 2;
        double yOfEndPoint = -pair.get(1) * column.getSection().h / 2;

        return beginLine.distanceToPoint(xOfEndPoint, yOfEndPoint);
    }

    private double axialStrength(NeutralAxis neutralAxis, double c, double theta) {
        double Pn = ConcreteForce.calculate(column.getConcrete(), column.getSection(), c, theta, n);

        for (Rebar rebar : column.getRebars()) {
            Pn += steelForce(rebar, neutralAxis, c, theta);
        }

        return Pn;
    }

    private Point flexuralStrength(NeutralAxis neutralAxis, double c, double theta) {
        NeutralAxis beginLine = new NeutralAxis(0, theta, column.getSection());
        double distanceToCentralPointFromBeginLine = beginLine.distanceToPoint(0, 0);
        double Mn = axialStrength(neutralAxis, c, theta) * (distanceToCentralPointFromBeginLine - c);

        double Cc = ConcreteForce.calculate(column.getConcrete(), column.getSection(), c, theta, n);
        Point centroid = ConcreteForce.centroid(column.getSection(), c, theta, n);
        Mn += Cc * neutralAxis.distanceToPoint(centroid.x, centroid.y);

        for (Rebar rebar : column.getRebars()) {
            double steelForce = steelForce(rebar, neutralAxis, c, theta);
            Mn += Math.abs(steelForce) * neutralAxis.distanceToPoint(rebar.x, rebar.y);
        }

        double thetaToRadians = Math.toRadians(theta);
        double Mnx = Math.cos(thetaToRadians) * Mn;
        double Mny = Math.sin(thetaToRadians) * Mn;

        return new Point(Mnx, Mny);
    }

    private double steelForce(Rebar rebar, NeutralAxis neutralAxis, double c, double theta) {
        return rebar.As * steelStress(rebar, neutralAxis, c, theta);
    }

    private double steelStress(Rebar rebar, NeutralAxis neutralAxis, double c, double theta) {
        double distanceFromNeutralAxis = neutralAxis.distanceToPoint(rebar.x, rebar.y);
        double ruptureCurvature = Concrete.ecu / c;
        double sign = signOfSteelStress(rebar, neutralAxis, theta);

        double fs = sign * Steel.Es * distanceFromNeutralAxis * ruptureCurvature;
        return rebar.restrictStress(fs);
    }

    private int signOfSteelStress(Rebar rebar, NeutralAxis neutralAxis, double theta) {
        if (isInCompressionZone(rebar, neutralAxis, theta)) {
            return 1;
        }

        return -1;
    }

    private boolean isInCompressionZone(Rebar rebar, NeutralAxis neutralAxis, double theta) {
        List<Integer> pair = Quadrant.getPair(theta);
        double xOfBeginPoint = pair.get(0) * column.getSection().b / 2;
        double yOfBeginPoint = pair.get(1) * column.getSection().h / 2;

        boolean signOfCompression = neutralAxis.isNegativeValue(xOfBeginPoint, yOfBeginPoint);
        boolean signOfSteel = neutralAxis.isNegativeValue(rebar.x, rebar.y);

        return signOfCompression == signOfSteel;
    }

}
