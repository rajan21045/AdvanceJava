import javax.swing.*;
import java.awt.event.*;

class eventHandlingAndSwing extends JFrame implements ActionListener {
    JButton b;

    eventHandlingAndSwing() {
        b = new JButton("Click me");
        add(b);
        b.addActionListener(this);

        setTitle("Swing Example");
        setSize(300, 200);
        setLayout(new java.awt.FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked!");
    }

    public static void main(String[] args) {
        new eventHandlingAndSwing();
    }
}