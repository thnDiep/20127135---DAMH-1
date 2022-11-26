import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {
    public CustomTableModel() {
        addColumn(App.SLANG_WORD);
        addColumn(App.DEFINITION);
    }
}
