package odo.structure.column.pmcurve;

import odo.structure.column.material.Steel;

public class Phi {

    private static final double COMPRESSION_DOMINANT_SECTION = 0.65;
    private static final double TENSION_DOMINANT_SECTION = 0.85;

    public static double calculate(double strain, double tensileStrength) {
        double yieldStrain = tensileStrength / Steel.Es;
        double minimumStrainForTensionDominant = Math.max(0.005, 2.5 * yieldStrain);

        if (strain <= yieldStrain) {
            return COMPRESSION_DOMINANT_SECTION;
        }

        if (strain <= minimumStrainForTensionDominant) {
            return transitionSection(strain, yieldStrain, minimumStrainForTensionDominant);
        }

        return TENSION_DOMINANT_SECTION;
    }

    private static double transitionSection(double strain, double yieldStrain, double minimumStrainForTensionDominant) {
        double slope = (TENSION_DOMINANT_SECTION - COMPRESSION_DOMINANT_SECTION) / (minimumStrainForTensionDominant - yieldStrain);
        double deltaX = strain - yieldStrain;
        return COMPRESSION_DOMINANT_SECTION + slope * deltaX;
    }

}
