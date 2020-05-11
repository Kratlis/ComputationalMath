package approximation.function;

import static java.lang.Math.pow;

public class LogarithmApproximation {
    private final double a;
    private final double b;
    private final double[] x;
    private final double[] y;

    public LogarithmApproximation(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        int n = x.length;
        a = (n * sumXY() - sumX() * sumY()) / (n * sumSquareX() - pow(sumX(), 2));
        b = (sumY() - a * sumX()) / n;
        System.out.println("Log: a = " + a + ", b = " + b);
    }


    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    private double sumXY() {
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += Math.log(x[i]) * y[i];
        }
        return sum;
    }

    private double sumX() {
        double sum = 0;
        for (double v : x) {
            sum += Math.log(v);
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

    private double sumSquareX() {
        double sum = 0;
        for (double v : x) {
            sum += pow(Math.log(v), 2);
        }
        return sum;
    }
}
