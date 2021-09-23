import java.util.Stack;

public class CalculatorWithStrings {

    public double mathValueFromStrings(String input) { // Converts the input value to a postfix string, using the Shunting-yard algorithm, passes the resulting postfix string to
                                                       // the getResultFromPostfix() method, which turns the postfix string into the result of the equation and returns that result
        if (input.isEmpty()) {
            throw new IllegalArgumentException("No value found");
        }

        System.out.println("Input: " + input);

        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) { // Iterates over the input string and check each character. Does different things based on what that character is.

            while (input.charAt(i) == ' ') { // If the character is a whitespace, skip it
                ++i;
            }

            if (Character.isDigit(input.charAt(i))) { // If the character is a digit, add it to the result String
                result.append(input.charAt(i));

                if (i + 1 >= input.length() || !Character.isDigit(input.charAt(i+1))) { // If the character after the digit is not a digit, add a whitespace to result instead
                    result.append(' ');
                }
            } else if (input.charAt(i) == '(') { // If the character is a (, push it to the stack
                stack.push(input.charAt(i));
            } else if (input.charAt(i) == ')') { // If the character is a ), pop the last item from the stack and add it to result until a ( is encountered. When it is, remove it,
                                                 // but don't add it to result
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                    result.append(' ');
                }
                stack.pop();
            } else if (getPrecedence(input.charAt(i)) != 0) { // If the current character is an operator
                // While the stack isn't empty and the precedence of the last operator on the stack is greater than, or equal to the precedence of the current operator,
                // add the last character from the stack to the result string, put a whitespace after it and remove it from the stack
                while ((!stack.isEmpty()) && (getPrecedence(stack.peek()) >= getPrecedence(input.charAt(i))) && (stack.peek() != '(')) {
                    result.append(stack.pop());
                    result.append(' ');
                }
                stack.push(input.charAt(i)); // Add the current operator to the stack after removing the last operator from the stack
            }
        }

        while (!stack.isEmpty()) { // Dump all of the remaining items from the stack into the result string
            result.append(stack.pop());
            result.append(' ');
        }

        return getResultFromPostfix(result.toString()); // Return the result from the postfix string

    }

    public double getResultFromPostfix(String postfix) { // Get the result from the postfix string
        Stack<Double> stack = new Stack<>();
        System.out.println("Parsed postfix string: " + postfix);

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (Character.isDigit(c)) { // If the character is a digit, push it to the stack. If multiple digits are in a row, push them together
                double n = 0;

                while(Character.isDigit(c)) {
                    n = n * 10 + (c - '0');
                    i++;
                    c = postfix.charAt(i);
                }

                i--;

                stack.push(n);
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') { // If the character is an operator, pop two numbers from the stack, store them in variables and apply that operator to them. Then push the result to the stack
                     // For example: "1 + 2 + 3 - 2" gets converted to "1 2 + 3 + 2 -". 1 and 2 are pushed to the stack. When the loop gets to the 1st +, it stores 1 and 2 in val1 and val2
                     // respectively, and adds them together. It then pushes the result, which is 3, to the stack. It keeps checking the following characters in the string and gets to 3.
                     // When it gets to the 2nd +, it stores the two 3s from the stack into val1 and val2 and adds them together, then pushes the result to the stack (6). Pushes 2 to the stack,
                     // gets to - and does the subtraction 6 - 2 and pushes 4 to the stack, which is the final result
                double val1 = stack.pop();
                double val2 = stack.pop();

                if (val1 == 0) {
                    throw new ArithmeticException("Division by zero");
                }

                switch (c) {
                    case '+' -> stack.push(addition(val2, val1));
                    case '-' -> stack.push(subtraction(val2, val1));
                    case '/' -> stack.push(division(val2, val1));
                    case '*' -> stack.push(multiplication(val2, val1));
                    case '^' -> stack.push(Math.pow(val2, val1));
                    default -> throw new IllegalArgumentException("Something went wrong");
                }
            } else if (c != ' ') {
                throw new IllegalArgumentException("Invalid character");
            }
        }

        double result = stack.pop();
        System.out.printf("Result: %.1f\n%n", result);
        return Math.round(result); // Return the result from the stack
    }

    private int getPrecedence (char ch) { // Checks the precedence of the operators. * and / have the same precedence as each other and higher precedence than + and -, which also have
                                          // the same precedence
        return switch (ch) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
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
}
