import javax.swing.*;

public class CustomTable extends JTable {
    public CustomTable(CustomTableModel cTableModel) {
        setModel(cTableModel);
        setEnabled(false);
        getColumnModel().getColumn(0).setPreferredWidth(App.WIDTH_COLUMN_SLANG_WORD);
        getColumnModel().getColumn(0).setMaxWidth(App.MAX_WIDTH_COLUMN_SLANG_WORD);
    }
}
