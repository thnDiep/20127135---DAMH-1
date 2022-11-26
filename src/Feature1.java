import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

public class Feature1 extends JPanel implements ActionListener {
    private final Dictionary dictionary;
    private final DefaultTableModel tableModelHistory;
    private final CustomTableModel tableModel;

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
        this.tableModel = new CustomTableModel();

        // Input part
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField textField = new JTextField("", 20);
        textField.addActionListener(this);

        inputPanel.add(new JLabel("Enter the slang word: "));
        inputPanel.add(textField);

        // Output part
        JPanel outputPanel = new JPanel(new BorderLayout());
        CustomTable table = new CustomTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setVisible(true);

        outputPanel.add(table.getTableHeader(), BorderLayout.NORTH);
        outputPanel.add(scroll, BorderLayout.CENTER);

        // Card 1
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(inputPanel);
        add(outputPanel);
    }
}