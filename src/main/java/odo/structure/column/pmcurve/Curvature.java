package odo.structure.column.pmcurve;

import odo.structure.column.material.Concrete;

public class Curvature {

    public static double calculate(double compressionZoneDepth) {
        return Concrete.ecu / compressionZoneDepth;
    }

}
