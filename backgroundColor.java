import javax.swing.*;
import java.awt.*;
public class backgroundColor {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Background Color");
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);

        frame.add(panel);
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
