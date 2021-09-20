import java.util.List;

public class JockesRefactorCounter {

    public double stringAddSubParameterized(String inputValue) {

        if (inputValue.isEmpty()) {
            throw new IllegalArgumentException("No value found");
        }
        List<String> inputList = List.of(inputValue.trim().split(" "));

        double num1 = Double.parseDouble(inputList.get(0));
        double num2 = Double.parseDouble(inputList.get(2));
        String type = inputList.get(1);

        if (isValuesAllowed(num1, num2, type)) {
            throw new IllegalArgumentException("Cannot be divided or multiplied by zero");
        }

        if (inputList.size() > 3) {
          String  type2 = inputList.get(3);
           double num3 = Double.parseDouble(inputList.get(4));

            if (isValuesAllowed(num3, type2)) {
                throw new IllegalArgumentException("Cannot be divided or multiplied by zero");
            }
            return advancedCalc(num1, num2, num3, type, type2);

        }
        return simpleCalc(num1, num2, type);
    }

    public boolean isValuesAllowed(double num1, double num2, String type) {
        return (num2 == 0 || num1 == 0) && (type.equals("/") || type.equals("*"));
    }

    public boolean isValuesAllowed(double num3, String type2) {
        return num3 == 0 && (type2.equals("/") || type2.equals("*"));
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

    public double advancedCalc(double num1, double num2, double num3, String type, String type2) {

        switch (type + type2) {
            case "+" + "+" -> {
                return num1 + num2 + num3;
            }
            case "+" + "-" -> {
                return num1 + num2 - num3;
            }
            case "+" + "*" -> {
                return num1 + num2 * num3;
            }
            case "+" + "/" -> {
                return num1 + num2 / num3;
            }
            case "-" + "-" -> {
                return num1 - num2 - num3;
            }
            case "-" + "+" -> {
                return num1 - num2 + num3;
            }
            case "-" + "*" -> {
                return num1 - num2 * num3;
            }
            case "-" + "/" -> {
                return num1 - num2 / num3;
            }
            case "*" + "+" -> {
                return num1 * num2 + num3;
            }
            case "*" + "-" -> {
                return num1 * num2 - num3;
            }
            case "*" + "*" -> {
                return num1 * num2 * num3;
            }
            case "*" + "/" -> {
                return num1 * num2 / num3;
            }
            case "/" + "+" -> {
                return num1 / num2 + num3;
            }
            case "/" + "-" -> {
                return num1 / num2 - num3;
            }
            case "/" + "*" -> {
                return num1 / num2 * num3;
            }
            case "/" + "/" -> {
                return num1 / num2 / num3;
            }
            default -> throw new IllegalArgumentException("Something went wrong");
        }
    }
}
