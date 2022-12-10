package odo.structure.analysis.section;

import odo.structure.column.Column;
import odo.structure.column.material.Concrete;
import odo.structure.column.material.Steel;
import odo.structure.column.pmcurve.*;
import odo.structure.column.reinforcement.Rebar;

import java.util.ArrayList;
import java.util.List;

public class Biaxial {

    private static final int n = 10000;
    private final Column column;

    public Biaxial(Column column) {
        this.column = column;
    }

    public List<Point3D> PMCurve(double theta) {
        List<Point3D> points3D = new ArrayList<>();
        double furthestCompressionZoneDepth = furthestCompressionZoneDepth(theta);

        for (int c = 1; c < furthestCompressionZoneDepth; c++) {
            NeutralAxis neutralAxis = new NeutralAxis(c, theta);
            neutralAxis.setSection(column.getSection());

            double Pn = axialStrength(neutralAxis, c, theta);
        }

        return null;
    }

    private double furthestCompressionZoneDepth(double theta) {
        NeutralAxis beginLine = new NeutralAxis(0, theta);

        List<Integer> pair = Quadrant.getPair(theta);
        double xOfEndPoint = -pair.get(0) * column.getSection().b;
        double yOfEndPoint = -pair.get(1) * column.getSection().h;

        return beginLine.distanceToPoint(xOfEndPoint, yOfEndPoint);
    }

    private double axialStrength(NeutralAxis neutralAxis, double c, double theta) {
        double Pn = ConcreteForce.calculate(column.getConcrete(), c, theta, n);

        for (Rebar rebar : column.getRebars()) {
            Pn += steelForce(rebar, neutralAxis, c, theta);
        }

        return Pn;
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
        double xOfBeginPoint = pair.get(0) * column.getSection().b;
        double yOfBeginPoint = pair.get(1) * column.getSection().h;

        boolean signOfCompression = neutralAxis.isNegativeValue(xOfBeginPoint, yOfBeginPoint);
        boolean signOfSteel = neutralAxis.isNegativeValue(rebar.x, rebar.y);

        return signOfCompression == signOfSteel;
    }

}
