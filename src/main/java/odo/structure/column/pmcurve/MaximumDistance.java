package odo.structure.column.pmcurve;

import odo.structure.column.reinforcement.Rebar;

import java.util.List;

public class MaximumDistance {

    public static double calculate(List<Rebar> rebars) {
        double maximumDistance = Double.MIN_VALUE;

        for (Rebar rebar : rebars) {
            if (rebar.d > maximumDistance) {
                maximumDistance = rebar.d;
            }
        }

        return maximumDistance;
    }

}
