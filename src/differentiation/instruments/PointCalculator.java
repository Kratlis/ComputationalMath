package differentiation.instruments;

import differentiation.function.Function;

import java.util.ArrayList;

public class PointCalculator {
    private final ArrayList<Double> X;
    private final ArrayList<Double> Y;
    private final Function function;
    private double step;

    public PointCalculator(double x0, double y0, double xn, double accuracy, Function function) {
        this.step = (xn - x0) / 2;
        this.function = function;
        X = new ArrayList<>();
        Y = new ArrayList<>();
        X.add(x0);
        Y.add(y0);
        double y_next = getNextYValue(x0, y0);
        while ((y_next - y0) > accuracy) {
            step /= 2;
            y_next = getNextYValue(x0, y0);
        }
        double x1 = x0 + step;
        X.add(x1);
        Y.add(y_next);
        for (double i = x0 + step; i < xn; i += step) {
            y_next = getNextYValue(i, y_next);
            X.add(i + step);
            Y.add(y_next);
        }
    }

    private double getNextYValue(double x_prev, double y_prev) {
        double k0, k1, k2, k3, delta;
        k0 = function.getValue(x_prev, y_prev);
        k1 = function.getValue(x_prev + step / 2, y_prev + k0 * step / 2);
        k2 = function.getValue(x_prev + step / 2, y_prev + k1 * step / 2);
        k3 = function.getValue(x_prev + step, y_prev + k2 * step);
        delta = step * (k0 + 2 * k1 + 2 * k2 + k3) / 6;
        return y_prev + delta;
    }

    public ArrayList<Double> getX() {
        return X;
    }

    public ArrayList<Double> getY() {
        return Y;
    }
}
