package integral.function;

public class Function {
    private int number;
    private double accuracy;

    public Function(int number, double accuracy) {
        this.number = number;
        this.accuracy = accuracy;
    }

    public int getNumber() {
        return number;
    }

    public double countY(double x) {
        switch (number) {
            case 1:
                return Math.pow(x, 2) + 3 * x - 7;
            case 2:
                return 1 / (1 - Math.pow(x, 2));
            case 3: {
                if (x > 1) {
                    return 2 * x;
                } else if (x == 1) {
                    return (-2 * (1 - accuracy) + 2 * (1 + accuracy)) / 2;
                } else return -2 * x;
            }
            case 4:
                return Math.sin(x);
            default:
                return 1 / x;
        }
    }


}
