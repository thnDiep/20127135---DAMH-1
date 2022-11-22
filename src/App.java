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
    private JTextField textField1, textField2, textField3, textField4;
    private DefaultTableModel modelTable1, modelTable2, modelTable3;
    private JPanel cards[];

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

    class SearchBySlangWordActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            dict.searchWordBySlangWord(ae.getActionCommand());
            modelTable1.setRowCount(0);

            for (Map.Entry<String, Set<String>> entry : dict.getSubDictionary().entrySet()) {
                String row[] = new String[2];

                row[0] = entry.getKey();
                row[1] = "";

                for (int i = 0; i < entry.getValue().size(); i++) {
                    row[1] += (String) entry.getValue().toArray()[i];

                    if (i < entry.getValue().size() - 1) {
                        row[1] += ", ";
                    }
                }

                modelTable1.insertRow(modelTable1.getRowCount(), new Object[]{row[0], row[1]});
                modelTable3.insertRow(modelTable3.getRowCount(), new Object[]{row[0], row[1]}); // history
            }
            frame.pack();
            frame.setPreferredSize(new Dimension(500, 500));
        }
    }

    class SearchByDefinitionActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            dict.searchWordByDefinition(ae.getActionCommand());
            modelTable2.setRowCount(0);

            for (Map.Entry<String, Set<String>> entry : dict.getSubDictionary().entrySet()) {
                String row[] = new String[2];

                row[0] = entry.getKey();
                row[1] = "";

                for (int i = 0; i < entry.getValue().size(); i++) {
                    row[1] += (String) entry.getValue().toArray()[i];

                    if (i < entry.getValue().size() - 1) {
                        row[1] += ", ";
                    }
                }

                modelTable2.insertRow(modelTable2.getRowCount(), new Object[]{row[0], row[1]});
                modelTable3.insertRow(modelTable3.getRowCount(), new Object[]{row[0], row[1]}); // history
            }
            frame.pack();
            frame.setPreferredSize(new Dimension(500, 500));
        }
    }

    class AddButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            boolean isAdded = dict.add(textField3.getText(), textField4.getText(), false);

            if (isAdded)
                JOptionPane.showMessageDialog(frame,
                        "You just successfully added a new slang word",
                        "Successful Dialog",
                        JOptionPane.INFORMATION_MESSAGE);
            else {
                int output = JOptionPane.showConfirmDialog(frame,
                        "The slang word has already exist. Do you want to continue adding overwrites?",
                        "Comfirm Dialog",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (output == JOptionPane.YES_OPTION) {
                    dict.add(textField3.getText(), textField4.getText(), true);
                    JOptionPane.showMessageDialog(frame,
                            "You just successfully added a new slang word",
                            "Successful Dialog",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "You just canceled the operation of adding new slang word",
                            "Information Dialog",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

            textField3.setText("");
            textField4.setText("");
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

    private void createFeature1() {
        // Input part
        JPanel input = new JPanel();
        input.add(new JLabel("Input the slang word: "));

        textField1 = new JTextField("", 20);
        textField1.addActionListener(new SearchBySlangWordActionListener());
        input.add(textField1);

        // Output part
        JPanel output = new JPanel(new BorderLayout());
        modelTable1 = new DefaultTableModel();
        modelTable1.addColumn("Slang word");
        modelTable1.addColumn("Definition");

        JTable table = new JTable(modelTable1);
        table.setEnabled(false);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(5, 10, 300, 150);
        scroll.setVisible(true);

        output.add(table.getTableHeader(), BorderLayout.NORTH);
        output.add(scroll, BorderLayout.CENTER);

        // Add all to card 1
        cards[1].setLayout(new BoxLayout(cards[1], BoxLayout.Y_AXIS));
        cards[1].add(input);
        cards[1].add(output);
    }

    private void createFeature2() {
        // Input part
        JPanel input = new JPanel();
        input.add(new JLabel("Input the definition: "));

        textField2 = new JTextField("", 20);
        textField2.addActionListener(new SearchByDefinitionActionListener());
        input.add(textField2);

        // Output part
        JPanel output = new JPanel(new BorderLayout());
        modelTable2 = new DefaultTableModel();
        modelTable2.addColumn("Slang word");
        modelTable2.addColumn("Definition");

        JTable table = new JTable(modelTable2);
        table.setEnabled(false);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(5, 10, 300, 150);
        scroll.setVisible(true);

        output.add(table.getTableHeader(), BorderLayout.NORTH);
        output.add(scroll, BorderLayout.CENTER);

        // Add all to card 2
        cards[2].setLayout(new BoxLayout(cards[2], BoxLayout.Y_AXIS));
        cards[2].add(input);
        cards[2].add(output);
    }

    private void createFeature3() {
        // Output part
        JPanel output = new JPanel(new BorderLayout());
        modelTable3 = new DefaultTableModel();
        modelTable3.addColumn("Slang word");
        modelTable3.addColumn("Definition");

        JTable table = new JTable(modelTable3);
        table.setEnabled(false);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(5, 10, 300, 150);
        scroll.setVisible(true);

        output.add(table.getTableHeader(), BorderLayout.NORTH);
        output.add(scroll, BorderLayout.CENTER);

        // Add all to card 3
        cards[3].add(output);
    }

    private void createFeature4() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton buttonAdd = new JButton("Add");
        textField3 = new JTextField();
        textField4 = new JTextField();

        textField3.setMaximumSize(new Dimension(800, 24));
        textField4.setMaximumSize(new Dimension(800, 24));
        buttonAdd.addActionListener(new AddButtonActionListener());

        panel.add(new JLabel("Input the new slang word: "));
        panel.add(textField3);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(new JLabel("Input the definition of the new slang word part: "));
        panel.add(textField4);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(buttonAdd);

        cards[4].setLayout(new BoxLayout(cards[4], BoxLayout.Y_AXIS));
        cards[4].add(Box.createRigidArea(new Dimension(15, 0)));
        cards[4].add(panel);
        frame.pack();
    }

    private void createFeatureGUI(int i) {
        cards[i] = new JPanel();
        switch (i) {
            case 1:
                createFeature1();
                break;
            case 2:
                createFeature2();
                break;
            case 3:
                createFeature3();
                break;
            case 4:
                createFeature4();
                break;
            default:
                break;
        }
    }

    public void addComponentToPane(Container pane) {
        content = new JPanel(new CardLayout());
        cards = new JPanel[11];

        for (int i = 0; i < cards.length; i++) {
            createFeatureGUI(i);
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
//        frame.setPreferredSize(new Dimension(500, 500));
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
