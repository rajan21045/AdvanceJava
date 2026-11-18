import java.awt.*;
import java.awt.event.*;

public class MyFrameAdapter extends Frame {
    private TextField textField;

    MyFrameAdapter() {
        textField = new TextField();
        add(textField);
        setSize(400, 300);
        setTitle("Adapter Class Example");

        // 1. WindowAdapter
        // WindowAdapter is an abstract class which implements WindowListener interface.
        // It provides empty implementation of all methods of WindowListener.
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Window Is Closing.");
                System.exit(0);
            }
        });

        // 2. MouseAdapter
        // MouseAdapter is an abstract class which implements MouseListener interface.
        // It provides empty implementation of all methods of MouseListener.
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse Is Clicked At: " + e.getX() + ", " + e.getY());
            }
        });

        // 3. KeyAdapter
        // KeyAdapter is an abstract class which implements KeyListener interface.
        // It provides empty implementation of all methods of KeyListener.
        textField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                System.out.println("Key Is Pressed: " + e.getKeyChar());
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MyFrameAdapter();
    }
}