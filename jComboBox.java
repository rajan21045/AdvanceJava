import javax.swing.*;
import java.awt.*;
public class jComboBox {
    // Class implementation goes here
    public static void main(String[] args) {
        // Create JFrame
        JFrame frame = new JFrame("JComboBox Example");
        
        // Array of items
        String arr[] = {"BCA", "CSIT", "BBA", "MCA", "MBA"};
        
        // Create JComboBox
        JComboBox<String> cmb = new JComboBox<String>();

        // Add items to JComboBox
        cmb.addItem("BCA");
        cmb.addItem("CSIT");
        cmb.addItem("BBA");
        cmb.addItem("MCA");
        cmb.addItem("MBA");

        // Add JComboBox to JFrame
        frame.add(cmb);

        // Set layout
        frame.setLayout(new FlowLayout());

        // Set frame size
        frame.setSize(400, 200);

        // Close application when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make frame visible
        frame.setVisible(true);
    }
}