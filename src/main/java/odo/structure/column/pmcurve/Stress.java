package odo.structure.column.pmcurve;

import odo.structure.column.material.Concrete;
import odo.structure.column.reinforcement.Rebar;

public class Stress {

    public static double calculate(Rebar rebar, double compressiveZoneDepth) {
        double fs = Rebar.Es * strain(rebar.d, compressiveZoneDepth);
        return rebar.restrictStress(fs);
    }

    private static double strain(double d, double c) {
        return curvature(c) * (d - c);
    }

    private static double curvature(double c) {
        return Concrete.ecu / c;
    }

}
