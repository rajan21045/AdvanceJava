import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerExample implements KeyListener {

    private final JTextField inputField = new JTextField(20);
    private final JLabel typedLabel = new JLabel("keyTyped: -");
    private final JLabel pressedLabel = new JLabel("keyPressed: -");
    private final JLabel releasedLabel = new JLabel("keyReleased: -");
    private final JTextArea log = new JTextArea(8, 30);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KeyListenerExample().createAndShowGui());
    }

    private void createAndShowGui() {
        JFrame frame = new JFrame("KeyListener Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Register this class as the key listener for the text field
        inputField.addKeyListener(this);

        JPanel top = new JPanel(new FlowLayout());
        top.add(new JLabel("Type here:"));
        top.add(inputField);

        JPanel labels = new JPanel(new GridLayout(3, 1));
        labels.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        labels.add(typedLabel);
        labels.add(pressedLabel);
        labels.add(releasedLabel);

        log.setEditable(false);

        frame.add(top, BorderLayout.NORTH);
        frame.add(labels, BorderLayout.CENTER);
        frame.add(new JScrollPane(log), BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        inputField.requestFocusInWindow();
    }

    // Called when a character is typed (not called for Shift, Ctrl, arrows, F-keys)
    @Override
    public void keyTyped(KeyEvent e) {
        typedLabel.setText("keyTyped: '" + e.getKeyChar() + "'");
        log.append("typed    : '" + e.getKeyChar() + "'\n");
    }

    // Called when any key goes down, including special keys
    @Override
    public void keyPressed(KeyEvent e) {
        String name = KeyEvent.getKeyText(e.getKeyCode());
        pressedLabel.setText("keyPressed: " + name + " (code " + e.getKeyCode() + ")");
        log.append("pressed  : " + name + "\n");

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            log.append(">> Enter pressed, text = \"" + inputField.getText() + "\"\n");
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            inputField.setText("");
        } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
            log.append(">> Ctrl+S detected\n");
        }
    }

    // Called when a key is released
    @Override
    public void keyReleased(KeyEvent e) {
        String name = KeyEvent.getKeyText(e.getKeyCode());
        releasedLabel.setText("keyReleased: " + name);
        log.append("released : " + name + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }
}