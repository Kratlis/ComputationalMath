package equationSystem.cli;

import java.util.Scanner;

public class Reader {
    Scanner in;

    public Reader(Scanner in) {
        this.in = in;
    }

    public double readDouble() {
        try {
            return Double.parseDouble(in.next().split(" ")[0].replace(",", "."));
        } catch (Exception e) {
            System.out.println("Возникла ошибка при вводе числа. Введите число ещё раз, пожалуйста.");
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

    public boolean yesNoAnswer() throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        if (answer.equals("y") || answer.equals("yes")) {
            return true;
        }
        if (answer.equals("n") || answer.equals("no")) {
            return false;
        }
        throw new IllegalArgumentException();
    }

    public String readFileName() {
        return in.nextLine();
    }
}
