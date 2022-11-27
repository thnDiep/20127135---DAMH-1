import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Menu extends JMenuBar implements ActionListener {
    private final Dictionary dictionary;
    private final JPanel content;
    private final JMenu menu;

    public void actionPerformed(ActionEvent ae) {
        CardLayout cardLayout = (CardLayout) content.getLayout();

        if (ae.getActionCommand().equals(App.FEATURES[App.INDEX_RESET])) { // reset feature
            int isConfirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to reset to the original dictionary?",
                    "Confirm Dialog",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (isConfirm == JOptionPane.YES_OPTION) {
                dictionary.reset();
                menu.setText(App.CONSTRAINT_CARD_MENU);
                cardLayout.show(content, App.CONSTRAINT_CARD_MENU);
            }
        } else {
            menu.setText(ae.getActionCommand());
            cardLayout.show(content, ae.getActionCommand());
        }
    }

    public Menu(Dictionary dictionary, JPanel content) {
        this.dictionary = dictionary;
        this.content = content;
        this.menu = new JMenu(App.CONSTRAINT_CARD_MENU);
        menu.setFont(App.NORMAL_FONT);

        for (String menuItemName : App.FEATURES) {
            JMenuItem menuItem = new JMenuItem(menuItemName);
            menuItem.setFont(App.NORMAL_FONT);
            menuItem.addActionListener(this);
            this.menu.add(menuItem);
        }

        add(menu);
        setPreferredSize(new Dimension(0, 40));
    }
}