package equationSystem.checkers;

import equationSystem.system.EquationSystem;

public class DeterminantCalculator {

    EquationSystem equationSystem;

    public DeterminantCalculator(EquationSystem equationSystem) {
        this.equationSystem = equationSystem;
    }

    public double calculateDeterminant() {
        double[][] a = equationSystem.getA();
        double det = 0;
        int row = 0;
        for (int i = 0; i < a.length; i++) {
            det += Math.pow(-1, row + i) * a[row][i] * calculateMinor(row, i, a);

        }
        return det;
    }

    private double calculateMinor(int row, int column, double[][] matrix) {
        double minor = 0;
        double[][] a = cutMatrix(row, column, matrix);
        if (a.length != 2) {
            int minorRow = 0;
            for (int i = 0; i < a.length; i++) {
                System.out.println(a.length + "\t" + i);
                minor += Math.pow(-1, minorRow + i) * a[minorRow][i] * calculateMinor(minorRow, i, a);
            }
            if (a.length == 20) {
                System.out.println(10);
            }
        } else {
            minor = a[0][0] * a[1][1] - a[0][1] * a[1][0];
        }
        return minor;
    }

    private double[][] cutMatrix(int row, int column, double[][] matrix) {
        int newSize = matrix.length - 1;
        double[][] newMatrix = new double[newSize][newSize];
        int matrixI = 0;
        int matrixJ = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i != row) {
                for (int j = 0; j < matrix.length; j++) {
                    if (j != column) {
                        newMatrix[matrixI][matrixJ] = matrix[i][j];
                        matrixJ++;
                    }
                }
                matrixJ = 0;
                matrixI++;
            }
        }
        return newMatrix;
    }

}
