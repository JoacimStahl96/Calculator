import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class CalculatorWithStrings {

    public double mathValueFromStrings(String input) { // Converts the input value to a postfix string, using the Shunting-yard algorithm, passes the resulting postfix string to
                                                       // the getResultFromPostfix() method, which turns the postfix string into the result of the equation and returns that result
        if (input.isEmpty()) {
            throw new IllegalArgumentException("No value found");
        }

        System.out.println("Input: " + input);

        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) { // Iterates over the input string and check each character. Does different things based on what that character is.

            while (input.charAt(i) == ' ') { // If the character is a whitespace, skip it
                ++i;
            }

            if (Character.isDigit(input.charAt(i))) { // If the character is a digit, add it to the result String
                result += input.charAt(i);

                if (i + 1 >= input.length() || !Character.isDigit(input.charAt(i+1))) { // If the character after the digit is not a digit, add a whitespace to result instead
                    result += ' ';
                }
            } else if (input.charAt(i) == '(') { // If the character is a (, push it to the stack
                stack.push(input.charAt(i));
            } else if (input.charAt(i) == ')') { // If the character is a ), pop the last item from the stack and add it to result until a ( is encountered. When it is, remove it,
                                                 // but don't add it to result
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            } else if (getPrecedence(input.charAt(i)) != 0) { // If the current character is an operator
                // While the stack isn't empty and the precedence of the last operator on the stack is greater than, or equal to the precedence of the current operator,
                // add the last character from the stack to the result string, put a whitespace after it and remove it from the stack
                while ((!stack.isEmpty()) && (getPrecedence(stack.peek()) >= getPrecedence(input.charAt(i))) && (stack.peek() != '(')) {
                    result += stack.pop();
                    result += ' ';
                }
                stack.push(input.charAt(i)); // Add the current operator to the stack after removing the last operator from the stack
            }
        }

        while (!stack.isEmpty()) { // Dump all of the remaining items from the stack into the result string
            result += stack.pop();
            result += ' ';
        }

        return getResultFromPostfix(result); // Return the result from the postfix string

    }

    public double getResultFromPostfix(String postfix) { // Get the result from the postfix string
        Stack<Double> stack = new Stack<>();
        System.out.println("Parsed postfix string: " + postfix);

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (c == ' ') { // Skip whitespaces
                continue;
            } else if (Character.isDigit(c)) { // If the character is a digit, push it to the stack. If multiple digits are in a row, push them together
                double n = 0;
                while(Character.isDigit(c)) {
                    n = n * 10 + (int)(c - '0');
                    i++;
                    c = postfix.charAt(i);
                }
                i--;

                stack.push(n);
            } else { // If the character is an operator, pop two numbers from the stack, store them in variables and apply that operator to them. Then push the result to the stack
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
                    case '+':
                        stack.push(addition(val2, val1));
                        break;
                    case '-':
                        stack.push(subtraction(val2, val1));
                        break;
                    case '/':
                        stack.push(division(val2, val1));
                        break;
                    case '*':
                        stack.push(multiplication(val2, val1));
                        break;
                    case '^':
                        stack.push(Math.pow(val2, val1));
                        break;
                    default:
                        throw new IllegalArgumentException("Something went wrong");
                }
            }
        }
        System.out.println("Result: " + stack + "\n");
        return stack.pop(); // Return the result from the stack
    }

    private int getPrecedence (char ch) { // Checks the precedence of the operators. * and / have the same precedence as each other and higher precedence than + and -, which also have
                                          // the same precedence
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
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

//    public double simpleCalc(double num1, double num2, String type) {
//        switch (type) {
//            case "+" -> {
//                return addition(num1, num2);
//            }
//            case "-" -> {
//                return subtraction(num1, num2);
//            }
//            case "*" -> {
//                return multiplication(num1, num2);
//            }
//            case "/" -> {
//                return division(num1, num2);
//            }
//            default -> throw new IllegalArgumentException("Something went wrong");
//        }
//    }
}
