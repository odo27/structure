package odo.structure.column.pmcurve;

import odo.structure.column.section.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class NeutralAxis {

    private final double c;
    private final double theta;
    private final Rectangle section;
    private final LinearEquation linearEquation;

    public NeutralAxis(double c, double theta, Rectangle section) {
        this.c = c;
        this.theta = theta;
        this.section = section;
        linearEquation = setLinearEquation();
    }

    public double distanceOfTwoCrossingPoint() {
        List<Point> crossingPoints = solve();
        double deltaX = crossingPoints.get(0).x - crossingPoints.get(1).x;
        double deltaY = crossingPoints.get(0).y - crossingPoints.get(1).y;
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    public List<Point> solve() {
        List<Point> crossingPoints = new ArrayList<>();

        double maximumX = section.b / 2;
        double minimumX = -section.b / 2;
        double maximumY = section.h / 2;
        double minimumY = -section.h / 2;

        double topX = linearEquation.solveX(maximumY);
        double bottomX = linearEquation.solveX(minimumY);
        double leftY = linearEquation.solveY(minimumX);
        double rightY = linearEquation.solveY(maximumX);

        if (topX >= minimumX && topX <= maximumX) {
            crossingPoints.add(new Point(topX, maximumY));
        }

        if (bottomX >= minimumX && bottomX <= maximumX) {
            crossingPoints.add(new Point(bottomX, minimumY));
        }

        if (leftY >= minimumY && leftY <= maximumY) {
            crossingPoints.add(new Point(minimumX, leftY));
        }

        if (rightY >= minimumY && rightY <= maximumY) {
            crossingPoints.add(new Point(maximumX, rightY));
        }

        if (crossingPoints.size() != 2) {
            throw new IllegalArgumentException();
        }

        return crossingPoints;
    }

    public double distanceToPoint(double x, double y) {
        double numerator = Math.abs(linearEquation.cx * x + linearEquation.cy * y + linearEquation.c);
        double denominator = Math.sqrt(Math.pow(linearEquation.cx, 2) + Math.pow(linearEquation.cy, 2));

        return numerator / denominator;
    }

    public boolean isNegativeValue(double x, double y) {
        double value = linearEquation.cx * x + linearEquation.cy * y + linearEquation.c;
        return value < 0;
    }

    private LinearEquation setLinearEquation() {
        double thetaToRadians = Math.toRadians(theta);
        List<Integer> pair = Quadrant.getPair(theta);

        return new LinearEquation(
                Math.tan(thetaToRadians),
                -1,
                -pair.get(0) * section.b / 2 * Math.tan(thetaToRadians) +
                        c * Math.sin(thetaToRadians) * Math.tan(thetaToRadians) +
                        pair.get(1) * section.h / 2 +
                        c * Math.cos(thetaToRadians)
        );
    }
}
