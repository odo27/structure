package odo.structure.column.material;

public class Concrete {

    public static final double ecu = 0.003;
    public final double fck;
    public final double b1;

    public Concrete(double compressiveStrength) {
        fck = compressiveStrength;
        b1 = calculateBetaOne(fck);
    }

    private double calculateBetaOne(double fck) {
        if (fck <= 28) {
            return 0.85;
        }

        if (fck <= 56) {
            return 0.85 - 0.007 * (fck - 28);
        }

        return 0.65;
    }

}
