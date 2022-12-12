package odo.structure.view;

import odo.structure.column.pmcurve.Point3D;

import java.io.*;
import java.util.List;

public class WriteCSV {

    public static void writeBiAxialThetaAnalysisResult(List<Point3D> points3D) {
        File resultCSV = new File("C:/Users/user/Desktop/resultTheta.csv");
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(resultCSV));

            int c = -1;
            for (Point3D point3D : points3D) {
                String line = String.format("%d, ", c);
                line += String.format("%.2f, ", point3D.x / 1e3);
                line += String.format("%.2f, ", point3D.y / 1e6);
                line += String.format("%.2f ", point3D.z / 1e6);
                bw.write(line);
                bw.newLine();
                c++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.flush();
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeBiAxialAnalysisResult(List<List<Point3D>> analysisResult) {
        File resultCSV = new File("C:/Users/user/Desktop/result.csv");
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(resultCSV));

            for (List<Point3D> points3D : analysisResult) {
                int c = 1;
                for (Point3D point3D : points3D) {
                    String line = String.format("%d, ", c);
                    line += String.format("%.2f, ", point3D.x / 1e3);
                    line += String.format("%.2f, ", point3D.y / 1e6);
                    line += String.format("%.2f", point3D.z / 1e6);
                    bw.write(line);
                    bw.newLine();
                    c++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.flush();
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
