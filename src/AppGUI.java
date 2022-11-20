import javax.swing.*;
import java.awt.*;

public class AppGUI {
    final static String features[] = { "Search by slang word",
                                "Search by definition",
                                "View history",
                                "Add slang word",
                                "Edit slang word",
                                "Delete slang word",
                                "Reset to the original dictionary",
                                "Random slang word",
                                "Game 1",
                                "Game 2"};

    public static JMenuBar createMenu(String[] menuItems){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        for(String menuItem : menuItems){
            menu.add(new JMenuItem(menuItem));
        }

        menuBar.add(menu);
        return menuBar;
    }

    public static void addComponentToPane(Container pane){
    }
   public static void createAndShowGUI(){
       JFrame.setDefaultLookAndFeelDecorated(true);

       JFrame frame = new JFrame("20127135 - Đồ án môn học #1");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       addComponentToPane(frame.getContentPane());

       frame.setJMenuBar(createMenu(features));
       frame.pack();
       frame.setSize(500, 500);
       frame.setVisible(true);
       frame.setLocationRelativeTo(null);
   }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
