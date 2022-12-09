package odo.structure.column.pmcurve;

import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;

public class Stress {

    public static double calculate(Rectangle section, Rebar rebar, double compressiveZoneDepth) {
        double d = section.h / 2 - rebar.y;
        double fs = Rebar.Es * Strain.calculate(d, compressiveZoneDepth);
        return rebar.restrictStress(fs);
    }

}
