import javax.swing.*;
import java.awt.*;
public class setfont extends JPanel{
    @Override 
    protected void paintComponent(Graphics g){

        //Arial Font
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Normal Text", 50, 50);

        //Serial Font
        g.setFont(new Font("Serial", Font.BOLD, 24));
        g.drawString("BOLD Text", 50, 100);

        //Italic Font
        g.setFont(new Font("SansSerif", Font.ITALIC, 24));
        g.drawString("ITATIC Text", 50, 150);

        //Bold And Italic
        g.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 24));
        g.drawString("Monospaced Text", 50, 200);

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Special Font");
        frame.add(new setfont());

        frame.setSize(500, 280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
