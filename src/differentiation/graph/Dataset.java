package differentiation.graph;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.List;

public class Dataset {
    private XYSeries points;

    public XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(points);
        return dataset;
    }

    public void createDatasetPoints(List<Double> x, List<Double> y, String function) {
        final XYSeries series = new XYSeries(function);
        for (int i = 0; i < x.size(); i++) {
            series.add(x.get(i), y.get(i));
        }
        points = series;
    }

}
