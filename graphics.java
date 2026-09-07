import javax.swing.*;
import java.awt.*;
public class graphics extends JPanel {
    @Override 
    protected void paintComponent(Graphics g){

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        setBackground(Color.BLUE);
        g2.drawLine(50, 50, 200, 50);
        g2.drawRect(50, 80, 100, 50);
        g2.fillRect(250, 80, 150, 80);
        g2.drawOval(50, 190, 150, 80);
        g2.fillOval(250, 190, 150, 80);
    }

    public static void main(String[] agrs){
        JFrame frame = new JFrame("Graphics Example");
        frame.add(new graphics());
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
