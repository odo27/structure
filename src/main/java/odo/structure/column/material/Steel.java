package odo.structure.column.material;

public class Steel {

    public static final double Es = 200_000;
    public final double fy;

    public Steel(double tensileStrength) {
        fy = tensileStrength;
    }

    public double restrictStress(double stress) {
        if (stress > fy) {
            return fy;
        }

        return Math.max(stress, -fy);
    }

}
