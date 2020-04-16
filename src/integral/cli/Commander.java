package integral.cli;

import integral.instruments.IntegralManager;

import java.io.InputStream;
import java.util.Scanner;

public class Commander {
    Reader reader;
    IntegralManager manager;

    public Commander(InputStream in) {
        reader = new Reader(new Scanner(in));
    }

    public void greet() {
        System.out.println("Здравствуйте! \t\\(^v^)/\n" +
                "==================================" +
                "\nЭто программа для нахождения интеграла функций методом прямоугольников.\n" +
                "Вы выбираете функцию для интегрирования, задаёте точность и пределы интегрирования.\n" +
                "Программа выводит значение интеграла, количество разбиений и полученную погрешность. " +
                "Для оценки погрешности используется оценка Рунге.\n\n" +
                "Доступные функции:\n" +
                "1. y = x^2 + 3 * x - 7;\n" +
                "2. y = 1 / (1 - x^2);\n" +
                "3. y = -2 * x, x < 1;\n" +
                "y = 2 * x, x >= 1" +
                "4. y = sin(x);\n" +
                "5. y = 1 / x.\n");
    }

    public void defineFunction() {
        System.out.println("Выберите функцию и введите её номер:");
        int number = reader.readInt();
        manager = new IntegralManager(number);
    }

    public void defineAccuracy() {
        System.out.println("Введите точность:");
        double accuracy = reader.readDouble();
        manager.setAccuracy(accuracy);
    }

    public void defineLimits() {
        System.out.println("Введите нижнюю границу интегрирования:");
        double lower = reader.readDouble();
        System.out.println("Введите верхнюю границу интегрирования:");
        double upper = reader.readDouble();
        if (upper >= lower) {
            try {
                manager.setLimits(lower, upper);
            } catch (ArithmeticException e) {
                System.out.println("Интеграл равен нулю.");
                System.exit(0);
            }
        } else {
            manager.setLimits(upper, lower);
        }
        checkLimits();
    }

    public void calculateIntegral() {
        System.out.println("Метод левых прямоугольников:\nЗначение интеграла: " +
                manager.calculateLeftRectanglesIntegral() +
                "\nКоличество разбиений: " + manager.getN() +
                "\nПогрешность: " + manager.getDifference() +
                "\n---------------------------------------");
        System.out.println("Метод правых прямоугольников:\nЗначение интеграла: " +
                manager.calculateRightRectanglesIntegral() +
                "\nКоличество разбиений: " + manager.getN() +
                "\nПогрешность: " + manager.getDifference() +
                "\n---------------------------------------");
        System.out.println("Метод средних прямоугольников:\nЗначение интеграла: " +
                manager.calculateMiddleRectanglesIntegral() +
                "\nКоличество разбиений: " + manager.getN() +
                "\nПогрешность: " + manager.getDifference() +
                "\n---------------------------------------");
    }

    private void checkLimits() {
        if (!manager.isIntegralExist()) {
            System.out.println("Определенного интеграла на этом промежутке не существует.");
            System.exit(1);
        }
        if (manager.hasEssentialDiscontinuity()) {
            System.out.println("Заданный промежуток содержит точки разрыва.");
            System.exit(1);
        }
    }
}
