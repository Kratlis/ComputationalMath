package approximation.function;

import static java.lang.Math.pow;

public class SquareApproximation {
    private final double a;
    private final double b;
    private final double c;
    private final double[] x;
    private final double[] y;
    private final double[][] matrix = new double[3][4];

    public SquareApproximation(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        fillMatrix();
        transformMatrix();
        c = countC();
        b = countB();
        a = countA();
        System.out.println("Square: a = " + a + ", b = " + b + ", c = " + c);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    private void fillMatrix() {
        int n = x.length;
        matrix[0][0] = sumX4();
        matrix[0][1] = sumX3();
        matrix[0][2] = sumX2();
        matrix[0][3] = sumX2Y();
        matrix[1][0] = sumX3();
        matrix[1][1] = sumX2();
        matrix[1][2] = sumX();
        matrix[1][3] = sumXY();
        matrix[2][0] = sumX2();
        matrix[2][1] = sumX();
        matrix[2][2] = n;
        matrix[2][3] = sumY();
    }

    private void transformMatrix() {
        double[][] oldMatrix = new double[3][4];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, oldMatrix[i], 0, matrix[i].length);
        }
        for (int k = 0; k < 3; k++) {
            for (int i = 1 + k; i < 3; i++) {
                for (int j = k; j < 3; j++) {
                    matrix[i][j] = matrix[i][j] - oldMatrix[i][k] / matrix[k][k] * matrix[k][j];
                }
                matrix[i][3] = matrix[i][3] - oldMatrix[i][k] * matrix[k][3] / matrix[k][k];
                System.arraycopy(matrix[i], 0, oldMatrix[i], 0, matrix[i].length);
            }
        }
    }

    private double countC() {
        return matrix[2][3] / matrix[2][2];
    }

    private double countB() {
        return (matrix[1][3] - c * matrix[1][2]) / matrix[1][1];
    }

    private double countA() {
        return (matrix[0][3] - b * matrix[0][1] - c * matrix[0][2]) / matrix[0][0];
    }

    private double sumXY() {
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i] * y[i];
        }
        return sum;
    }

    private double sumX2Y() {
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += Math.pow(x[i], 2) * y[i];
        }
        return sum;
    }

    private double sumY() {
        double sum = 0;
        for (double v : y) {
            sum += v;
        }
        return sum;
    }

    private double sumX() {
        double sum = 0;
        for (double v : x) {
            sum += v;
        }
        return sum;
    }

    private double sumX2() {
        double sum = 0;
        for (double v : x) {
            sum += pow(v, 2);
        }
        return sum;
    }

    private double sumX3() {
        double sum = 0;
        for (double v : x) {
            sum += pow(v, 3);
        }
        return sum;
    }

    private double sumX4() {
        double sum = 0;
        for (double v : x) {
            sum += pow(v, 4);
        }
        return sum;
    }
}
