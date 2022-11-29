package odo.structure.column;

import odo.structure.column.material.Concrete;
import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;

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

}
