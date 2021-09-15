public class Counter {

    public int addition(int number1, int number2) {
        return number1 + number2;
    }

    public int subtraction(int num1, int num2) {
        return num1 - num2;
    }

    public int addSubMultDiv(String type, int number1, int number2) {

        if (((number2 == 0 || number1 == 0) && (type.equals("division") || type.equals("multiplication")))) {
            throw new IllegalArgumentException();
        }

        return switch (type) {
            case "addition" -> number1 + number2;
            case "subtraction" -> number1 - number2;
            case "multiplication" -> number1 * number2;
            case "division" -> number1 / number2;
            default -> throw new IllegalArgumentException();
        };
    }

    public int stringAddSub(String value) {

        String[] arrayValues = value.trim().split("\\s+");
        int number1 = Integer.parseInt(arrayValues[0]);
        int number2 = Integer.parseInt(arrayValues[2]);
        int number3 = 0;
        String type = arrayValues[1];
        String type2 = "";

        if (value.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (arrayValues.length > 3) {
            type2 = arrayValues[3];
            number3 = Integer.parseInt(arrayValues[4]);
        }

        if (((number2 == 0 || number1 == 0) && (type.equals("/") || type.equals("*")))) {
            throw new IllegalArgumentException();
        }

        return switch (type + type2) {

            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            case "/" -> number1 / number2;

            case "+" + "+" -> number1 + number2 + number3;
            case "+" + "-" -> number1 + number2 - number3;
            case "+" + "*" -> number1 + number2 * number3;
            case "+" + "/" -> number1 + number2 / number3;

            case "-" + "-" -> number1 - number2 - number3;
            case "-" + "+" -> number1 - number2 + number3;
            case "-" + "*" -> number1 - number2 * number3;
            case "-" + "/" -> number1 - number2 / number3;

            case "*" + "+" -> number1 * number2 + number3;
            case "*" + "-" -> number1 * number2 - number3;
            case "*" + "*" -> number1 * number2 * number3;
            case "*" + "/" -> number1 * number2 / number3;

            case "/" + "+" -> number1 / number2 + number3;
            case "/" + "-" -> number1 / number2 - number3;
            case "/" + "*" -> number1 / number2 * number3;
            case "/" + "/" -> number1 / number2 / number3;

            default -> throw new IllegalArgumentException();
        };
    }
}


