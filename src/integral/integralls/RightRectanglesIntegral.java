package integral.integralls;

import integral.function.Function;

public class RightRectanglesIntegral implements Integral {
    private final Function function;
    private final double lowerLimit;
    private final double upperLimit;

    public RightRectanglesIntegral(Function function, double lowerLimit, double upperLimit) {
        this.function = function;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    @Override
    public double countIntegral(double h) {
        double sum = 0;
        double x = lowerLimit + h;
        int n = (int) Math.round((upperLimit - lowerLimit) / h);
        for (int i = 1; i <= n; i++) {
            sum += function.countY(x);
            x += h;
        }
        return sum * h;
    }
}
