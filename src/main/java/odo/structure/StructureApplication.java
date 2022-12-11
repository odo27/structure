package odo.structure;

import odo.structure.analysis.section.BiAxial;
import odo.structure.column.Column;
import odo.structure.column.material.Concrete;
import odo.structure.column.pmcurve.Point3D;
import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;

import java.util.List;

public class StructureApplication {

	public static void main(String[] args) {
		BiAxialAnalysis();
	}

	private static void BiAxialAnalysis() {
		Rectangle section = new Rectangle(400, 400);
		Concrete concrete = new Concrete(21);
		List<Rebar> rebars = List.of(
				new Rebar(400, 506, -150, 150),
				new Rebar(400, 506, 150, -150)
		);
		Column column = new Column(section, concrete, rebars);

		BiAxial biAxial = new BiAxial(column);
		List<Point3D> points3D = biAxial.PMCurve(59.71204);
		int c = 1;
		for (Point3D point3D : points3D) {
			System.out.printf("%d, ", c);
			System.out.printf("%.2f, ", point3D.x / 1e3);
			System.out.printf("%.2f, ", point3D.y / 1e6);
			System.out.printf("%.2f%n", point3D.z / 1e6);
			c++;
		}
	}
}
