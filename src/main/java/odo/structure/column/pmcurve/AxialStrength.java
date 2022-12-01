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

    static double concreteForce(Rectangle section, Concrete concrete, double compressionZoneDepth) {
        double a = concrete.b1 * compressionZoneDepth;
        return 0.85 * concrete.fck * a * section.b;
    }

    static double steelForce(Rebar rebar, double compressionZoneDepth) {
        return rebar.As * Stress.calculate(rebar, compressionZoneDepth);
    }

}
