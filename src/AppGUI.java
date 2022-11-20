import javax.management.JMX;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AppGUI implements ActionListener {
    private JMenu menu;
    private JPanel content;
    private JPanel card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10;
    private JPanel cards[] = {card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10};
    public void actionPerformed(ActionEvent ae) {
        this.menu.setText(ae.getActionCommand());
        CardLayout cardLayout = (CardLayout)content.getLayout();
        cardLayout.show(content, ae.getActionCommand());
    }
    private final String features[] = {"1. Search by slang word",
            "2. Search by definition",
            "3. View history",
            "4. Add slang word",
            "5. Edit slang word",
            "6. Delete slang word",
            "7. Reset to the original dictionary",
            "8. Random slang word",
            "9. Game 1",
            "10. Game 2"};

    private JMenuBar createMenu(String[] menuItems) {
        JMenuBar menuBar = new JMenuBar();
        this.menu = new JMenu("Menu");

        for (String menuItemName : menuItems) {
            JMenuItem menuItem = new JMenuItem(menuItemName);
            menuItem.addActionListener(this);
            this.menu.add(menuItem);
        }

        menuBar.add(menu);
        return menuBar;
    }

    public void addComponentToPane(Container pane) {
        content = new JPanel(new CardLayout());

        for(int i =  0; i < cards.length; i++){
            cards[i] = new JPanel();
            cards[i].add(new Label("Card "+ i));
            if(i == 0)
                content.add(cards[i], "START");
            else
                content.add(cards[i], features[i - 1]);
        }

        pane.add(content);
    }

    public void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("20127135 - Đồ án môn học #1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(this.createMenu(features));
        this.addComponentToPane(frame.getContentPane());

        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        AppGUI appGui = new AppGUI();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                appGui.createAndShowGUI();
            }
        });
    }
}
