package approximation.cli;

import approximation.instruments.ApproximationManager;

import java.io.InputStream;
import java.util.Scanner;

public class Commander {
    Reader reader;
    ApproximationManager manager = new ApproximationManager();

    public Commander(InputStream in) {
        reader = new Reader(new Scanner(in));
    }

    public void greet() {
        System.out.println("Здравствуйте! \t\\(^v^)/\n" +
                "==================================" +
                "\nЭто программа для аппроксимации функций.\n" +
                "Вы вводите координаты точек, выбираете аппроксимирующую фнкцию.\n" +
                "Программа строит график и выводит полученные значения аппроксимирующих коэффициентов.\n\n" +
                "Доступные аппроксимирующие функции:\n" +
                "1. y = a * x^2 + b * x + c;\n" +
                "2. y = a * x + b;\n" +
                "3. y = a * log(x) + b.\n");
    }

    public void readDataset() {
        System.out.println("У Вас есть координаты точек? [y/n]");
        if (isAnswerYes()) {
            readCoordinates();
            System.out.println("Выберите аппроксимирующую функцию и введите её номер:");
            int number = reader.readIntBounded(1, 3);
            manager.setType(number);
        } else {
            createDataset();
        }
    }

    public void approximate() {
        try {
            manager.approximate();
        } catch (IllegalArgumentException e) {
            System.out.println("Для логарифмической функции необходимы положительные аргументы. Хотите изменить аппроксимирующую функцию?");
            if (isAnswerYes()) {
                System.out.println("Выберите аппроксимирующую функцию и введите её номер:");
                int number = reader.readIntBounded(1, 3);
                manager.setType(number);
                approximate();
            }
        }
    }

    private void createDataset() {
        System.out.println("Выберите аппроксимирующую функцию и введите её номер:");
        int number = reader.readIntBounded(1, 3);
        manager.setType(number);
        System.out.println("Сколько точек сгенерировать?");
        int n = reader.readInt();
        manager.createDataset(n);
    }

    private void readCoordinates() {
        System.out.println("Сколько точек вы будете вводить?");
        int n = reader.readInt();
        double[] x = new double[n];
        double[] y = new double[n];
        System.out.println("Можете вводить координаты:");
        for (int i = 0; i < n; i++) {
            x[i] = reader.readDouble();
            y[i] = reader.readDouble();
        }
        manager.setX(x);
        manager.setY(y);
    }


    private boolean isAnswerYes() {
        try {
            return reader.isYes();
        } catch (IllegalArgumentException e) {
            System.out.println("Не удалось разобрать ответ. Введите ответ латинскими или русскими буквами. " +
                    "(\"да\", \"y\", \"yes\", \"нет\", \"n\", \"no\")");
            try {
                return reader.isYes();
            } catch (Exception ex) {
                return false;
            }
        }
    }
}
