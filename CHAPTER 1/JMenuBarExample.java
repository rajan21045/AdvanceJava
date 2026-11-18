import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class JMenuBarExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JMenuBarExample::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("JMenuBar Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);

        JTextArea textArea = new JTextArea();
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JLabel status = new JLabel(" Ready");
        frame.add(status, BorderLayout.SOUTH);

        // ----- Menu bar -----
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem newItem = new JMenuItem("New");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newItem.addActionListener(e -> {
            textArea.setText("");
            status.setText(" New file created");
        });

        JMenuItem openItem = new JMenuItem("Open...");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                status.setText(" Selected: " + chooser.getSelectedFile().getName());
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);

        JMenuItem cutItem = new JMenuItem("Cut");
        cutItem.addActionListener(e -> textArea.cut());
        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.addActionListener(e -> textArea.copy());
        JMenuItem pasteItem = new JMenuItem("Paste");
        pasteItem.addActionListener(e -> textArea.paste());

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // View menu with checkbox and radio items
        JMenu viewMenu = new JMenu("View");

        JCheckBoxMenuItem wrapItem = new JCheckBoxMenuItem("Word Wrap");
        wrapItem.addActionListener(e -> textArea.setLineWrap(wrapItem.isSelected()));
        viewMenu.add(wrapItem);
        viewMenu.addSeparator();

        JRadioButtonMenuItem smallFont = new JRadioButtonMenuItem("Small font");
        JRadioButtonMenuItem largeFont = new JRadioButtonMenuItem("Large font", true);
        ButtonGroup fontGroup = new ButtonGroup();
        fontGroup.add(smallFont);
        fontGroup.add(largeFont);
        smallFont.addActionListener(e -> textArea.setFont(textArea.getFont().deriveFont(12f)));
        largeFont.addActionListener(e -> textArea.setFont(textArea.getFont().deriveFont(18f)));
        viewMenu.add(smallFont);
        viewMenu.add(largeFont);
        textArea.setFont(textArea.getFont().deriveFont(18f));

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "JMenuBar demo", "About",
                        JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}