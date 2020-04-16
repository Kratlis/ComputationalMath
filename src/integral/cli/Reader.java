package integral.cli;

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
            if (string.matches("((-)?([0-9])*)(pi)")){
                try{
                    return readPI(string);
                } catch (Exception e1){
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

    private double readPI(String string) throws Exception {
        String coefficient = string.split("pi")[0];
        try{
            return Double.parseDouble(coefficient) * Math.PI;
        } catch (Exception e1){
            if (coefficient.equals("-")){
                return -Math.PI;
            }
            throw new Exception();
        }
    }
}
