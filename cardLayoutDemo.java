import javax.swing.*;
import java.awt.*;

class CardLayoutDemo extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public CardLayoutDemo() {
        setTitle("CardLayout Demo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create cards
        JPanel card1 = new JPanel();
        card1.add(new JLabel("Card 1"));
        JButton nextButton1 = new JButton("Next");
        nextButton1.addActionListener(e -> cardLayout.next(cardPanel));
        card1.add(nextButton1);

        JPanel card2 = new JPanel();
        card2.add(new JLabel("Card 2"));
        JButton nextButton2 = new JButton("Next");
        nextButton2.addActionListener(e -> cardLayout.next(cardPanel));
        card2.add(nextButton2);

        JPanel card3 = new JPanel();
        card3.add(new JLabel("Card 3"));
        JButton nextButton3 = new JButton("Next");
        nextButton3.addActionListener(e -> cardLayout.next(cardPanel));
        card3.add(nextButton3);

        // Add cards to the panel
        cardPanel.add(card1, "Card 1");
        cardPanel.add(card2, "Card 2");
        cardPanel.add(card3, "Card 3");

        add(cardPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CardLayoutDemo demo = new CardLayoutDemo();
            demo.setVisible(true);
        });
    }
}