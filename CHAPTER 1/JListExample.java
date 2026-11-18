import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class JListExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JListExample::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("JList Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 350);
        frame.setLayout(new BorderLayout(10, 10));

        // Model holds the data; JList displays it
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Apple");
        model.addElement("Banana");
        model.addElement("Cherry");
        model.addElement("Mango");

        JList<String> list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Label showing the current selection
        JLabel selectedLabel = new JLabel("Selected: none");

        // Selection listener
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String value = list.getSelectedValue();
                    selectedLabel.setText("Selected: " + (value == null ? "none" : value));
                }
            }
        });

        // Input + buttons to add/remove items
        JTextField input = new JTextField(12);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        addButton.addActionListener(e -> {
            String text = input.getText().trim();
            if (!text.isEmpty()) {
                model.addElement(text);
                input.setText("");
            }
        });

        removeButton.addActionListener(e -> {
            int index = list.getSelectedIndex();
            if (index != -1) {
                model.remove(index);
            }
        });

        JPanel controls = new JPanel(new FlowLayout());
        controls.add(input);
        controls.add(addButton);
        controls.add(removeButton);

        frame.add(new JScrollPane(list), BorderLayout.CENTER);
        frame.add(selectedLabel, BorderLayout.NORTH);
        frame.add(controls, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}