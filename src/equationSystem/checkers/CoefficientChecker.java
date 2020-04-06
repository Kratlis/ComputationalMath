package equationSystem.checkers;

import equationSystem.system.EquationSystem;

public class CoefficientChecker {
    EquationSystem equationSystem;

    public CoefficientChecker(EquationSystem equationSystem) {
        this.equationSystem = equationSystem;
    }

    public boolean notNullDiagonal() {
        boolean res = true;
        for (int i = 0; i < equationSystem.getSize(); i++) {
            res &= (equationSystem.getA()[i][i] != 0);
        }
        return res;
    }

    public boolean isDiagonallyDominant() {
        double[][] coefficient = equationSystem.getA();
        boolean rightMatrix = true;
        boolean strict = false;
        for (int i = 0; i < coefficient.length; i++) {
            double otherCoefficients = findSumOtherCoefficients(i);
            if (Math.abs(coefficient[i][i]) > otherCoefficients) {
                strict = true;
            } else if (coefficient[i][i] < otherCoefficients) {
                rightMatrix = false;
                break;
            }
        }
        return rightMatrix && strict;
    }

    public int findRowOfMaxCoefficient(int column) {
        double[][] coefficients = equationSystem.getA();
        double max = coefficients[column][column];
        int row = column;
        for (int i = column; i < coefficients.length; i++) {
            if (coefficients[i][column] > max) {
                max = coefficients[i][column];
                row = i;
            }
        }
        return row;
    }

    public boolean checkRowMainCoefficient(int row) {
        double[][] coefficients = equationSystem.getA();
        double otherCoefficients = findSumOtherCoefficients(row);
        return Math.abs(coefficients[row][row]) >= otherCoefficients;
    }

    private double findSumOtherCoefficients(int row) {
        double[][] coefficients = equationSystem.getA();
        double otherCoefficients = 0;
        for (int column = 0; column < coefficients.length; column++) {
            if (row != column) {
                otherCoefficients += Math.abs(coefficients[row][column]);
            }
        }
        return otherCoefficients;
    }
}
