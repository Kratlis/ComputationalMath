package integral.instruments;

import integral.function.Function;
import integral.integralls.Integral;
import integral.integralls.LeftRectanglesIntegral;
import integral.integralls.MiddleRectanglesIntegral;
import integral.integralls.RightRectanglesIntegral;

public class IntegralManager {
    private final Function function;
    private double accuracy;
    private double lowerLimit;
    private double upperLimit;
    private double h;
    private int n;
    private double difference;

    public IntegralManager(int functionNumber) {
        function = new Function(functionNumber, accuracy);
    }

    public boolean isIntegralExist() {
        return true;
    }

    public boolean hasEssentialDiscontinuity() {
        switch (function.getNumber()) {
            case 2:
                if ((lowerLimit <= -1 && upperLimit >= -1) || (lowerLimit <= 1 && upperLimit >= 1)) {
                    return true;
                }
            case 5:
                if (lowerLimit < 0 && upperLimit > 0) {
                    return true;
                }
        }
        return false;
    }

    public double calculateLeftRectanglesIntegral() {
        Integral leftIntegral = new LeftRectanglesIntegral(function, lowerLimit, upperLimit);
        RectangleCalculator rectangleCalculator = new RectangleCalculator(accuracy, leftIntegral);
        h = rectangleCalculator.calculateRectangleWidth(lowerLimit, upperLimit);
        difference = rectangleCalculator.getDifference();
        n = (int) Math.round((upperLimit - lowerLimit) / h);
        return leftIntegral.countIntegral(h);
    }

    public double calculateRightRectanglesIntegral() {
        Integral rightIntegral = new RightRectanglesIntegral(function, lowerLimit, upperLimit);
        RectangleCalculator rectangleCalculator = new RectangleCalculator(accuracy, rightIntegral);
        h = rectangleCalculator.calculateRectangleWidth(lowerLimit, upperLimit);
        difference = rectangleCalculator.getDifference();
        n = (int) Math.round((upperLimit - lowerLimit) / h);
        return rightIntegral.countIntegral(h);
    }

    public double calculateMiddleRectanglesIntegral() {
        Integral middleIIntegral = new MiddleRectanglesIntegral(function, lowerLimit, upperLimit);
        RectangleCalculator rectangleCalculator = new RectangleCalculator(accuracy, middleIIntegral);
        h = rectangleCalculator.calculateRectangleWidth(lowerLimit, upperLimit);
        difference = rectangleCalculator.getDifference();
        n = (int) Math.round((upperLimit - lowerLimit) / h);
        return middleIIntegral.countIntegral(h);
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setLimits(double lowerLimit, double upperLimit) {
        if (lowerLimit == upperLimit) {
            throw new ArithmeticException();
        }
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public int getN() {
        return n;
    }

    public double getDifference() {
        return difference;
    }
}
