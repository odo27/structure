package odo.structure.column;

import odo.structure.column.material.Concrete;
import odo.structure.column.pmcurve.*;
import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Column {

    private final Rectangle section;
    private final Concrete concrete;
    private final List<Rebar> rebars;

    public Column(Rectangle section, Concrete concrete, List<Rebar> rebars) {
        this.section = section;
        this.concrete = concrete;
        this.rebars = rebars;
    }

    public List<Point> PMCurve() {
        List<Point> points = new ArrayList<>();

        for (int c = 1; c < section.h; c++) {
            double Mn = FlexuralStrength.calculate(section, concrete, rebars, c);
            double Pn = AxialStrength.calculate(section, concrete, rebars, c);
            points.add(new Point(Mn, Pn));
        }

        return points;
    }

    public List<Point> reducedPMCurve() {
        List<Point> points = new ArrayList<>();
        Rebar furthestRebar = FurthestRebar.calculate(rebars);
        double distanceFromTopOfFurthestRebar = section.h / 2 - furthestRebar.y;

        for (int c = 1; c < section.h; c++) {
            double furthestRebarStrain = Strain.calculate(distanceFromTopOfFurthestRebar, c);
            double phi = Phi.calculate(furthestRebarStrain, furthestRebar.fy);

            double Mn = FlexuralStrength.calculate(section, concrete, rebars, c);
            double Pn = AxialStrength.calculate(section, concrete, rebars, c);
            points.add(new Point(phi * Mn, phi * Pn));
        }

        return points;
    }

}
