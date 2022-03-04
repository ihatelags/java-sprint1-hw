import java.util.ArrayList;
import java.util.HashMap;

public class StepTracker {

    HashMap<Integer, int[]> monthToData = new HashMap<>();
    Converter converter = new Converter();
    int targetSteps = 10000;

    // создадим масив с именами месяцев, для вывода на печать и возможно ещё где понадобится
    static String[] monthName = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь",
            "Октябрь", "Ноябрь", "Декабрь"};

    public StepTracker() {
        for (int i = 0; i < 12; i++) {
            monthToData.put(i, new int[30]);
        }

    }

    void updateMonthData(int month, int day, int steps) {
        int[] monthData = monthToData.get(month);
        monthData[(day - 1)] = steps;
        monthToData.put(month, monthData);
    }

    void setTargetSteps(int targetInput) {
        targetSteps = targetInput;
        System.out.println("Ваша новая цель: " + targetSteps + " шагов в день.");
    }

    void showStats(int month) {
        getSumStepsYear();
        System.out.println("Ваша статистика за " + monthName[month - 1] + ":");
        getStepsByDay(month);
        System.out.println();
        getMaxSteps(month);
        getAverageSteps(month);
        getDistanceKM(month);
        getCalories(month);
        getBestStreak(month);
        System.out.println();
    }

    void getStepsByDay(int month) {
        System.out.println("Количество пройденных шагов по дням");
        System.out.println("(звездочкой отмечен день с выполненной целью):");
        int index = 1;
        for (int steps : monthToData.get(month)) {
            System.out.print(index + " день: " + steps);
            if (steps >= targetSteps) {
                System.out.print("*");
            }
            if (index != 31) {
                System.out.print(", ");
            } else {
                System.out.println();
            }

            //перенос строки для более удобного чтения
            if (index %10 == 0) {
                System.out.println();
            }
            index++;
        }
    }


    void getSumStepsYear() {
        int stepsSum = 0;
        for (int[] values : monthToData.values()) {
            for (int step : values) {
                stepsSum += step;
            }
        }
        double distance = converter.getDistance(stepsSum);
        System.out.println("Пройдено за год: " + stepsSum + " (" + String.format("%.2f", distance) + " км)");
    }

    private int getSumStepsByMonth(int month) {
        int stepsSum = 0;
        for (int step : monthToData.get(month)) {
            stepsSum += step;
        }
        return stepsSum;
    }

    void getMaxSteps(int month) {
        int maxSteps = 0;
        for (int step : monthToData.get(month)) {
            if (step > maxSteps) {
                maxSteps = step;
            }
        }
        System.out.println("Максимальное количество шагов: " + maxSteps);
    }

    void getAverageSteps(int month) {
        int averageSteps = getSumStepsByMonth(month) / monthToData.get(month).length;
        System.out.println("Среднее количество шагов: " + averageSteps);
    }

    void getDistanceKM(int month) {
        int steps = getSumStepsByMonth(month);
        double distance = converter.getDistance(steps);
        System.out.println("Пройдено км: " + String.format("%.2f", distance));
    }

    void getCalories(int month) {
        int steps = getSumStepsByMonth(month);
        double calories = converter.getCalories(steps);
        System.out.println("Количество сожжённых килокалорий: " + String.format("%.2f", calories));
    }

    void getBestStreak(int month) {
        //создаем список с нулями
        ArrayList<Integer> stepsAboveTarget = new ArrayList<>(30);
        for (int i = 0; i < 31; i++) {
            stepsAboveTarget.add(0);
        }

        int count = 0, bestStreak = 0, index = 0;

        //создаем список дней, когда цель была выполнена
        for (int steps : monthToData.get(month)) {
            if (steps >= targetSteps) {
                stepsAboveTarget.add(index, steps);
            }
            index++;
        }
        // проходимся по списку и ищем дни подряд
        for (int i = 0; i < stepsAboveTarget.size() - 1; i++) {
            if ((stepsAboveTarget.get(i) > 0) && (stepsAboveTarget.get(i+1) > 0)) {
                count++;
            } else {
                count = 1;
            }

            if (count > bestStreak) {
                bestStreak = count;
            }
        }
        System.out.println("Лучшая серия: " + bestStreak);
    }
}
