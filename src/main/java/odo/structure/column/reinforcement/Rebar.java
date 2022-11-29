package odo.structure.column.reinforcement;

import odo.structure.column.material.Steel;

public class Rebar extends Steel {

    public final double A;
    public final double d;

    public Rebar(double tensileStrength, double area, double distanceFromTop) {
        super(tensileStrength);
        A = area;
        d = distanceFromTop;
    }

}
