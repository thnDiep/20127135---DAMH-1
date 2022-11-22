import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

public class App {
    private Dictionary dictionary;
    private JFrame frame;
    private JPanel content;
    private DefaultTableModel tableModelHistory;
    private JPanel cards[];

    public final String features[] = {"1. Search by slang word",
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
        dictionary = new Dictionary();
        cards = new JPanel[features.length + 1];
        tableModelHistory = new DefaultTableModel();
    }

    private void createFeatureGUI(int i) {
        switch (i) {
            case 1:
                cards[1] = new Feature1(dictionary, tableModelHistory);
                break;
            case 2:
                cards[2] = new Feature2(dictionary, tableModelHistory);
                break;
            case 3:
                cards[3] = new Feature3(tableModelHistory);
                break;
            case 4:
                cards[4] = new Feature4(frame, dictionary);
                break;
            default:
                cards[i] = new JPanel();
                break;
        }
    }

    public void addComponentToPane(Container pane) {
        content = new JPanel(new CardLayout());

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

        this.addComponentToPane(frame.getContentPane());
        frame.setJMenuBar(new Menu(content, features));

        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws IOException {
        App app = new App();

        app.dictionary.readFile("slang.txt");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                app.createAndShowGUI();
            }
        });
    }
}