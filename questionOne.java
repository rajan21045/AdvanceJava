//make a GUI application in java that take two number and identify the smaller and greater number between them taken through text field and deplay in a label if the uer press the mouse it should display the smalller one and if user release the mouse display the greater number
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class questionOne {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Number Comparison");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(4, 2, 10, 10));
            frame.setSize(450, 220);
            frame.setLocationRelativeTo(null);

            JLabel firstLabel = new JLabel("Enter First Number:");
            JTextField firstNumberField = new JTextField();

            JLabel secondLabel = new JLabel("Enter Second Number:");
            JTextField secondNumberField = new JTextField();

            JLabel resultTitle = new JLabel("Result");
            JLabel resultLabel = new JLabel("Press mouse here to see smaller number");
            resultLabel.setOpaque(true);
            resultLabel.setBackground(new Color(240, 240, 240));
            resultLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

            frame.add(firstLabel);
            frame.add(firstNumberField);
            frame.add(secondLabel);
            frame.add(secondNumberField);
            frame.add(resultTitle);
            frame.add(new JLabel());
            frame.add(new JLabel());
            frame.add(resultLabel);

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

            frame.setVisible(true);
        });
    }

    private static void showComparison(JLabel resultLabel, String firstText, String secondText, boolean isPressed) {
        try {
            double firstNumber = Double.parseDouble(firstText);
            double secondNumber = Double.parseDouble(secondText);

            double smaller = Math.min(firstNumber, secondNumber);
            double greater = Math.max(firstNumber, secondNumber);

            if (isPressed) {
                resultLabel.setText("Smaller Number: " + smaller);
            } else {
                resultLabel.setText("Greater Number: " + greater);
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numbers");
        }
    }
}
