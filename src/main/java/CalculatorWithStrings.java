import java.util.ArrayList;
import java.util.List;

public class CalculatorWithStrings {

    public double mathValueFromStrings(String inputValue) {

        if (inputValue.isEmpty()) {
            throw new IllegalArgumentException("No value found");
        }
        ArrayList<String> inputList = new ArrayList<>(List.of(inputValue.trim().split(" ")));

        calculateInRightOrder(inputList);

        double sum = Double.parseDouble(inputList.get(0));
        double result = 0;

        for (int i = 1; i < inputList.size(); i += 2) {
            System.out.println("sum: " + sum + " operator: " + inputList.get(i) + " next number: " + inputList.get(i + 1));
            System.out.println("========================");

            result = simpleCalc(sum, Double.parseDouble(inputList.get(i + 1)), inputList.get(i));
            sum = result;
        }
        return result;
    }

    public ArrayList<String> calculateInRightOrder(ArrayList<String> inputList) {
        for (int i = 1; i < inputList.size(); i += 2) {

            if (inputList.contains("*") && !inputList.contains("/")) {

                i = inputList.indexOf("*");
                double sum = simpleCalc(Double.parseDouble(inputList.get(i - 1)), Double.parseDouble(inputList.get(i + 1)), inputList.get(i));
                inputList.remove(i - 1);
                inputList.remove(i - 1);
                inputList.remove(i - 1);
                inputList.add(i - 1, String.valueOf(sum));
            }

            if (inputList.contains("/") && !inputList.contains("*")) {

                i = inputList.indexOf("/");
                double sum = simpleCalc(Double.parseDouble(inputList.get(i - 1)), Double.parseDouble(inputList.get(i + 1)), inputList.get(i));
                inputList.remove(i - 1);
                inputList.remove(i - 1);
                inputList.remove(i - 1);
                inputList.add(i - 1, String.valueOf(sum));
            }

            if (inputList.contains("*") && inputList.contains("/")) {
                i = inputList.indexOf("*");
                double indexOfDivision = inputList.indexOf("/");

                if (i < indexOfDivision) {
                    double sum = simpleCalc(Double.parseDouble(inputList.get(i - 1)), Double.parseDouble(inputList.get(i + 1)), inputList.get(i));
                    inputList.remove(i - 1);
                    inputList.remove(i - 1);
                    inputList.remove(i - 1);
                    inputList.add(i - 1, String.valueOf(sum));

                } else {
                    i = inputList.indexOf("/");

                    double sum = simpleCalc(Double.parseDouble(inputList.get(i - 1)), Double.parseDouble(inputList.get(i + 1)), inputList.get(i));
                    inputList.remove(i - 1);
                    inputList.remove(i - 1);
                    inputList.remove(i - 1);
                    inputList.add(i - 1, String.valueOf(sum));
                }
            }
            if (inputList.contains("*") && (inputList.contains("+") || inputList.contains("-"))) {

                i = inputList.indexOf("*");
                double sum = simpleCalc(Double.parseDouble(inputList.get(i - 1)), Double.parseDouble(inputList.get(i + 1)), inputList.get(i));
                inputList.remove(i - 1);
                inputList.remove(i - 1);
                inputList.remove(i - 1);
                inputList.add(i - 1, String.valueOf(sum));
            }
            if (inputList.contains("/") && (inputList.contains("+") || inputList.contains("-"))) {

                i = inputList.indexOf("/");
                double sum = simpleCalc(Double.parseDouble(inputList.get(i - 1)), Double.parseDouble(inputList.get(i + 1)), inputList.get(i));
                inputList.remove(i - 1);
                inputList.remove(i - 1);
                inputList.remove(i - 1);
                inputList.add(i - 1, String.valueOf(sum));
            }
        }
        return inputList;
    }

    public double addition(double num1, double num2) {
        return num1 + num2;
    }

    public double subtraction(double num1, double num2) {
        return num1 - num2;
    }

    public double multiplication(double num1, double num2) {
        return num1 * num2;
    }

    public double division(double num1, double num2) {
        try {
            return num1 / num2;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Division by zero");
        }
    }

    public double simpleCalc(double num1, double num2, String type) {
        switch (type) {
            case "+" -> {
                return addition(num1, num2);
            }
            case "-" -> {
                return subtraction(num1, num2);
            }
            case "*" -> {
                return multiplication(num1, num2);
            }
            case "/" -> {
                return division(num1, num2);
            }
            default -> throw new IllegalArgumentException("Something went wrong");
        }
    }
}
