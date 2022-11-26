import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

public class Feature2 extends JPanel implements ActionListener {
    private Dictionary dictionary;
    private CustomTableModel tableModel, tableModelHistory;

    public void actionPerformed(ActionEvent ae) {
        dictionary.searchWordByDefinition(ae.getActionCommand());
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

    public Feature2(Dictionary dictionary, CustomTableModel tableModelHistory) {
        this.dictionary = dictionary;
        this.tableModelHistory = tableModelHistory;
        this.tableModel = new CustomTableModel();

        // Input part
        JLabel label = new JLabel("Enter the definition: ");
        label.setFont(App.SMALL_FONT);
        JTextField textField = new JTextField("");
        textField.setPreferredSize(new Dimension(App.TEXTFIELD_WIDTH, App.TEXTFIELD_HEIGH));
        textField.addActionListener(this);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(label);
        inputPanel.add(textField);

        // Output part
        CustomTable table = new CustomTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setVisible(true);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.add(table.getTableHeader(), BorderLayout.NORTH);
        outputPanel.add(scroll, BorderLayout.CENTER);

        // Card 2
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(inputPanel);
        add(outputPanel);
    }
}