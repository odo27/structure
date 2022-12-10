package odo.structure.column.pmcurve;

import odo.structure.column.section.Rectangle;

import java.util.List;

public class NeutralAxis {

    private final double c;
    private final double theta;

    private Rectangle section;
    private LinearEquation linearEquation;

    public NeutralAxis(double c, double theta) {
        this.c = c;
        this.theta = theta;
    }

    public double distanceOfTwoCrossingPoint() {
        double thetaToRadians = Math.toRadians(theta);
        return c * (Math.tan(thetaToRadians) + 1 / Math.tan(thetaToRadians));
    }

    public double distanceToPoint(double x, double y) {
        double numerator = Math.abs(linearEquation.cx * x + linearEquation.cy * y + linearEquation.c);
        double denominator = Math.sqrt(Math.pow(linearEquation.cx, 2) + Math.pow(linearEquation.cy, 2));

        return numerator / denominator;
    }

    public void setSection(Rectangle section) {
        this.section = section;
        setLinearEquation();
    }

    private void setLinearEquation() {
        double thetaToRadians = Math.toRadians(theta);
        List<Integer> pair = Quadrant.getPair(theta);

        linearEquation = new LinearEquation(
                Math.tan(thetaToRadians),
                -1,
                -pair.get(0) * section.b / 2 * Math.tan(thetaToRadians) +
                        c * Math.sin(thetaToRadians) * Math.tan(thetaToRadians) +
                        pair.get(1) * section.h / 2 +
                        c * Math.cos(thetaToRadians)
        );
    }
}
