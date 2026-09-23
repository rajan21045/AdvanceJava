/*
    Write a program to create a GUI application in java that identifies the smaller and greater number between two inputs numbers taken through two text fields and display the result in a label.
    if the user presses the mouse it should display the smaller number in the label and if the user releases the mouse it should display the greater number.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// This program creates a GUI in Java that accepts two numbers,
// compares them, and shows the smaller number when the mouse is pressed
public class questionPractice extends JFrame {
    // Constructor to set up the GUI components and event handling
    public questionPractice() {

        // Set up the frame & its properties
        setTitle("Number Comparison");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 220);
        setLayout(new GridLayout(0, 2, 10, 10));

        // Create labels and text fields for user input
        JLabel firstLabel = new JLabel("Enter First Number:");
        JTextField firstNumberField = new JTextField();

        // Create labels and text fields for user input
        JLabel secondLabel = new JLabel("Enter Second Number:");
        JTextField secondNumberField = new JTextField();

        // Create labels for displaying the result
        JLabel resultTitle = new JLabel("Result");
        JLabel resultLabel = new JLabel("Press mouse here to see smaller number");

        // Set properties for the result label
        add(firstLabel);
        add(firstNumberField);
        add(secondLabel);
        add(secondNumberField);
        add(resultTitle);
        add(resultLabel);

        // Add mouse listener to the result label
        resultLabel.addMouseListener(new MouseAdapter() {
            @Override
            // When the mouse is pressed, display the smaller number
            public void mousePressed(MouseEvent e) {
                showComparison(resultLabel, firstNumberField.getText(), secondNumberField.getText(), true);
            }

            /// When the mouse is released, display the greater number
            @Override
            public void mouseReleased(MouseEvent e) {
                // When the mouse is released, display the greater number
                showComparison(resultLabel, firstNumberField.getText(), secondNumberField.getText(), false);
            }
        });
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create and display the GUI application
            questionPractice frame = new questionPractice();

            // Set the frame to be visible
            frame.setVisible(true);
        });
    }

    // Method to compare the two numbers and update the result label
    public static void showComparison(JLabel resultLabel, String firstNumberText, String secondNumberText, boolean isMousePressed) {
        try {
            // Parse the input numbers from the text fields
            double firstNumber = Double.parseDouble(firstNumberText);
            double secondNumber = Double.parseDouble(secondNumberText);

            // Compare the numbers and update the result label based on mouse press/release
            if (isMousePressed) {
                // When the mouse is pressed, display the smaller number
                double smaller = Math.min(firstNumber, secondNumber);
                resultLabel.setText("Smaller Number: " + smaller);
            } else {
                // When the mouse is released, display the greater number
                double greater = Math.max(firstNumber, secondNumber);
                resultLabel.setText("Greater Number: " + greater);
            }
        } catch (NumberFormatException e) {
            // Handle invalid input by displaying an error message in the result label
            resultLabel.setText("Invalid input. Please enter valid numbers.");
        }
    }
}