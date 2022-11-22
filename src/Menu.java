import com.sun.source.tree.ArrayAccessTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Menu extends JMenuBar implements ActionListener {
    private JFrame frame;
    private Dictionary dictionary;
    private JPanel content;
    private JMenu menu;

    public void actionPerformed(ActionEvent ae) {
        CardLayout cardLayout = (CardLayout) content.getLayout();

        if (ae.getActionCommand().equals(App.FEATURES[App.INDEX_RESET])) { // reset feature
            int isConfirm = JOptionPane.showConfirmDialog(frame,
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

    public Menu(JFrame frame, Dictionary dictionary, JPanel content) {
        this.frame = frame;
        this.dictionary = dictionary;
        this.content = content;
        this.menu = new JMenu("Menu");

        for (String menuItemName : App.FEATURES) {
            JMenuItem menuItem = new JMenuItem(menuItemName);
            menuItem.addActionListener(this);
            this.menu.add(menuItem);
        }
        add(menu);
    }
}