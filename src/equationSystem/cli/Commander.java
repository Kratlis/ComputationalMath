package equationSystem.cli;

import equationSystem.system.SystemManager;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Commander {
    SystemManager manager;
    Reader reader;
    Artist artist;

    public Commander(InputStream in, OutputStream out) {
        reader = new Reader(new Scanner(in));
        manager = new SystemManager();
        artist = new Artist(out);
    }

    public void greet() {
        System.out.println("Здравствуйте! \t\\(^v^)/\n" +
                "==================================" +
                "\nЭто программа для решений системы линейных алгебраических уравнений методом Гаусса-Зейделя.\n" +
                "Вы можете ввести условия задачи или имя файла, где они записаны.\n" +
                "Также программа может сгенерировать систему самостоятельно, если условий задачи у Вас нет. " +
                "От Вас потребуется ввести только точность и размер системы (количество неизвестных).\n");
    }

    public void readSystem() {
        System.out.println("Ну как? У Вас есть условия задачи? [y/n]");
        if (readYesNoAnswer()) {
            System.out.println("Считаем из файла? [y/n]");
            if (readYesNoAnswer()) {
                try {
                    readSystemFromFile();
                } catch (FileNotFoundException e) {
                    System.out.println("С файлом что-то не так!");
                }
            } else {
                readSystemFromCLI();
            }
        } else {
            createSystem();
        }
    }

    public void solveSystem() {
        manager.solveSystem();
    }

    private void readSystemFromCLI() {
        manager.createSystemFromCLI();

    }

    private void readSystemFromFile() throws FileNotFoundException {
        System.out.println("Введите имя файла:");
        manager.createSystemFromFile(reader.readFileName());
    }

    private void createSystem() {
        System.out.println("Отлично! Коэффициенты будут сгенерированы!\n" +
                "Введите точность:");
        manager.setAccuracy(reader.readDouble());
        System.out.println("Введите размер системы:");
        manager.createRandomSystem(reader.readInt());
    }

    private boolean readYesNoAnswer() {
        try {
            return reader.yesNoAnswer();
        } catch (IllegalArgumentException e) {
            System.out.println("Не удалось разобрать ответ. Введите ответ латинскими буквами. " +
                    "(\"y\", \"yes\", \"n\", \"no\")");
            try {
                return reader.yesNoAnswer();
            } catch (Exception ex) {
                return false;
            }
        }
    }
}
