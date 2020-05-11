package approximation.graph;

import approximation.function.Function;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Dataset {
    private XYSeries points;
    private XYSeries first;
    private XYSeries second;

    public XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(points);
        dataset.addSeries(first);
        dataset.addSeries(second);
        return dataset;
    }

    public void createDatasetPoints(double[] x, double[] y) {
        final XYSeries series = new XYSeries("Исходные точки");
        for (int i = 0; i < x.length; i++) {
            series.add(x[i], y[i]);
        }
        points = series;
    }

    public void createDatasetFirst(Function function) {
        switch (function.getType()) {
            case SQUARE:
                first = DatasetUtils.sampleFunction2DToSeries(v -> function.getA() * Math.pow(v, 2) + function.getB() * v + function.getC(),
                        points.getMinX(),
                        points.getMaxX(),
                        points.getItemCount(),
                        "Первая");
                break;
            case LINEAR:
                first = DatasetUtils.sampleFunction2DToSeries(v -> function.getA() * v + function.getB(),
                        points.getMinX(),
                        points.getMaxX(),
                        points.getItemCount(),
                        "Первая");
                break;
            case LOGARITHM:
                first = DatasetUtils.sampleFunction2DToSeries(v -> function.getA() * Math.log(v) + function.getB(),
                        points.getMinX(),
                        points.getMaxX(),
                        points.getItemCount(),
                        "Первая");
        }
    }

    public void createDatasetSecond(Function function) {
        switch (function.getType()) {
            case SQUARE:
                second = DatasetUtils.sampleFunction2DToSeries(v -> function.getA() * Math.pow(v, 2) + function.getB() * v + function.getC(),
                        points.getMinX(),
                        points.getMaxX(),
                        points.getItemCount(),
                        "Вторая");
                break;
            case LINEAR:
                second = DatasetUtils.sampleFunction2DToSeries(v -> function.getA() * v + function.getB(),
                        points.getMinX(),
                        points.getMaxX(),
                        points.getItemCount(),
                        "Вторая");
                break;
            case LOGARITHM:
                second = DatasetUtils.sampleFunction2DToSeries(v -> function.getA() * Math.log(v) + function.getB(),
                        points.getMinX(),
                        points.getMaxX(),
                        points.getItemCount(),
                        "Вторая");
        }
    }
}
