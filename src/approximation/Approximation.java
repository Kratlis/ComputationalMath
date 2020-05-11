package approximation;

import approximation.cli.Commander;

public class Approximation {
    public static void main(String[] args) {
        Commander commander = new Commander(System.in);
        commander.greet();
        commander.readDataset();
        commander.approximate();
    }
}
