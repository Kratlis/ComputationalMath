package equationSystem.system;

public class EquationSystem {
    private double[][] a;
    private double[] b;
    private final int size;

    public EquationSystem(int n) {
        size = n;
        a = new double[n][n];
        b = new double[n];
    }

    public void addB(double el, int i) {
        b[i] = el;
    }

    public void addA(double el, int i, int j) {
        a[i][j] = el;
    }

    public int getSize() {
        return size;
    }

    public double[][] getA() {
        return a;
    }

    public void setA(double[][] a) {
        this.a = a;
    }

    public double[] getB() {
        return b;
    }

    public void setB(double[] b) {
        this.b = b;
    }
}
