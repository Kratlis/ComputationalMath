package equationSystem.system;

import equationSystem.cli.Artist;

public class SystemSolver {
    private EquationSystem equationSystem;
    private double[] currentX;
    private double[] previousX;
    private int numberX;
    private double accuracy;
    private int iterationCounter = 0;

    public SystemSolver() {
    }

    public void build(EquationSystem system) {
        this.equationSystem = system;
        currentX = system.getB().clone();
        previousX = system.getB().clone();
        numberX = system.getSize();
    }

    public double[] findSolution() throws ArithmeticException {
        currentX = equationSystem.getB().clone();
        previousX = equationSystem.getB().clone();
        Artist artist = new Artist(System.out);
        artist.drawTableHeader(equationSystem.getSize());
        double newAccuracy = 0;
        double previousAccuracy = 0;
        int errCounter = 0;
        do {
            if (newAccuracy >= previousAccuracy) {
                errCounter++;
                if (errCounter > 3) {
                    throw new ArithmeticException();
                }
            }
            makeIteration();
            previousAccuracy = newAccuracy;
            newAccuracy = countMaxDifference();
        }
        while (newAccuracy > accuracy);
        return currentX;
    }

    public double[] countDifference() {
        double[] dif = currentX.clone();
        for (int i = 0; i < dif.length; i++) {
            dif[i] = Math.abs(currentX[i] - previousX[i]);
        }
        return dif;
    }

    public int getIterationCounter() {
        return iterationCounter;
    }

    private void makeIteration() {
        previousX = currentX.clone();
        double[] b = equationSystem.getB().clone();
        double[][] matrix = equationSystem.getA().clone();
        for (int i = 0; i < numberX; i++) {
            currentX[i] = b[i] / matrix[i][i] - calculateSumCurrentX(i) - calculateSumPreviousX(i);
        }
        iterationCounter++;
    }

    private double calculateSumCurrentX(int i) {
        double[][] matrix = equationSystem.getA().clone();
        double sum = 0;
        for (int j = 0; j < i; j++) {
            sum += matrix[i][j] / matrix[i][i] * currentX[j];
        }
        return sum;
    }

    private double calculateSumPreviousX(int i) {
        double[][] matrix = equationSystem.getA().clone();
        double sum = 0;
        for (int j = i + 1; j < numberX; j++) {
            sum += matrix[i][j] / matrix[i][i] * previousX[j];
        }
        return sum;
    }

    private double countMaxDifference() {
        double difference = 0;
        for (int i = 0; i < numberX; i++) {
            double currentDifference = Math.abs(currentX[i] - previousX[i]);
            if (currentDifference > difference) {
                difference = currentDifference;
            }
        }
        return difference;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double[] getCurrentX() {
        return currentX;
    }
}
