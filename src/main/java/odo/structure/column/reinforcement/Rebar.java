package odo.structure.column.reinforcement;

import odo.structure.column.material.Steel;

public class Rebar extends Steel {

    public final double As;
    public final double d;

    public Rebar(double tensileStrength, double area, double distanceFromTop) {
        super(tensileStrength);
        As = area;
        d = distanceFromTop;
    }

}
