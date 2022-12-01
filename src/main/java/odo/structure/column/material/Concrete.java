package odo.structure.column.material;

public class Concrete {

    public static final double ecu = 0.003;
    public final double fck;

    public Concrete(double compressiveStrength) {
        fck = compressiveStrength;
    }

}
