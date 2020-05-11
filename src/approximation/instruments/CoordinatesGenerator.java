package approximation.instruments;

import approximation.function.ApproximationType;
import approximation.function.Function;

public class CoordinatesGenerator {
    Function function;
    double[] x;

    public CoordinatesGenerator(ApproximationType type) {
        this.function = new Function(type);
        new CoefficientGenerator(function).generateCoefficients();
    }

    public double[] generateX(int n) {
        x = new double[n];
        x[0] = Math.random() * 10 - 8;
        double dif = Math.random() * 3 - 1;
        for (int i = 1; i < n; i++) {
            x[i] = x[i - 1] + dif;
        }
        if (function.getType() == ApproximationType.LOGARITHM) {
            for (int i = 0; i < x.length; i++) {
                if (x[i] < 0) {
                    x[i] = Math.abs(x[i]);
                } else if (x[i] == 0) {
                    x[i] += Math.abs(dif);
                }
            }
        }
        return x;
    }

    public double[] generateY(int n) {
        double[] y = new double[n];
        int extra = Math.round((float) Math.random() * n);
        for (int i = 0; i < n; i++) {
            double dif;
            if (i != extra) {
                dif = Math.random() * 6 - 3;
            } else {
                dif = Math.random() * n * 3 - n;
            }
            y[i] = function.countY(x[i]) + dif;
        }
        return y;
    }
}
