import java.awt.FlowLayout;
import javax.swing.*;

public class displaying {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Information");
        frame.setLayout(new FlowLayout());

        JLabel name = new JLabel("Name: Rajan Poudel");
        JLabel age = new JLabel("Age: 20");
        JLabel course = new JLabel("Course: BSc CSIT");

        frame.add(name);
        frame.add(age);
        frame.add(course);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
