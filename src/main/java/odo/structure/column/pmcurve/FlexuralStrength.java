package odo.structure.column.pmcurve;

import odo.structure.column.material.Concrete;
import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;

import java.util.List;

public class FlexuralStrength {

    public static double calculate(Rectangle section, Concrete concrete, List<Rebar> rebars, double compressionZoneDepth) {
        double a = concrete.b1 * compressionZoneDepth;
        double Pn = AxialStrength.calculate(section, concrete, rebars, compressionZoneDepth);
        double Mn = Pn * (section.h / 2 - compressionZoneDepth);
        Mn += AxialStrength.concreteForce(section, concrete, compressionZoneDepth) * (compressionZoneDepth - a / 2);

        for (Rebar rebar : rebars) {
            double d = section.h / 2 - rebar.y;
            Mn += AxialStrength.steelForce(section, rebar, compressionZoneDepth) * (d - compressionZoneDepth);
        }

        return Mn;
    }

}
