import javax.swing.*;
import java.awt.*;
public class displayingImages extends JPanel{
    Image image;
    public displayingImages(){
        image = new ImageIcon("gifimage.gif").getImage();
    }

    @Override 
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(image, 50, 50, 400, 400, this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Example");
        frame.add(new displayingImages());

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
