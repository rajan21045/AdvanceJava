// This program creates a GUI in Java that accepts two numbers,
// compares them, and shows the smaller number when the mouse is pressed
// and the greater number when the mouse is released.

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class questionOne {
    public static void main(String[] args) {
        // Run the Swing code on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Create the main window
            JFrame frame = new JFrame("Number Comparison");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(4, 2, 10, 10));
            frame.setSize(450, 220);
            frame.setLocationRelativeTo(null);

            // First number label and text field
            JLabel firstLabel = new JLabel("Enter First Number:");
            JTextField firstNumberField = new JTextField();

            // Second number label and text field
            JLabel secondLabel = new JLabel("Enter Second Number:");
            JTextField secondNumberField = new JTextField();

            // Result label where the answer will be shown
            JLabel resultTitle = new JLabel("Result");
            JLabel resultLabel = new JLabel("Press mouse here to see smaller number");
            resultLabel.setOpaque(true);
            resultLabel.setBackground(new Color(240, 240, 240));
            resultLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

            // Add all components to the frame
            frame.add(firstLabel);
            frame.add(firstNumberField);
            frame.add(secondLabel);
            frame.add(secondNumberField);
            frame.add(resultTitle);
            frame.add(new JLabel());
            frame.add(new JLabel());
            frame.add(resultLabel);

            // Add mouse listener to the result label
            resultLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    // When the mouse is pressed, display the smaller number
                    showComparison(resultLabel, firstNumberField.getText(), secondNumberField.getText(), true);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // When the mouse is released, display the greater number
                    showComparison(resultLabel, firstNumberField.getText(), secondNumberField.getText(), false);
                }
            });

            // Make the frame visible
            frame.setVisible(true);
        });
    }

    // This method compares the two entered numbers and updates the label
    private static void showComparison(JLabel resultLabel, String firstText, String secondText, boolean isPressed) {
        try {
            // Convert text inputs to double
            double firstNumber = Double.parseDouble(firstText);
            double secondNumber = Double.parseDouble(secondText);

            // Find smaller and greater values
            double smaller = Math.min(firstNumber, secondNumber);
            double greater = Math.max(firstNumber, secondNumber);

            // Display smaller on mouse press and greater on mouse release
            if (isPressed) {
                resultLabel.setText("Smaller Number: " + smaller);
            } else {
                resultLabel.setText("Greater Number: " + greater);
            }
        } catch (NumberFormatException e) {
            // If the user enters invalid input, show an error message
            resultLabel.setText("Please enter valid numbers");
        }
    }
}
