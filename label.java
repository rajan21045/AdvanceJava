import javax.swing.*;
public class label {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My First Frame");
        JLabel label = new JLabel("Hello, World!");
        frame.add(label);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
