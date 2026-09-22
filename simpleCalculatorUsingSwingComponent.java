// Write a java program to create a Simple calculator using Swing components. The calculator should have buttons for digits 0-9, basic operations (+, -, *, /), and a display area to show the result. Implement the functionality to perform calculations based on user input.
import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class simpleCalculatorUsingSwingComponent {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        JTextField display = new JTextField();
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(e -> {
                String command = e.getActionCommand();
                if (command.equals("=")) {
                    try {
                        display.setText(evaluate(display.getText()));
                    } catch (Exception ex) {
                        display.setText("Error");
                    }
                } else {
                    display.setText(display.getText() + command);
                }
            });
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static String evaluate(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            return "0";
        }

        expression = expression.replaceAll("\\s+", "");
        if (expression.isEmpty()) {
            return "0";
        }

        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                current.append(ch);
            } else if (isOperator(ch)) {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }

                if (ch == '-' && (tokens.isEmpty() || isOperator(tokens.get(tokens.size() - 1).charAt(0)))) {
                    current.append('-');
                } else {
                    tokens.add(String.valueOf(ch));
                }
            } else {
                throw new IllegalArgumentException("Invalid input");
            }
        }

        if (current.length() > 0) {
            tokens.add(current.toString());
        }

        if (tokens.isEmpty()) {
            return "0";
        }

        Deque<Double> values = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                values.push(Double.parseDouble(token));
            } else if (isOperator(token.charAt(0))) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token.charAt(0))) {
                    applyOperator(values, operators.pop());
                }
                operators.push(token.charAt(0));
            }
        }

        while (!operators.isEmpty()) {
            applyOperator(values, operators.pop());
        }

        if (values.isEmpty()) {
            return "0";
        }

        double result = values.pop();
        if (Double.isInfinite(result) || Double.isNaN(result)) {
            return "Error";
        }

        if (result == Math.rint(result)) {
            return String.valueOf((long) result);
        }
        return String.valueOf(result);
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int precedence(char operator) {
        if (operator == '*' || operator == '/') {
            return 2;
        }
        return 1;
    }

    private static void applyOperator(Deque<Double> values, char operator) {
        if (values.size() < 2) {
            throw new IllegalArgumentException("Invalid expression");
        }

        double right = values.pop();
        double left = values.pop();

        switch (operator) {
            case '+':
                values.push(left + right);
                break;
            case '-':
                values.push(left - right);
                break;
            case '*':
                values.push(left * right);
                break;
            case '/':
                if (right == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                values.push(left / right);
                break;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}
