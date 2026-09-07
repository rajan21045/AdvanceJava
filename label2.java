import javax.swing.*;
public class label2 {
public static void main(String[] args) {
        JFrame frame = new JFrame("My First Frame");
        JLabel label = new JLabel("Hello, World!");
        label.setText("Welcome");
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        label.setForeground(java.awt.Color.BLUE);
        frame.add(label);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
