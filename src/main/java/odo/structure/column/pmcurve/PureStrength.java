package odo.structure.column.pmcurve;

import odo.structure.column.material.Concrete;
import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;

import java.util.List;

public class PureStrength {

    public static double compression(Rectangle section, Concrete concrete, List<Rebar> rebars) {
        double Pn = 0;
        double Ast = 0;

        for (Rebar rebar : rebars) {
            Pn += rebar.fy * rebar.As;
            Ast += rebar.As;
        }

        Pn += 0.85 * concrete.fck * (section.b * section.h - Ast);
        return Pn;
    }

    public static double tension(List<Rebar> rebars) {
        double Pn = 0;

        for (Rebar rebar : rebars) {
            Pn -= rebar.fy * rebar.As;
        }

        return Pn;
    }

}
