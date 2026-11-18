import javax.swing.*;
import java.awt.*;

public class MyGridLayout {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyGridLayout::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("GridLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 400);
        frame.setLayout(new BorderLayout(10, 10));

        // Display at the top
        JTextField display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(display.getFont().deriveFont(24f));
        display.setEditable(false);

        // GridLayout: 4 rows x 3 columns, 5px gaps. Every cell is the same size.
        JPanel gridPanel = new JPanel(new GridLayout(4, 3, 5, 5));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        String[] labels = {
                "1", "2", "3",
                "4", "5", "6",
                "7", "8", "9",
                "C", "0", "<"
        };

        for (String label : labels) {
            JButton button = new JButton(label);
            button.setFont(button.getFont().deriveFont(20f));
            button.addActionListener(e -> {
                switch (label) {
                    case "C":
                        display.setText("");
                        break;
                    case "<":
                        String text = display.getText();
                        if (!text.isEmpty()) {
                            display.setText(text.substring(0, text.length() - 1));
                        }
                        break;
                    default:
                        display.setText(display.getText() + label);
                }
            });
            gridPanel.add(button);
        }

        frame.add(display, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}