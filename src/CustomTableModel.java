import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {
    public static final String SLANG_WORD = "Slang word";
    public static final String DEFINITION = "Definition";
    public CustomTableModel() {
        addColumn(SLANG_WORD);
        addColumn(DEFINITION);
    }
}
