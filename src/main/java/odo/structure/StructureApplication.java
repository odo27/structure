package odo.structure;

import odo.structure.analysis.section.BiAxial;
import odo.structure.column.Column;
import odo.structure.column.material.Concrete;
import odo.structure.column.pmcurve.Point3D;
import odo.structure.column.pmcurve.Quadrant;
import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;
import odo.structure.view.WriteCSV;

import java.util.ArrayList;
import java.util.List;

public class StructureApplication {

	public static void main(String[] args) {
		biAxialThetaAnalysis();
	}

	private static void biAxialThetaAnalysis() {
		Rectangle section = new Rectangle(550, 550);
		Concrete concrete = new Concrete(27);

		double fy = 400;
		double As = 506;
		double space = 60;
		List<Rebar> rebars = generateRebars(fy, As, space, 3);

		Column column = new Column(section, concrete, rebars);
		BiAxial biAxial = new BiAxial(column);

		List<Point3D> points3D = biAxial.reducedPMCurve(210.46);

		WriteCSV.writeBiAxialThetaAnalysisResult(points3D);
	}

	private static List<Rebar> generateRebars(double fy, double As, double space, int n) {
		double currentX = space * n;
		double currentY = space * n;

		List<Integer> directionX = List.of(0, -1, 0, 1);
		List<Integer> directionY = List.of(-1, 0, 1, 0);

		List<Rebar> rebars = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2 * n; j++) {
				currentX += directionX.get(i) * space;
				currentY += directionY.get(i) * space;
				rebars.add(new Rebar(fy, As, currentX, currentY));
			}
		}

		return rebars;
	}

	private static void biAxialAnalysis() {
		Rectangle section = new Rectangle(400, 400);
		Concrete concrete = new Concrete(21);
		List<Rebar> rebars = List.of(
				new Rebar(400, 506, -150, 150),
				new Rebar(400, 506, 150, -150)
		);

		Column column = new Column(section, concrete, rebars);
		BiAxial biAxial = new BiAxial(column);

		List<List<Point3D>> analysisResult = new ArrayList<>();
		for (int theta = 1; theta < 360; theta++) {
			try {
				Quadrant.validateTheta(theta);
				System.out.println(theta);
				List<Point3D> points3D = biAxial.PMCurve(theta);

				analysisResult.add(points3D);
			} catch (IllegalArgumentException ignored) {
			}
		}

		WriteCSV.writeBiAxialAnalysisResult(analysisResult);
	}
}
