package differentiation.cli;

import java.util.Scanner;

public class Reader {
    Scanner in;

    public Reader(Scanner in) {
        this.in = in;
    }

    public double readDouble() {
        String string = in.next().split(" ")[0].replace(",", ".");
        try {
            return Double.parseDouble(string);
        } catch (Exception e) {
            if (string.matches("((-)?([0-9])*)(pi)")) {
                try {
                    return readPI(string);
                } catch (Exception e1) {
                    System.out.println("Возникла ошибка при распознавании коэффициента числа pi." +
                            " Введите число ещё раз, пожалуйста.");
                    return readDouble();
                }
            }
            System.out.println("Возникла ошибка при распознавании числа. Введите число ещё раз, пожалуйста.");
            return readDouble();
        }
    }

    public int readInt() {
        try {
            return Integer.parseInt(in.next().split(" ")[0].replace(",", "."));
        } catch (Exception e) {
            System.out.println("Возникла ошибка при вводе числа. Введите число ещё раз, пожалуйста.");
            return readInt();
        }
    }

    public int readIntBounded(int min, int max) {
        int num = readInt();
        if (num > max || num < min) {
            System.out.println("Введите число от " + min + " до " + max + ".");
            return readInt();
        }
        return num;
    }

    public boolean isYes() throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        if (answer.equals("y") || answer.equals("да") || answer.equals("yes")) {
            return true;
        }
        if (answer.equals("n") || answer.equals("нет") || answer.equals("no")) {
            return false;
        }
        throw new IllegalArgumentException();
    }

    private double readPI(String string) throws Exception {
        String coefficient = string.split("pi")[0];
        try {
            return Double.parseDouble(coefficient) * Math.PI;
        } catch (Exception e1) {
            if (coefficient.equals("-")) {
                return -Math.PI;
            }
            throw new Exception();
        }
    }
}
