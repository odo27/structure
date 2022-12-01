package odo.structure.column.pmcurve;

import odo.structure.column.material.Concrete;
import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;

import java.util.List;

public class AxialStrength {

    public static double calculate(Rectangle section, Concrete concrete, List<Rebar> rebars, double compressionZoneDepth) {
        double Pn = concreteForce(section, concrete, compressionZoneDepth);

        for (Rebar rebar : rebars) {
            Pn -= steelForce(rebar, compressionZoneDepth);
        }

        return Pn;
    }

    private static double concreteForce(Rectangle section, Concrete concrete, double c) {
        double a = concrete.b1 * c;
        return 0.85 * concrete.fck * a * section.b;
    }

    private static double steelForce(Rebar rebar, double c) {
        return rebar.As * Stress.calculate(rebar, c);
    }

}
