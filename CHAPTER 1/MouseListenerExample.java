import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseListenerExample implements MouseListener, MouseMotionListener {

    private final JPanel drawPanel = new JPanel();
    private final JLabel positionLabel = new JLabel("Position: -");
    private final JLabel eventLabel = new JLabel("Event: -");
    private final JTextArea log = new JTextArea(8, 30);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MouseListenerExample().createAndShowGui());
    }

    private void createAndShowGui() {
        JFrame frame = new JFrame("MouseListener Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Panel that receives the mouse events
        drawPanel.setPreferredSize(new Dimension(400, 200));
        drawPanel.setBackground(Color.WHITE);
        drawPanel.setBorder(BorderFactory.createTitledBorder("Move, click or drag the mouse here"));
        drawPanel.addMouseListener(this);        // click, press, release, enter, exit
        drawPanel.addMouseMotionListener(this);  // move, drag

        JPanel info = new JPanel(new GridLayout(2, 1));
        info.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        info.add(positionLabel);
        info.add(eventLabel);

        log.setEditable(false);

        frame.add(drawPanel, BorderLayout.NORTH);
        frame.add(info, BorderLayout.CENTER);
        frame.add(new JScrollPane(log), BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void report(String eventName, MouseEvent e) {
        positionLabel.setText("Position: (" + e.getX() + ", " + e.getY() + ")");
        eventLabel.setText("Event: " + eventName);
        log.append(eventName + " at (" + e.getX() + ", " + e.getY() + ")\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    private String buttonName(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) return "left";
        if (SwingUtilities.isRightMouseButton(e)) return "right";
        if (SwingUtilities.isMiddleMouseButton(e)) return "middle";
        return "unknown";
    }

    // ----- MouseListener -----

    @Override
    public void mouseClicked(MouseEvent e) {
        report("clicked (" + buttonName(e) + " button, " + e.getClickCount() + "x)", e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawPanel.setBackground(new Color(255, 230, 230));
        report("pressed (" + buttonName(e) + ")", e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawPanel.setBackground(Color.WHITE);
        report("released", e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        drawPanel.setBackground(new Color(230, 245, 255));
        report("entered", e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        drawPanel.setBackground(Color.WHITE);
        report("exited", e);
    }

    // ----- MouseMotionListener -----

    @Override
    public void mouseMoved(MouseEvent e) {
        positionLabel.setText("Position: (" + e.getX() + ", " + e.getY() + ")");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        report("dragged", e);
    }
}