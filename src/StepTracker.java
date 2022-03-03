import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class StepTracker {

    // создадим масив с именами месяцев, для вывода на печать и возможно ещё где понадобится
    static String[] monthName = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    public static HashMap<Integer, int[]> monthToData = new HashMap<>();
    Converter converter = new Converter();
    int targetSteps = 10000;

    public StepTracker() {
        for (int i = 0; i < 12; i++) {
            monthToData.put(i, new int[30]);
        }

    }

    static void updateMonthData(int month, int day, int steps) {
            int[] monthData = monthToData.get(month);
            monthData[(day - 1)] = steps;
            monthToData.put(month, monthData);
    }

    void setTargetSteps(int targetInput) {
        targetSteps = targetInput;
    }

    void showStats(int month) {
        int[] days = monthToData.get(month);
        System.out.println("Количество пройденных шагов за " + monthName[month-1] + " по дням:");
        int index = 1;
        for (int steps : days) {
            System.out.println(index++ + " день: " + steps);
            if (steps > targetSteps) {
                System.out.println("(В этот день цель по шагам была достигнута.)");
            }
        }

        System.out.println("");
        System.out.println("Пройденная дистанция: " + converter.getDistanceByMonth(month) + " км");
        System.out.println("Количество сожжённых килокалорий: " + converter.getCaloriesByMonth(month));
    }

    public static void importData(String path) {
        try {
            List<String> data = Files.readAllLines(Paths.get(path));
            data.remove(0);
            System.out.println(data);
            for (String row : data) {
                String[] cols = row.split(",");
                for (String col : cols) {
                    int month = Integer.parseInt(cols[0]);
                    int day = Integer.parseInt(cols[1]);
                    int steps = Integer.parseInt(cols[2]);
                    updateMonthData(month, day, steps);
                }
            }

        } catch (IOException e) {
            System.out.println("Cant read file");
        }
    }
}
