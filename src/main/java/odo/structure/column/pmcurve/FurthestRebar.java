package odo.structure.column.pmcurve;

import odo.structure.column.reinforcement.Rebar;

import java.util.List;

public class FurthestRebar {

    public static Rebar calculate(List<Rebar> rebars) {
        Rebar furthestRebar = rebars.get(0);

        for (Rebar rebar : rebars) {
            if (rebar.d > furthestRebar.d) {
                furthestRebar = rebar;
            }
        }

        return furthestRebar;
    }

}
