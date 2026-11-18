import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Multiple Document Interface (MDI) example using JDesktopPane and JInternalFrame.
 * Each "document" opens in its own movable, resizable window inside one parent window.
 */
public class DMIExample {

    private final JDesktopPane desktop = new JDesktopPane();
    private int windowCount = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DMIExample().createAndShowGui());
    }

    private void createAndShowGui() {
        JFrame frame = new JFrame("MDI Example (JDesktopPane)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        desktop.setBackground(new Color(70, 90, 120));
        frame.setContentPane(desktop);
        frame.setJMenuBar(createMenuBar(frame));

        // Open one document at start
        addDocument();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JMenuBar createMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        // ----- File menu -----
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem newItem = new JMenuItem("New");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        newItem.addActionListener(e -> addDocument());

        JMenuItem closeAllItem = new JMenuItem("Close All");
        closeAllItem.addActionListener(e -> {
            for (JInternalFrame f : desktop.getAllFrames()) {
                f.dispose();
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.add(closeAllItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // ----- Window menu -----
        JMenu windowMenu = new JMenu("Window");
        windowMenu.setMnemonic(KeyEvent.VK_W);

        JMenuItem cascadeItem = new JMenuItem("Cascade");
        cascadeItem.addActionListener(e -> cascade());

        JMenuItem tileItem = new JMenuItem("Tile");
        tileItem.addActionListener(e -> tile());

        windowMenu.add(cascadeItem);
        windowMenu.add(tileItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);
        return menuBar;
    }

    // Creates one internal frame (a "document") with a text area
    private void addDocument() {
        windowCount++;
        JInternalFrame internal = new JInternalFrame(
                "Document " + windowCount,
                true,   // resizable
                true,   // closable
                true,   // maximizable
                true);  // iconifiable

        JTextArea textArea = new JTextArea("Type in Document " + windowCount + "...");
        internal.add(new JScrollPane(textArea));
        internal.setSize(300, 200);

        // Offset each new window so they don't stack exactly on top of each other
        int offset = 30 * ((windowCount - 1) % 10);
        internal.setLocation(offset, offset);

        internal.setVisible(true);
        desktop.add(internal);

        try {
            internal.setSelected(true);
        } catch (java.beans.PropertyVetoException ignored) {
        }
    }

    private void cascade() {
        int offset = 0;
        for (JInternalFrame f : desktop.getAllFrames()) {
            if (f.isIcon()) continue;
            f.setBounds(offset, offset, 300, 200);
            f.toFront();
            offset += 30;
        }
    }

    private void tile() {
        JInternalFrame[] frames = desktop.getAllFrames();
        if (frames.length == 0) return;

        int cols = (int) Math.ceil(Math.sqrt(frames.length));
        int rows = (int) Math.ceil((double) frames.length / cols);
        int w = desktop.getWidth() / cols;
        int h = desktop.getHeight() / rows;

        for (int i = 0; i < frames.length; i++) {
            frames[i].setBounds((i % cols) * w, (i / cols) * h, w, h);
        }
    }
}