import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Feature3 extends JPanel {
    DefaultTableModel tableModel;

    public Feature3(DefaultTableModel tableModel) {
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

        // Card 3
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(output);
        add(Box.createRigidArea(new Dimension(10, 0)));
    }
}
