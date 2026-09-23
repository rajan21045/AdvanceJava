/*
Write a program to create a GUI application in java that identifies the smaller and greater number between two inputs numbers taken through two text fields and display the result in a label.
if the user presses the mouse it should display the smaller number in the label and if the user releases the mouse it should display the greater number.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class questionPractice extends JFrame {
    public questionPractice() {
        setTitle("Number Comparison");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 220);
        setLayout(new GridLayout(0, 2, 10, 10));

        JLabel firstLabel = new JLabel("Enter First Number:");
        JTextField firstNumberField = new JTextField();

        JLabel secondLabel = new JLabel("Enter Second Number:");
        JTextField secondNumberField = new JTextField();

        JLabel resultTitle = new JLabel("Result");
        JLabel resultLabel = new JLabel("Press mouse here to see smaller number");

        add(firstLabel);
        add(firstNumberField);
        add(secondLabel);
        add(secondNumberField);
        add(resultTitle);
        add(resultLabel);

        resultLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showComparison(resultLabel, firstNumberField.getText(), secondNumberField.getText(), true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showComparison(resultLabel, firstNumberField.getText(), secondNumberField.getText(), false);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            questionPractice frame = new questionPractice();
            frame.setVisible(true);
        });
    }

    public static void showComparison(JLabel resultLabel, String firstNumberText, String secondNumberText, boolean isMousePressed) {
        try {
            double firstNumber = Double.parseDouble(firstNumberText);
            double secondNumber = Double.parseDouble(secondNumberText);

            if (isMousePressed) {
                double smaller = Math.min(firstNumber, secondNumber);
                resultLabel.setText("Smaller Number: " + smaller);
            } else {
                double greater = Math.max(firstNumber, secondNumber);
                resultLabel.setText("Greater Number: " + greater);
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Please enter valid numbers.");
        }
    }
}