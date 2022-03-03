import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        // Поехали!
        /*
        Постановка задачи
        Компания по производству спортивных носков решила разработать свой счётчик шагов для
        дополнительной мотивации ведения здорового образа жизни. Написать прототип приложения поручили вам.

        Оно должно предоставлять следующий функционал:
        -Консольный интерфейс для управления программой;
        -Хранение данных о количестве пройденных шагов за несколько месяцев;
        -Ввод вашей цели по количеству шагов в день;
        -Ввод пройденного количества шагов за день;
        -Вывод статистики за определённый месяц.
         */

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
                steptracker.importData("resources/steps2021.csv");
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
}

