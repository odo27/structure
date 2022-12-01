package odo.structure.column.pmcurve;

public class Strain {

    public static double calculate(double distanceFromTop, double compressionZoneDepth) {
        return Curvature.calculate(compressionZoneDepth) * (distanceFromTop - compressionZoneDepth);
    }

}
