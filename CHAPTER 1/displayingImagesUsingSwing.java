import javax.swing.*;
public class displayingImagesUsingSwing{
    public static void main(String[] args) {
        //Creates A Frame
        JFrame frame = new JFrame("Displaying Images");

        //Creating An ImageIcon
        ImageIcon image = new ImageIcon("images.jpg");

        //Creating a Label with the image
        JLabel label = new JLabel(image);

        //Add Label to frame
        frame.add(label);

        //set frame Size
        frame.setSize(500,600);

        //Close Application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Displaying frame
        frame.setVisible(true);

    }
}