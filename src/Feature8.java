import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Set;

public class Feature8 extends JPanel implements ActionListener {
    private Dictionary dictionary;
    private JLabel keyLabel;
    private JLabel valueLabel;

    public void actionPerformed(ActionEvent ae) {
        String randomKey = dictionary.randomSlangWord();
        Set<String> values = dictionary.getDictionary().get(randomKey);

        String text = "";
        for (String value : values) {
            text += "- " + value + "<br>";
        }
        keyLabel.setText(randomKey.toString());
        valueLabel.setText("<html>" + text + "</html>");
    }

    public Feature8(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.keyLabel = new JLabel("", SwingConstants.CENTER);
        this.valueLabel = new JLabel("", SwingConstants.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        keyLabel.setFont(new Font("Roboto", Font.PLAIN, 64));
        keyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        valueLabel.setFont(new Font("Roboto", Font.PLAIN, 30));
        JButton button = new JButton("Random");
        button.setPreferredSize(new Dimension(0, 50));
        button.addActionListener(this);

        panel.add(keyLabel, BorderLayout.PAGE_START);
        panel.add(valueLabel, BorderLayout.CENTER);
        panel.add(button, BorderLayout.PAGE_END);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(panel);
    }
}
