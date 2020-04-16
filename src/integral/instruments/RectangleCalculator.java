package integral.instruments;

import integral.integralls.Integral;

public class RectangleCalculator {
    private final double accuracy;
    private final Integral integral;
    private double difference;

    public RectangleCalculator(double accuracy, Integral integral) {
        this.accuracy = accuracy;
        this.integral = integral;
    }

    public double calculateRectangleWidth(double lowerLimit, double upperLimit) {
        double integralBiggerH;
        double integralSmallerH;
        double h = upperLimit - lowerLimit;
        do {
            h = h / 2;
            integralBiggerH = integral.countIntegral(h);
            integralSmallerH = integral.countIntegral(2 * h);
            difference = Math.abs(integralBiggerH - integralSmallerH) / 3;
        } while (difference > accuracy);
        return h;
    }

    public double getDifference() {
        return difference;
    }
}
