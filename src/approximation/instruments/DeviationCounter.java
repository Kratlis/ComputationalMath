package approximation.instruments;

import approximation.function.Function;

public class DeviationCounter {
    private final double[] x;
    private final double[] y;
    private final Function function;

    public DeviationCounter(double[] x, double[] y, Function function) {
        this.x = x;
        this.y = y;
        this.function = function;
    }

    public int findExtraPoint() {
        int index = 0;
        double maxDeviation = 0;
        for (int i = 0; i < x.length; i++) {
            double deviation = Math.pow(y[i] - function.countY(x[i]), 2);
            if (deviation > maxDeviation) {
                maxDeviation = deviation;
                index = i;
            }
        }
        return index;
    }
}
