import javax.management.ObjectName;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

public class App {
    private Dictionary dict;
    JFrame frame;
    private JMenu menu;
    private JPanel content;
    private JLabel label;
    private JTextField textField;
    private DefaultTableModel modelTable;
    private JPanel card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10;
    private JPanel cards[] = {card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10};

    LinkedList<Object[]> linkedList;
    Object data[] = new Object[]{"haha", "hihi"};

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

    public App() {
        dict = new Dictionary();
    }

    class MenuActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            menu.setText(ae.getActionCommand());
            CardLayout cardLayout = (CardLayout) content.getLayout();
            cardLayout.show(content, ae.getActionCommand());
        }
    }

    class TextFieldActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            dict.searchWordBySlangWord(ae.getActionCommand());
            modelTable.setRowCount(0);

            for (Map.Entry<String, Set<String>> entry : dict.subDictionary.entrySet()) {
                String row[] = new String[2];
                row[0] = entry.getKey();

                for (int i = 0; i < entry.getValue().size(); i++) {
                    row[1] = (String) entry.getValue().toArray()[i];
                    if (i < entry.getValue().size() - 1) {
                        row[1].concat(", ");
                    }
                }

                data = new Object[]{row[0], row[1]};
                linkedList.add(data);
                System.out.println(row[0]);
                modelTable.insertRow(modelTable.getRowCount(), new Object[]{row[0], row[1]});
            }
            frame.pack();
            frame.setPreferredSize(new Dimension(500, 500));
        }
    }

    private JMenuBar createMenu(String[] menuItems) {
        JMenuBar menuBar = new JMenuBar();
        this.menu = new JMenu("Menu");

        for (String menuItemName : menuItems) {
            JMenuItem menuItem = new JMenuItem(menuItemName);
            menuItem.addActionListener(new MenuActionListener());
            this.menu.add(menuItem);
        }

        menuBar.add(menu);
        return menuBar;
    }

    private void createFeature1GUI(int i) {
        this.cards[i] = new JPanel();

        if(i == 1){
            this.cards[i].setLayout(new BoxLayout(this.cards[i], BoxLayout.Y_AXIS));

            JPanel input = new JPanel();
            JPanel output = new JPanel(new BorderLayout());

            label = new JLabel("Input the slang word: ");

            textField = new JTextField("", 20);

            linkedList = new LinkedList<Object[]>();
            textField.addActionListener(new TextFieldActionListener());

            modelTable = new DefaultTableModel();
            modelTable.addColumn("Slang word");
            modelTable.addColumn("Definition");

            JTable table = new JTable(modelTable);
            table.setEnabled(false);

            JScrollPane scroll = new JScrollPane(table);
            scroll.setBounds(5, 10, 300, 150);
            scroll.setVisible(true);

            input.add(label);
            input.add(textField);

            output.add(table.getTableHeader(), BorderLayout.NORTH);
            output.add(scroll, BorderLayout.CENTER);


            cards[i].add(input);
            cards[i].add(output);
        }

    }

    public void addComponentToPane(Container pane) {
        content = new JPanel(new CardLayout());
//        content = new JPanel();
//        createFeature1GUI(1);
//        content.add(cards[1]);

        for (int i = 0; i < cards.length; i++) {
            createFeature1GUI(i);
            if (i == 0)
                content.add(cards[i], "START");
            else
                content.add(cards[i], features[i - 1]);
        }
        pane.add(content);
    }

    public void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        frame = new JFrame("20127135 - Đồ án môn học #1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(this.createMenu(features));

        this.addComponentToPane(frame.getContentPane());

        frame.pack();
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.dict.readFile("slang.txt");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                app.createAndShowGUI();
            }
        });
    }
}
