import javax.swing.*;
import java.awt.*;

public class MyBorderLayout {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyBorderLayout::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("BorderLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 400);

        // BorderLayout has five regions: NORTH, SOUTH, EAST, WEST, CENTER.
        // The two numbers are the horizontal and vertical gaps between regions.
        frame.setLayout(new BorderLayout(5, 5));

        // NORTH: header, keeps its preferred height and stretches across the width
        JLabel header = new JLabel("NORTH - Header", SwingConstants.CENTER);
        header.setOpaque(true);
        header.setBackground(new Color(173, 216, 230));
        header.setPreferredSize(new Dimension(0, 40));

        // SOUTH: status bar, same behavior as NORTH
        JLabel statusBar = new JLabel(" SOUTH - Status bar");
        statusBar.setOpaque(true);
        statusBar.setBackground(new Color(211, 211, 211));
        statusBar.setPreferredSize(new Dimension(0, 30));

        // WEST: sidebar, keeps its preferred width and stretches to full height
        JPanel west = new JPanel(new GridLayout(4, 1, 5, 5));
        west.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        west.setPreferredSize(new Dimension(120, 0));
        west.add(new JButton("Home"));
        west.add(new JButton("Profile"));
        west.add(new JButton("Settings"));
        west.add(new JButton("Logout"));

        // EAST: info panel
        JPanel east = new JPanel(new BorderLayout());
        east.setBorder(BorderFactory.createTitledBorder("EAST"));
        east.setPreferredSize(new Dimension(120, 0));
        east.add(new JLabel("<html>Extra<br>info here</html>", SwingConstants.CENTER),
                BorderLayout.CENTER);

        // CENTER: takes all remaining space
        JTextArea center = new JTextArea("CENTER - takes all remaining space.\n\n"
                + "Resize the window and watch how only this region grows.");
        center.setLineWrap(true);
        center.setWrapStyleWord(true);

        // Buttons in the header update the status bar
        JButton hello = new JButton("Say hello");
        hello.addActionListener(e -> statusBar.setText(" Hello from BorderLayout!"));
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(header, BorderLayout.CENTER);
        headerPanel.add(hello, BorderLayout.EAST);

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(statusBar, BorderLayout.SOUTH);
        frame.add(west, BorderLayout.WEST);
        frame.add(east, BorderLayout.EAST);
        frame.add(new JScrollPane(center), BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}