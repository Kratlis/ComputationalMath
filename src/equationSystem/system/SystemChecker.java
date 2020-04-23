package equationSystem.system;

import equationSystem.checkers.CoefficientChecker;
import equationSystem.checkers.DeterminantCalculator;
import equationSystem.cli.Artist;
import equationSystem.cli.Reader;

import java.util.Scanner;

public class SystemChecker {
    EquationSystem equationSystem;
    CoefficientChecker coefficientChecker;

    public SystemChecker() {
    }

    public boolean correctSystem() {
        if (checkDiagonal()) {
            if (checkDeterminant()) {
                if (checkDiagonallyDominant()) {
                    Artist artist = new Artist(System.out);
                    artist.drawSystem(equationSystem);
                    return true;
                }
            }
        }
        return false;
    }

    public void setEquationSystem(EquationSystem equationSystem) {
        this.equationSystem = equationSystem;
        coefficientChecker = new CoefficientChecker(equationSystem);
    }

    private boolean hasSingleSolution() {
        DeterminantCalculator determinantCalculator = new DeterminantCalculator(equationSystem);
        return determinantCalculator.calculateDeterminant() != 0;
    }

    private boolean checkDeterminant() {
        if (hasSingleSolution()) {
            return true;
        } else {
            System.out.println("Определитель системы равен нулю! Хотите продолжить? [y/n]");
            try {
                return proceed();
            } catch (IllegalArgumentException e) {
                System.out.println("Не удалось разобрать ответ. Введите ответ латинскими буквами. " +
                        "(\"y\", \"yes\", \"n\", \"no\")");
                try {
                    return proceed();
                } catch (Exception ex) {
                    return false;
                }
            }
        }
    }

    private boolean checkDiagonal() {
        if (coefficientChecker.notNullDiagonal()) {
            return true;
        }
        System.out.println("На главной диагонали присутствуют нули!");
        return false;
    }

    private boolean checkDiagonallyDominant() {
        if (coefficientChecker.isDiagonallyDominant()) {
            System.out.println("Какое счастье! В матрице присутствует диагональное преобладание!");
            return true;
        } else {
            System.out.println("Диагональное преобладание в матрице отсутствует. Пробуем его получить...");
            if (fixMatrix()) {
                System.out.println("Диагональное преобладание достигнуто.");
                return true;
            } else {
                System.out.println("Диагонального преобладания достичь не удалось. Хотите продолжить? [y/n]");
                try {
                    return proceed();
                } catch (IllegalArgumentException e) {
                    System.out.println("Не удалось разобрать ответ. Введите ответ латинскими буквами. " +
                            "(\"y\", \"yes\", \"n\", \"no\")");
                    try {
                        return proceed();
                    } catch (Exception ex) {
                        return false;
                    }
                }
            }
        }
    }

    private boolean fixMatrix() {
        createDiagonallyDominant();
        return coefficientChecker.isDiagonallyDominant();
    }

    private void createDiagonallyDominant() {
        double[][] coefficients = equationSystem.getA();
        for (int i = 0; i < coefficients.length; i++) {
            if (!coefficientChecker.checkRowMainCoefficient(i)) {
                int row = coefficientChecker.findRowOfMaxCoefficient(i);
                swapRows(i, row);
            }
        }
    }

    private void swapRows(int firstRow, int secondRow) {
        double[] buffer = equationSystem.getA()[firstRow];
        equationSystem.getA()[firstRow] = equationSystem.getA()[secondRow];
        equationSystem.getA()[secondRow] = buffer;

        double n = equationSystem.getB()[firstRow];
        equationSystem.getB()[firstRow] = equationSystem.getB()[secondRow];
        equationSystem.getB()[secondRow] = n;
    }

    private boolean proceed() {
        Reader reader = new Reader(new Scanner(System.in));
        return reader.yesNoAnswer();
    }
}
