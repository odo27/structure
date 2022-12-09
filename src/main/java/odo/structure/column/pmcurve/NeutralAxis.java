package odo.structure.column.pmcurve;

public class NeutralAxis {

    private final double c;
    private final double theta;

    public NeutralAxis(double c, double theta) {
        this.c = c;
        this.theta = theta;
    }

    public double distanceOfTwoCrossingPoint() {
        double thetaToRadians = Math.toRadians(theta);
        return c * (Math.tan(thetaToRadians) + 1 / Math.tan(thetaToRadians));
    }
}
