package approximation.instruments;

import approximation.function.Function;
import approximation.function.LinearApproximation;
import approximation.function.LogarithmApproximation;
import approximation.function.SquareApproximation;

public class CoefficientGenerator {
    private final Function function;

    public CoefficientGenerator(Function function) {
        this.function = function;
    }

    public void generateCoefficients() {
        double a;
        double b;
        double c;
        switch (function.getType()) {
            case LINEAR: {
                a = Math.random() * 5 - 2;
                b = Math.random() * 10 - 5;
                function.setA(a);
                function.setB(b);
                System.out.println("Generated : a = " + a + ", b = " + b);
                return;
            }
            case SQUARE: {
                a = Math.random() * 3 - 1;
                b = Math.random() * 12 - 6;
                c = Math.random() * 10 - 5;
                function.setA(a);
                function.setB(b);
                function.setC(c);
                System.out.println("Generated: a = " + a + ", b = " + b + ", c = " + c);
                return;
            }
            case LOGARITHM: {
                a = Math.random() * 4 - 2;
                b = Math.random() * 10 - 5;
                function.setA(a);
                function.setB(b);
                System.out.println("Generated: a = " + a + ", b = " + b);
            }
        }
    }

    public void calculateCoefficients(double[] x, double[] y) {
        switch (function.getType()) {
            case LINEAR: {
                LinearApproximation linearApproximation = new LinearApproximation(x, y);
                function.setA(linearApproximation.getA());
                function.setB(linearApproximation.getB());
                return;
            }
            case SQUARE: {
                SquareApproximation squareApproximation = new SquareApproximation(x, y);
                function.setA(squareApproximation.getA());
                function.setB(squareApproximation.getB());
                function.setC(squareApproximation.getC());
                return;
            }
            default: {
                LogarithmApproximation logarithmApproximation = new LogarithmApproximation(x, y);
                function.setA(logarithmApproximation.getA());
                function.setB(logarithmApproximation.getB());
            }
        }
    }
}
