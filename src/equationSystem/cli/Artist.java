package equationSystem.cli;

import equationSystem.system.EquationSystem;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Artist {
    PrintWriter writer;

    public Artist(OutputStream os) {
        writer = new PrintWriter(os);
    }

    public void drawSystem(EquationSystem system) {
        double[][] a = system.getA();
        writer.println("Введённая система:");
        for (int i = 0; i < system.getSize(); i++) {
            for (int j = 0; j <= system.getSize(); j++) {
                if (j == system.getSize()) {
                    writer.print(" = " + system.getB()[i]);
                } else {
                    if (j != 0) {
                        writer.print(" + ");
                    }
                    writer.print(a[i][j] + " * x" + (j + 1));
                }
            }
            writer.print("\n");
        }
        writer.flush();
    }

    public void drawTableHeader(int numberX) {
        writer.print("X\t\t\t\t\t|");
        writer.print("\t\tDifference\n");
        writer.print("----------------------------------------------------\n");
        writer.flush();
    }

    public void drawTableLine(int iteration, double[] x, double accuracy) {
        writer.print("\t" + iteration + "\t\t|");
        for (double v : x) {
            int length = String.valueOf(Math.abs(v)).length();
            if (length > 10) {
                writer.print("\t" + v + "\t|");
            } else {
                if (length > 7) {
                    writer.print("\t\t" + v + "\t\t|");
                } else {
                    writer.print("\t\t" + v + "\t\t\t|");
                }
            }
        }
        writer.print("\t" + accuracy + "\n");
        writer.flush();
    }

    public void drawTableLine(double x, double dif) {
        writer.print(x + "\t\t" + dif +"\n");
        writer.flush();
    }

}
