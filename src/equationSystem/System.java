package equationSystem;

import equationSystem.cli.Commander;

public class System {
    public static void main(String[] args) {
        Commander commander = new Commander(java.lang.System.in, java.lang.System.out);
        commander.greet();
        commander.readSystem();
        commander.solveSystem();
    }

}
