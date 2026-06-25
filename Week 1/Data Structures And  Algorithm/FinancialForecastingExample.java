import java.util.HashMap;
import java.util.Map;

class FinancialForecasting {

    public static double forecastFutureValue(double currentValue, double[] growthRates, int month) {
        if (month == growthRates.length) {
            return currentValue;
        }

        double nextValue = currentValue * (1 + growthRates[month]);
        return forecastFutureValue(nextValue, growthRates, month + 1);
    }

    public static double forecastFutureValueMemo(double currentValue, double[] growthRates, int month, Map<Integer, Double> memo) {
        if (month == growthRates.length) {
            return currentValue;
        }

        if (memo.containsKey(month)) {
            return memo.get(month);
        }

        double nextValue = currentValue * (1 + growthRates[month]);
        double result = forecastFutureValueMemo(nextValue, growthRates, month + 1, memo);
        memo.put(month, result);
        return result;
    }
}

public class FinancialForecastingExample {
    public static void main(String[] args) {
        double initialValue = 10000.0;
        double[] growthRates = {0.05, 0.04, -0.02, 0.06, 0.03};

        double futureValue = FinancialForecasting.forecastFutureValue(initialValue, growthRates, 0);
        System.out.println("Future value using recursion: " + futureValue);

        double memoValue = FinancialForecasting.forecastFutureValueMemo(initialValue, growthRates, 0, new HashMap<>());
        System.out.println("Future value using memoization: " + memoValue);
    }
}