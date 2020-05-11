package approximation.instruments;

import approximation.function.ApproximationType;
import approximation.function.Function;
import approximation.graph.Dataset;
import approximation.graph.Graph;

import java.util.Arrays;

import static org.jfree.chart.ui.UIUtils.centerFrameOnScreen;

public class ApproximationManager {
    private final Dataset dataset = new Dataset();
    double[] x;
    double[] y;
    private Function function;

    public ApproximationManager() {
    }

    public void approximate() {
        if (!isDataCorrect()) throw new IllegalArgumentException();
        dataset.createDatasetPoints(x, y);
        CoefficientGenerator coefficientGenerator = new CoefficientGenerator(function);
        coefficientGenerator.calculateCoefficients(x, y);
        dataset.createDatasetFirst(function);
        int extraPoint = new DeviationCounter(x, y, function).findExtraPoint();
        double extraX = x[extraPoint];
        double extraY = y[extraPoint];
        for (int i = 0; i < x.length - 1; i++) {
            if (i > extraPoint) {
                x[i - 1] = x[i];
                y[i - 1] = y[i];
            }
        }
        double[] nexX = Arrays.copyOf(x, x.length - 1);
        double[] nexY = Arrays.copyOf(y, y.length - 1);
        coefficientGenerator.calculateCoefficients(nexX, nexY);
        dataset.createDatasetSecond(function);
        Graph graph = new Graph("Аппроксимация. Лабораторная работа №3", dataset.createDataset());
        graph.setExtra(extraX, extraY);
        graph.createGraphic();
        graph.pack();
        centerFrameOnScreen(graph);
        graph.setVisible(true);
    }

    public void createDataset(int n) {
        CoordinatesGenerator generator = new CoordinatesGenerator(function.getType());
        x = generator.generateX(n);
        y = generator.generateY(n);
    }

    public void setType(int number) {
        switch (number) {
            case 1:
                function = new Function(ApproximationType.SQUARE);
                break;
            case 2:
                function = new Function(ApproximationType.LINEAR);
                break;
            case 3:
                function = new Function(ApproximationType.LOGARITHM);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public void setY(double[] y) {
        this.y = y;
    }

    private boolean isDataCorrect() {
        if (function.getType() == ApproximationType.LOGARITHM) {
            for (double v : x) {
                if (v <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
