package odo.structure.column.pmcurve;

import odo.structure.column.material.Concrete;

public class ConcreteForce {

    private static final double eco = 0.002;

    public static double calculate(Concrete concrete, double compressiveZoneDepth, double theta, double n) {
        double delta = compressiveZoneDepth / n;
        double Cc = 0;
        for (int k = 0; k < n; k++) {
            double y = k * delta;
            double ec = strainOfConcrete(compressiveZoneDepth, y);
            double sc = stressOfConcrete(concrete.fck, ec);
            NeutralAxis neutralAxis = new NeutralAxis(y, theta);
            Cc += neutralAxis.distanceOfTwoCrossingPoint() * delta * sc;
        }
        return Cc;
    }

    private static double strainOfConcrete(double c, double y) {
        return Curvature.calculate(c) * y;
    }

    private static double stressOfConcrete(double fck, double ec) {
        return fck * (2 * ec / eco - (ec / eco) * (ec / eco));
    }
}
