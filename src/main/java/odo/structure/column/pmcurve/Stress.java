package odo.structure.column.pmcurve;

import odo.structure.column.material.Concrete;
import odo.structure.column.reinforcement.Rebar;

public class Stress {

    private static double c;

    public static double calculate(Rebar rebar, double compressiveZoneDepth) {
        c = compressiveZoneDepth;
        double fs = Rebar.Es * strain(rebar.d);
        return rebar.restrictStress(fs);
    }

    private static double strain(double d) {
        return curvature() * (d - c);
    }

    private static double curvature() {
        return Concrete.ecu / c;
    }

}
