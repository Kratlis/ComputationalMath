package differentiation.cli;

import differentiation.instruments.DifferentiationManager;

import java.io.InputStream;
import java.util.Scanner;

public class Commander {
    Reader reader;
    DifferentiationManager manager = new DifferentiationManager();

    public Commander(InputStream in) {
        reader = new Reader(new Scanner(in));
    }

    public void greet() {
        System.out.println("Здравствуйте! \t\\(^v^)/\n" +
                "==================================" +
                "\nЭто программа для решения задачи Коши.\n" +
                "Вы выбираете дифференциальное уравление, задаете начальные условия, конец отрезка и точность.\n" +
                "Программа строит график искомой функции.\n\n" +
                "Доступные дифференциальные уравнения:\n" +
                "1. y' = -5x + 4y;\n" +
                "2. y' = xy;\n" +
                "3. y'= y * cos(x).\n");
    }

    public void readData() {
        readEquationNumber();
        readInitialData();
        readEndpointOfSegment();
        readAccuracy();
    }

    public void graph() {
        manager.calculatePoints();
        manager.drawGraph();
    }

    private void readEquationNumber(){
        System.out.println("Выберите дифференциальное уравнение и введите его номер:");
        int number = reader.readIntBounded(1, 3);
        manager.setType(number);
    }

    private void readEndpointOfSegment() {
        System.out.println("Введите конец отрезка:");
        double xn = reader.readDouble();
        manager.setEndpointOfSegment(xn);
    }

    private void readInitialData() {
        System.out.println("Введите через пробел начальные условия:");
        double x0 = reader.readDouble();
        double y0 = reader.readDouble();
        manager.setX0(x0);
        manager.setY0(y0);
    }

    private void readAccuracy() {
        System.out.println("Введите точность:");
        double accuracy = reader.readDouble();
        manager.setAccuracy(accuracy);
    }
}
