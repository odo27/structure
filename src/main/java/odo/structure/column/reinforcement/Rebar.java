package odo.structure.column.reinforcement;

import odo.structure.column.material.Steel;

public class Rebar extends Steel {

    public final double As;
    public final double x;
    public final double y;

    public Rebar(double tensileStrength, double area, double xCoordinate, double yCoordinate) {
        super(tensileStrength);
        As = area;
        x = xCoordinate;
        y = yCoordinate;
    }

}
