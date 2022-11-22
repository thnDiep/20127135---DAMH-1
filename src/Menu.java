import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Menu extends JMenuBar implements ActionListener {
    private JPanel content;
    private JMenu menu;

    String[] menuItems;

    public void actionPerformed(ActionEvent ae) {
        menu.setText(ae.getActionCommand());
        CardLayout cardLayout = (CardLayout) content.getLayout();
        cardLayout.show(content, ae.getActionCommand());
    }

    public Menu(JPanel content, String[] menuItems) {
        this.content = content;
        this.menu = new JMenu("Menu");
        this.menuItems = menuItems;

        for (String menuItemName : menuItems) {
            JMenuItem menuItem = new JMenuItem(menuItemName);
            menuItem.addActionListener(this);
            this.menu.add(menuItem);
        }
        add(menu);
    }
}