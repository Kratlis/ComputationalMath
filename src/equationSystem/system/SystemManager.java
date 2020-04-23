package equationSystem.system;

import equationSystem.cli.Artist;
import equationSystem.cli.Reader;
import equationSystem.fileWorkers.FileManager;
import equationSystem.fileWorkers.FileParser;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class SystemManager {
    private final SystemChecker systemChecker = new SystemChecker();
    private final SystemSolver systemSolver = new SystemSolver();
    private EquationSystem equationSystem;

    public SystemManager() {
    }

    public void setAccuracy(double accuracy) {
        systemSolver.setAccuracy(accuracy);
    }

    public void solveSystem() {
        System.out.println("Точность = " + systemSolver.getAccuracy());
        if (systemChecker.correctSystem()) {
            double[] x;
            try {
                x = systemSolver.findSolution();
            } catch (ArithmeticException e) {
                System.out.println("Решение не сходится.");
                x = systemSolver.getCurrentX();
            }
            double[] dif = systemSolver.countDifference();
            Artist artist = new Artist(System.out);
            for (int i = 0; i < x.length; i++) {
                artist.drawTableLine(x[i], dif[i]);
            }
            System.out.println("Количество итераций: " + systemSolver.getIterationCounter());
        } else System.out.println("Измените матрицу.");
    }

    public void createSystemFromFile(String fileName) throws FileNotFoundException {
        FileManager fileWorker = new FileManager(fileName);

        while (fileWorker.readFile().equals("")) {
            System.out.println("Данные в файле записаны неверно. Введите имя файла.");
            fileWorker = new FileManager(new Scanner(System.in).next());
        }

        FileParser fileParser = new FileParser(fileWorker.readFile());
        equationSystem = new EquationSystem(fileParser.readSystemSize());
        equationSystem.setA(fileParser.readMatrix());
        equationSystem.setB(fileParser.readB());
        systemSolver.setAccuracy(fileParser.readAccuracy());
        prepareCheckerAndSolver();
    }

    public void createRandomSystem(int size) {
        equationSystem = new EquationSystem(size);
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                equationSystem.getA()[i][j] = Math.pow(-1, random.nextInt()) * random.nextInt();
            }
        }
        for (int i = 0; i < size; i++) {
            equationSystem.getB()[i] = Math.pow(-1, random.nextInt()) * random.nextInt();
        }
        prepareCheckerAndSolver();
        writeSystem();
    }

    public void createSystemFromCLI() {
        Reader reader = new Reader(new Scanner(System.in));
        System.out.println("Введите точность:");
        double accuracy = reader.readDouble();
        systemSolver.setAccuracy(accuracy);

        System.out.println("Введите размер матрицы:");
        int n = reader.readInt();
        equationSystem = new EquationSystem(n);
        systemChecker.setEquationSystem(equationSystem);

        System.out.println("Введите коэффициенты и правые части уравнений:");
        fillSystem();
        prepareCheckerAndSolver();
    }

    private void writeSystem() {
        Artist artist = new Artist(System.out);
        artist.drawSystem(equationSystem);
    }

    private void prepareCheckerAndSolver() {
        systemChecker.setEquationSystem(equationSystem);
        systemSolver.build(equationSystem);
    }

    private void fillSystem() {
        Reader reader = new Reader(new Scanner(System.in));
        int n = equationSystem.getSize();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                if (j == n) {
                    equationSystem.addB(reader.readDouble(), i);
                } else {
                    equationSystem.addA(reader.readDouble(), i, j);
                }
            }
        }
    }
}
