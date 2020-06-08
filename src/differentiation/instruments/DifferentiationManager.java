package differentiation.instruments;

import differentiation.function.Function;
import differentiation.graph.Dataset;
import differentiation.graph.Graph;

import java.util.ArrayList;

import static org.jfree.chart.ui.UIUtils.centerFrameOnScreen;

public class DifferentiationManager {
    private final Dataset dataset = new Dataset();
    double x0;
    double y0;
    double endpointOfSegment;
    double accuracy;
    private Function function;

    public DifferentiationManager() {
    }

    public void calculatePoints() {
        PointCalculator pointCalculator = new PointCalculator(x0, y0, endpointOfSegment, accuracy, function);
        ArrayList<Double> x = pointCalculator.getX();
        ArrayList<Double> y = pointCalculator.getY();
        dataset.createDatasetPoints(x, y, function.toString());
    }

    public void drawGraph() {
        Graph graph = new Graph("Решение ОДУ (задача Коши). Лабораторная работа №4", dataset.createDataset());
        graph.createGraphic();
        graph.pack();
        centerFrameOnScreen(graph);
        graph.setVisible(true);
    }

    public void setType(int number) {
        function = new Function(number);
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public void setEndpointOfSegment(double endpointOfSegment) {
        this.endpointOfSegment = endpointOfSegment;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}
