import javax.swing.*;
import java.awt.*;

public class MyFlowLayout {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyFlowLayout::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("FlowLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 300);
        frame.setLayout(new BorderLayout(10, 10));

        // Panel using FlowLayout: components are placed left to right
        // and wrap to the next row when the window is too narrow.
        JPanel flowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        flowPanel.setBorder(BorderFactory.createTitledBorder("FlowLayout panel"));

        for (int i = 1; i <= 8; i++) {
            flowPanel.add(new JButton("Button " + i));
        }

        // Controls to change alignment at runtime
        JRadioButton left = new JRadioButton("Left");
        JRadioButton center = new JRadioButton("Center", true);
        JRadioButton right = new JRadioButton("Right");

        ButtonGroup group = new ButtonGroup();
        group.add(left);
        group.add(center);
        group.add(right);

        left.addActionListener(e -> setAlignment(flowPanel, FlowLayout.LEFT));
        center.addActionListener(e -> setAlignment(flowPanel, FlowLayout.CENTER));
        right.addActionListener(e -> setAlignment(flowPanel, FlowLayout.RIGHT));

        JPanel controls = new JPanel(new FlowLayout());
        controls.add(new JLabel("Alignment:"));
        controls.add(left);
        controls.add(center);
        controls.add(right);

        frame.add(flowPanel, BorderLayout.CENTER);
        frame.add(controls, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void setAlignment(JPanel panel, int alignment) {
        FlowLayout layout = (FlowLayout) panel.getLayout();
        layout.setAlignment(alignment);
        panel.revalidate();
        panel.repaint();
    }
}