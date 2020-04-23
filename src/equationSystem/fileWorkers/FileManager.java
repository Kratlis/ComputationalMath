package equationSystem.fileWorkers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManager {

    private File workFile;

    public FileManager(String fileName) {
        while (!checkFileUse(fileName)) {
            System.out.println("Файл не может быть использован. Введите имя файла.");
            fileName = new Scanner(System.in).next();
        }
        workFile = new File(fileName);
    }

    public String readFile() throws FileNotFoundException {
        StringBuilder s = new StringBuilder();
        Scanner scanner = new Scanner(workFile);
        while (scanner.hasNextLine()) {
            s.append(scanner.nextLine()).append(" ");
        }
        scanner.close();
        return s.toString();
    }

    private boolean checkFileUse(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            if (file.isFile()) {
                if (file.canRead()) {
                    if (file.canWrite()) {
                        System.out.println("Файл найден.");
                        return true;
                    } else {
                        System.out.println("Файл не может быть изменен.");
                        return false;
                    }
                } else {
                    System.out.println("Файл не может быть прочитан.");
                    return false;
                }
            } else {
                if (file.isDirectory()) {
                    System.out.println("Это директория.");
                } else {
                    System.out.println("Неизвестный вид файла.");
                }
                return false;
            }
        } else {
            System.out.println("Файл не найден.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "equationSystem.fileWorkers.FileManager{" +
                "workFile=" + workFile +
                '}';
    }
}
