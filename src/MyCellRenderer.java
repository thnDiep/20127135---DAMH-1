import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

// References: https://community.oracle.com/tech/developers/discussion/1362611/jtable-wrapping-text-in-a-cell
public class MyCellRenderer extends JTextArea implements TableCellRenderer {
    public MyCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setFont(App.SMALL_FONT);
    }

    public Component getTableCellRendererComponent(JTable table, Object
            value, boolean isSelected, boolean hasFocus, int row, int column) {

        setText(value.toString());
        setSize(table.getColumnModel().getColumn(column).getWidth(),
                getPreferredSize().height);
        if (table.getRowHeight(row) != getPreferredSize().height) {
            table.setRowHeight(row, getPreferredSize().height);
        }
        return this;
    }
}