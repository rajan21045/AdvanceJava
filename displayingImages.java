import javax.swing.*;
import java.awt.*;
public class displayingImages extends JPanel{
    Image image;
    public displayingImages(){
        image = new ImageIcon("images.jpg").getImage();
    }

    @Override 
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(image, 50, 50, this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Example");
        frame.add(new displayingImages());

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
