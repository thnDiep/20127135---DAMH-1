import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

public class Feature1 extends JPanel implements ActionListener {
    private Dictionary dictionary;
    private DefaultTableModel tableModel, tableModelHistory;

    public void actionPerformed(ActionEvent ae) {
        dictionary.searchWordBySlangWord(ae.getActionCommand());
        tableModel.setRowCount(0);

        for (Map.Entry<String, Set<String>> entry : dictionary.getSubDictionary().entrySet()) {
            String row[] = new String[2];

            row[0] = entry.getKey();
            row[1] = "";

            for (int i = 0; i < entry.getValue().size(); i++) {
                row[1] += (String) entry.getValue().toArray()[i];

                if (i < entry.getValue().size() - 1) {
                    row[1] += ", ";
                }
            }

            tableModel.insertRow(0, new Object[]{row[0], row[1]});
            tableModelHistory.insertRow(tableModelHistory.getRowCount(), new Object[]{row[0], row[1]}); // save history
        }
    }

    public Feature1(Dictionary dictionary, DefaultTableModel tableModelHistory) {
        this.dictionary = dictionary;
        this.tableModelHistory = tableModelHistory;
        this.tableModel = new DefaultTableModel();

        // Input part
        JPanel input = new JPanel(new FlowLayout(FlowLayout.LEFT));
        input.add(new JLabel("Input the slang word: "));

        JTextField textField = new JTextField("", 20);
        textField.addActionListener(this);
        input.add(textField);

        // Output part
        JPanel output = new JPanel(new BorderLayout());
        tableModel.addColumn("Slang word");
        tableModel.addColumn("Definition");

        JTable table = new JTable(tableModel);
        table.setEnabled(false);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(5, 10, 300, 150);
        scroll.setVisible(true);

        output.add(table.getTableHeader(), BorderLayout.NORTH);
        output.add(scroll, BorderLayout.CENTER);

        // Card 1
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(input);
        add(output);
    }
}