
import javax.swing.*;

public class textAreaDemo {
    public static void main(String[] args){
        JFrame frame = new JFrame("Text Area");
        JTextArea textArea = new JTextArea();
        textArea.setText(
                "Java Swing \n" +
                        "GUI Programing \n"+
                        "BscCSIT \n"
        );

        frame.add(textArea);
        frame.setSize(400,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

