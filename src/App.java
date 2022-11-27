import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class App {
    private final Dictionary dictionary;
    private JFrame frame;
    private JPanel content;
    private CustomTableModel tableModelHistory;
    private JPanel cards[];
    public static final int MIN_WIDTH = 700;
    public static final int MIN_HEIGHT = 500;
    public static Font SMALL_FONT, NORMAL_FONT, LARGE_FONT, HEADING_FONT;
    public static final int TEXTFIELD_HEIGH = 24;
    public static final int TEXTFIELD_WIDTH = 500;
    public static final String FEATURES[] = {"Search by slang word",
            "Search by definition",
            "Search history",
            "Add",
            "Edit",
            "Delete",
            "Reset",
            "Random",
            "Quiz 1",
            "Quiz 2"
    };
    public static final String CONSTRAINT_CARD_MENU = "MENU";
    public static final int INDEX_RESET = 6;

    public App() {
        dictionary = new Dictionary();
        cards = new JPanel[FEATURES.length + 1]; // + card of menu
        tableModelHistory = new CustomTableModel();

        SMALL_FONT = new Font("Roboto", Font.PLAIN, 18);
        NORMAL_FONT = new Font("Roboto", Font.PLAIN, 24);
        LARGE_FONT = new Font("Roboto", Font.PLAIN, 30);
        HEADING_FONT = new Font("Roboto", Font.BOLD, 36);
    }

    private void createFeatureGUI(int i) {
        switch (i) {
            case 0:
                cards[0] = new StartGUI();
                break;
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
                cards[4] = new Feature4(dictionary);
                break;
            case 5:
                cards[5] = new Feature5(dictionary);
                break;
            case 6:
                cards[6] = new Feature6(dictionary);
                break;
            case 8:
                cards[8] = new Feature8(dictionary);
                break;
            case 9:
                cards[9] = new Feature9(dictionary);
                break;
            case 10:
                cards[10] = new Feature10(dictionary);
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
                content.add(cards[i], CONSTRAINT_CARD_MENU);
            else
                content.add(cards[i], FEATURES[i - 1]);
        }

        pane.add(content);
    }

    public void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        frame = new JFrame("20127135 - Đồ án môn học #1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addComponentToPane(frame.getContentPane());
        frame.setJMenuBar(new Menu(dictionary, content));

        frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dictionary.save();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        App app = new App();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                app.createAndShowGUI();
            }
        });
    }
}