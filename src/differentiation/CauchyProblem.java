package differentiation;

import differentiation.cli.Commander;

public class CauchyProblem {
    public static void main(String[] args) {
        Commander commander = new Commander(System.in);
        commander.greet();
        commander.readData();
        commander.graph();
    }
}
