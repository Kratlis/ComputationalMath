package equationSystem.fileWorkers;

import java.util.Scanner;

public class FileParser {
    private String systemInString;
    private Scanner in;

    public FileParser(String string) {
        systemInString = string;
    }

    public int readSystemSize() {
        in = new Scanner(systemInString);
        in.next();
        int n = in.nextInt();
        in.close();
        return n;
    }

    public double[][] readMatrix() {
        in = new Scanner(systemInString);
        in.next();
        int n = in.nextInt();
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                if (j == n) {
                    in.next();
                } else {
                    try {
                        a[i][j] = in.nextDouble();
                    } catch (Exception e){
                        System.out.println("Не удалось считать число: "+e.getMessage());
                    }
                }
            }
        }
        in.close();
        return a;
    }

    public double readAccuracy() {
        in = new Scanner(systemInString);
        double acc = Double.parseDouble(in.next().split(" ")[0].replace(",", "."));
        in.close();
        return acc;
    }

    public double[] readB() {
        in = new Scanner(systemInString);
        in.next();
        int n = in.nextInt();
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                if (j == n) {
                    b[i] = Double.parseDouble(in.next().split(" ")[0].replace(",", "."));;
                } else {
                    in.next();
                }
            }
        }
        in.close();
        return b;
    }
}
