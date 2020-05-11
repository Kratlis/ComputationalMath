package approximation.function;

public class Function {
    ApproximationType type;
    double a;
    double b;
    double c;

    public Function(ApproximationType type) {
        this.type = type;
    }

    public ApproximationType getType() {
        return type;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double countY(double x) {
        switch (type) {
            case LINEAR:
                return a * x + b;
            case SQUARE:
                return a * Math.pow(x, 2) + b * x + c;
            default: {
                return a * Math.log(x) + b;
            }
        }
    }

}
