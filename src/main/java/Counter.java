public class Counter {

    public int addition(int number1, int number2) {
        return number1 + number2;
    }

    public int subtraction(int num1, int num2) {
        return num1 - num2;
    }

    public int addSubMultDiv(String type, int number1, int number2) {

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
        String type = arrayValues[1];

        if(value.isEmpty()){
            throw new IllegalArgumentException();
        }

        return switch (type) {
            case "add" -> number1 + number2;
            case "sub" -> number1 - number2;
            default -> throw new IllegalArgumentException();
        };

    }
}


