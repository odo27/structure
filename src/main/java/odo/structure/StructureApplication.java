package odo.structure;

import odo.structure.analysis.section.BiAxial;
import odo.structure.column.Column;
import odo.structure.column.material.Concrete;
import odo.structure.column.pmcurve.Point3D;
import odo.structure.column.pmcurve.PureStrength;
import odo.structure.column.pmcurve.Quadrant;
import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;
import odo.structure.view.WriteCSV;

import java.util.ArrayList;
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

		double pureCompression = PureStrength.compression(section, concrete, rebars);
		double pureTension = PureStrength.tension(rebars);

		Column column = new Column(section, concrete, rebars);
		BiAxial biAxial = new BiAxial(column);

		List<List<Point3D>> analysisResult = new ArrayList<>();
		for (int theta = 1; theta < 360; theta++) {
			try {
				Quadrant.validateTheta(theta);
				System.out.println(theta);
				List<Point3D> points3D = biAxial.PMCurve(theta);

				points3D.add(new Point3D(pureCompression, 0, 0));
				points3D.add(new Point3D(pureTension, 0, 0));

				analysisResult.add(points3D);
			} catch (IllegalArgumentException ignored) {
			}
		}

		WriteCSV.writeBiAxialAnalysisResult(analysisResult);
	}
}
