package odo.structure.column.pmcurve;

import java.util.List;

public class Quadrant {

    private static final int FIRST = 90;
    private static final int SECOND = 180;
    private static final int THIRD = 270;
    private static final int FOURTH = 360;

    private static final List<List<Integer>> PAIRS = List.of(
            List.of(1, 1),
            List.of(-1, 1),
            List.of(-1, -1),
            List.of(1, -1)
    );

    public static List<Integer> getPair(double theta) {
        validateTheta(theta);

        if (theta < FIRST) {
            return PAIRS.get(3);
        }

        if (theta < SECOND) {
            return PAIRS.get(0);
        }

        if (theta < THIRD) {
            return PAIRS.get(1);
        }

        return PAIRS.get(2);
    }

    public static void validateTheta(double theta) {
        if (theta == FIRST || theta == SECOND || theta == THIRD) {
            throw new IllegalArgumentException();
        }

        if (theta <= 0 || theta >= FOURTH) {
            throw new IllegalArgumentException();
        }
    }

}
