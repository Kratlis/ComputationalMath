package differentiation.function;

public class Function {
    private final int number;

    public Function(int number) {
        this.number = number;
    }

    public double getValue(double x, double y) {
        switch (number) {
            case 1:
                return -5 * x + 4 * y;
            case 2:
                return x * y;
            case 3:
                return y * Math.cos(x);
        }
        throw new ArithmeticException();
    }

    @Override
    public String toString() {
        switch (number) {
            case 1:
                return "y = c1*e^(4x) + 5x/4 + 5/16";
            case 2:
                return "y = c1*e^(x^2 / 2)";
            case 3:
                return "y = c1*e^(sin(x))";
        }
        throw new ArithmeticException();
    }
}
