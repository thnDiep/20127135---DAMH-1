import javax.swing.*;
import java.awt.*;

public class Feature3 extends JPanel {
    CustomTableModel tableModel;

    public Feature3(CustomTableModel tableModel) {
        this.tableModel = tableModel;

        // Output part
        JPanel outputPanel = new JPanel(new BorderLayout());
        CustomTable table = new CustomTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setVisible(true);

        outputPanel.add(table.getTableHeader(), BorderLayout.NORTH);
        outputPanel.add(scroll, BorderLayout.CENTER);

        // Card 3
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(outputPanel);
        add(Box.createRigidArea(new Dimension(10, 0)));
    }
}
