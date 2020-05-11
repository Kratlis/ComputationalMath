package approximation.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.xy.XYDataset;

import java.awt.*;

public class Graph extends ApplicationFrame {
    private static final long serialVersionUID = 1L;
    private final XYDataset dataset;
    private double extraX;
    private double extraY;

    public Graph(final String title, XYDataset dataset) {
        super(title);
        this.dataset = dataset;
    }

    public void setExtra(double x, double y) {
        extraX = x;
        extraY = y;
    }

    public void createGraphic() {
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setPreferredSize(new java.awt.Dimension(1024, 720));
        setContentPane(chartPanel);
    }

    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Аппроксимация",
                "x",                        // x axis label
                "y",                        // y axis label
                dataset,                     // data
                PlotOrientation.VERTICAL,
                true,                        // include legend
                false,                       // tooltips
                false                        // urls
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(new Color(232, 232, 232));

        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint(Color.gray);

        // Определение отступа меток делений
        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));

        // Скрытие осевых линий и меток делений
        ValueAxis axis = plot.getDomainAxis();
        axis.setAxisLineVisible(false);    // осевая линия

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        // Удаление связующих линий Series 1
        renderer.setSeriesLinesVisible(0, false);

        // Удаление меток Series 2
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesStroke(1, new BasicStroke(2f));

        renderer.setSeriesShapesVisible(2, false);

        // Настройка графика (цвет, ширина линии) Series 3
        renderer.setSeriesPaint(2, Color.orange);
        renderer.setSeriesStroke(2, new BasicStroke(2.5f));

        XYPointerAnnotation pointer1 = new XYPointerAnnotation(" Extra ", extraX, extraY, Math.PI);
        pointer1.setBaseRadius(20.0);
        pointer1.setTipRadius(1.0);
        pointer1.setPaint(Color.yellow);
        pointer1.setBackgroundPaint(Color.red);
        pointer1.setFont(new Font("SansSerif", Font.PLAIN, 24));
        pointer1.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
        plot.addAnnotation(pointer1);

        plot.setRenderer(renderer);

        // Настройка NumberAxis
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

        rangeAxis.setAxisLineVisible(true);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;
    }
}
