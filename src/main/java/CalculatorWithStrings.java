import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CalculatorWithStrings {

    public double mathValueFromStrings(String inputValue) {

        /*
            TODO fixa matematiska regler, smäller på test [3], [4], [5]
         */

        if (inputValue.isEmpty()) {
            throw new IllegalArgumentException("No value found");
        }
        List<String> inputList = new ArrayList<>(List.of(inputValue.trim().split(" ")));


        for (int i = 1; i < inputList.size(); i += 2) {

            if (inputList.contains("*")) {

                i = inputList.indexOf("*");

                double sum = simpleCalc(Double.parseDouble(inputList.get(i - 1)), Double.parseDouble(inputList.get(i + 1)), inputList.get(i));
                inputList.remove(i - 1);
                inputList.remove(i - 1);
                inputList.remove(i - 1);
                inputList.add(i - 1, String.valueOf(sum));


            }
        }
        for (int i = 1; i < inputList.size(); i += 2) {

            if (inputList.contains("/")) {

                i = inputList.indexOf("/");

                double sum = simpleCalc(Double.parseDouble(inputList.get(i - 1)), Double.parseDouble(inputList.get(i + 1)), inputList.get(i));
                inputList.remove(i - 1);
                inputList.remove(i - 1);
                inputList.remove(i - 1);
                inputList.add(i - 1, String.valueOf(sum));


            }
        }


    /*        if ( inputList.contains("/")){

                i= inputList.indexOf("/");

                double sum = simpleCalc(Double.parseDouble(inputList.get(i - 1)), Double.parseDouble(inputList.get(i + 1)), inputList.get(i));
                inputList.remove(i-1);
                inputList.remove(i-1);
                inputList.remove(i-1);
                inputList.add(i-1,String.valueOf(sum));


            }

     */


        String[] operators = {"*", "/", "-", "+"};
    /*
        if (inputList.contains("*") || inputList.contains("/")){
            List<String> rearrangedInputList = new ArrayList<>();

            System.out.println("inputList: " + inputList);
        }

    */
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

    public boolean isValuesAllowed(double num1, double num2, String type) {
        return (num2 == 0 || num1 == 0) && (type.equals("/"));
    }

    public boolean isValuesAllowed(double num3, String type2) {
        return num3 == 0 && (type2.equals("/"));
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
