package odo.structure.column.pmcurve;

import odo.structure.column.material.Concrete;
import odo.structure.column.section.Rectangle;

import java.util.List;

public class ConcreteForce {

    private static final double eco = 0.002;

    public static double calculate(Concrete concrete, Rectangle section, double compressiveZoneDepth, double theta, double n) {
        double delta = compressiveZoneDepth / n;
        double Cc = 0;
        for (int k = 1; k < n; k++) {
            double y = k * delta;
            double ec = strainOfConcrete(compressiveZoneDepth, compressiveZoneDepth - y);
            double sc = stressOfConcrete(concrete.fck, ec);
            NeutralAxis neutralAxis = new NeutralAxis(y, theta, section);
            Cc += neutralAxis.distanceOfTwoCrossingPoint() * delta * sc;
        }
        return Cc;
    }

    public static Point centroid(Rectangle section, double compressiveZoneDepth, double theta, double n) {
        double delta = compressiveZoneDepth / n;
        double thetaToRadians = Math.toRadians(theta);

        double area = 0;
        double Qx = 0;
        double Qy = 0;

        for (int k = 1; k < n; k++) {
            double y = k * delta;
            NeutralAxis neutralAxis = new NeutralAxis(y, theta, section);
            List<Point> crossingPoints = neutralAxis.solve();

            Point centralPoint = new Point(
                    (crossingPoints.get(0).x + crossingPoints.get(1).x) / 2 - delta * Math.sin(thetaToRadians),
                    (crossingPoints.get(0).y + crossingPoints.get(1).y) / 2 + delta * Math.cos(thetaToRadians)
            );

            double dArea = neutralAxis.distanceOfTwoCrossingPoint() * delta;
            area += dArea;
            Qx += dArea * centralPoint.x;
            Qy += dArea * centralPoint.y;
        }

        double centroidX = Qx / area;
        double centroidY = Qy / area;

        return new Point(centroidX, centroidY);
    }

    private static double strainOfConcrete(double c, double y) {
        return Curvature.calculate(c) * y;
    }

    private static double stressOfConcrete(double fck, double ec) {
        return fck * (2 * ec / eco - (ec / eco) * (ec / eco));
    }
}
