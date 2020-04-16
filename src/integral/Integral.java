package integral;

import integral.cli.Commander;

public class Integral {
    public static void main(String[] args) {
        Commander commander = new Commander(System.in);
        commander.greet();
        commander.defineFunction();
        commander.defineAccuracy();
        commander.defineLimits();
        commander.calculateIntegral();
    }

}
