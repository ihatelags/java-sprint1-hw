/*
В этом классе осуществляется преобразование шагов в километры и калории.
Для подсчёта дистанции можно считать, что один шаг равен 75 см.
Для подсчёта количества сожжённых килокалорий можно считать, что 1 шаг = 50 калорий, 1 килокалория = 1 000 калорий.
 */
public class Converter {

    // суммируем шаги за заданный месяц
    public int sumStepsByMonth(int month) {
        int stepsSum = 0;
        int[] monthData = StepTracker.monthToData.get(month);
        for (int step : monthData) {
            stepsSum += step;
        }
        return stepsSum;
    }

    // конвертируем шаги в килокалории в заданном месяце, 1 шаг = 50 калорий
    public double getCaloriesByMonth(int month) {
        return sumStepsByMonth(month) * 0.05;
    }

    // конвертируем шаги в километры в заданном месяце, 1 шаг = 75 см = 0,75 м = 0,00075 км
    public double getDistanceByMonth(int month) {
        return sumStepsByMonth(month) * 0.00075;
    }

}
