import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StepTracker steptracker = new StepTracker();

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                int month = readInt(scanner, 1, 12,"Введите месяц (1-12):");
                int day = readInt(scanner, 1, 30,"Введите день (1-30):");
                int steps = readInt(scanner, 0, Integer.MAX_VALUE,"Введите количество пройденных шагов:");
                steptracker.updateMonthData(month, day, steps);
            } else if (command == 2) {
                int month = readInt(scanner, 1, 12,"Введите месяц (1-12):");
                steptracker.showStats(month);
            } else if (command == 3) {
                int target = readInt(scanner, 0, Integer.MAX_VALUE,"Введите цель по количеству шагов в день:");
                steptracker.setTargetSteps(target);
            } else if (command == 4) {
                System.out.println("Вводим данные из файла.");
                importData(steptracker, "resources/steps2021.csv");
            } else if (command == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Ввести количество шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("4 - Ввести данные из файла");
        System.out.println("0 - Выйти из приложения");

    }

    public static int readInt(Scanner scanner, int min, int max, String hint) {
        System.out.println(hint);
        while (true) {
            try {
                int number = scanner.nextInt();
                if (number < min || number > max) {
                    throw new IllegalArgumentException();
                }
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод, введите число");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(hint);
                scanner.nextLine();
            }
        }
    }

     static void importData(StepTracker object, String filePath) {
        try {
            List<String> data = Files.readAllLines(Paths.get(filePath));
            data.remove(0);
            System.out.println(data);
            for (String row : data) {
                String[] cols = row.split(",");
                int month = Integer.parseInt(cols[0]);
                int day = Integer.parseInt(cols[1]);
                int steps = Integer.parseInt(cols[2]);
                object.updateMonthData(month, day, steps);
            }

        } catch (IOException ignored) {
            System.out.println("Cant read file");
        }
    }

}

