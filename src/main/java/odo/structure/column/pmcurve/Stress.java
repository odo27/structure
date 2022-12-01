package odo.structure.column.pmcurve;

import odo.structure.column.reinforcement.Rebar;

public class Stress {

    public static double calculate(Rebar rebar, double compressiveZoneDepth) {
        double fs = Rebar.Es * Strain.calculate(rebar.d, compressiveZoneDepth);
        return rebar.restrictStress(fs);
    }

}
