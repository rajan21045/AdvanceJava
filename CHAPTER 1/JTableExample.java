import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class JTableExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JTableExample::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("JTable Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout(10, 10));

        // Column names and initial data
        String[] columns = {"ID", "Name", "Department", "Salary"};
        Object[][] data = {
                {1, "Aarav Sharma", "Engineering", 85000.0},
                {2, "Sita Thapa", "Marketing", 62000.0},
                {3, "Ram Gurung", "Finance", 70000.0},
                {4, "Maya Rai", "Engineering", 91000.0}
        };

        // Model holds the data; JTable displays it
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            // Make ID column non-editable and give columns proper types for sorting
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0: return Integer.class;
                    case 3: return Double.class;
                    default: return String.class;
                }
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(24);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);

        // Click column headers to sort; text field below filters rows
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JTextField filterField = new JTextField(15);
        filterField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void apply() {
                String text = filterField.getText().trim();
                sorter.setRowFilter(text.isEmpty()
                        ? null
                        : RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(text)));
            }
            @Override public void insertUpdate(javax.swing.event.DocumentEvent e) { apply(); }
            @Override public void removeUpdate(javax.swing.event.DocumentEvent e) { apply(); }
            @Override public void changedUpdate(javax.swing.event.DocumentEvent e) { apply(); }
        });

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Filter:"));
        top.add(filterField);

        // Add / delete rows
        JTextField nameField = new JTextField(8);
        JTextField deptField = new JTextField(8);
        JTextField salaryField = new JTextField(6);
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete selected");

        final int[] nextId = {model.getRowCount() + 1};

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String dept = deptField.getText().trim();
                double salary = Double.parseDouble(salaryField.getText().trim());
                if (name.isEmpty() || dept.isEmpty()) {
                    throw new IllegalArgumentException("Name and department are required");
                }
                model.addRow(new Object[]{nextId[0]++, name, dept, salary});
                nameField.setText("");
                deptField.setText("");
                salaryField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Salary must be a number.",
                        "Invalid input", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(),
                        "Invalid input", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            int viewRow = table.getSelectedRow();
            if (viewRow != -1) {
                // Convert view index to model index because of sorting/filtering
                model.removeRow(table.convertRowIndexToModel(viewRow));
            }
        });

        JPanel bottom = new JPanel(new FlowLayout());
        bottom.add(new JLabel("Name:"));
        bottom.add(nameField);
        bottom.add(new JLabel("Dept:"));
        bottom.add(deptField);
        bottom.add(new JLabel("Salary:"));
        bottom.add(salaryField);
        bottom.add(addButton);
        bottom.add(deleteButton);

        frame.add(top, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(bottom, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}