package odo.structure.column;

import odo.structure.column.material.Concrete;
import odo.structure.column.pmcurve.AxialStrength;
import odo.structure.column.pmcurve.FlexuralStrength;
import odo.structure.column.pmcurve.Point;
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

}
