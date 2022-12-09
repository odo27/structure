package odo.structure.column.pmcurve;

public class LinearEquation {

    public final double cx;
    public final double cy;
    public final double c;

    public LinearEquation(double coefficientX, double coefficientY, double constant) {
        cx = coefficientX;
        cy = coefficientY;
        c = constant;
    }

}
