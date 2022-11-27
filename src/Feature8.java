import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class Feature8 extends JPanel implements ActionListener {
    private final Dictionary dictionary;
    private final JLabel keyLabel, valueLabel;

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

        // Label
        keyLabel = new JLabel("");
        valueLabel = new JLabel("");
        JLabel slangWordLabel = new JLabel("Random slang word: ");
        JLabel definitionLabel = new JLabel("Definitions");

        keyLabel.setFont(App.HEADING_FONT);
        valueLabel.setFont(App.LARGE_FONT);
        slangWordLabel.setFont(App.LARGE_FONT);
        definitionLabel.setFont(App.HEADING_FONT);

        // Button
        JButton button = new JButton("Random");
        button.setFont(App.LARGE_FONT);
        button.addActionListener(this);

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setPreferredSize(new Dimension(0, 100));

        headerPanel.add(Box.createVerticalGlue());
        headerPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        headerPanel.add(slangWordLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        headerPanel.add(keyLabel);
        headerPanel.add(Box.createVerticalGlue());

        // Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        centerPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        centerPanel.add(valueLabel);

        JScrollPane scrollCenter = new JScrollPane(centerPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Line start panel
        JPanel lineStartPanel = new JPanel();
        lineStartPanel.setLayout(new BoxLayout(lineStartPanel, BoxLayout.X_AXIS));
        lineStartPanel.setPreferredSize(new Dimension(280, 0));
        lineStartPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        lineStartPanel.add(Box.createHorizontalGlue());
        lineStartPanel.add(definitionLabel);
        lineStartPanel.add(Box.createHorizontalGlue());

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setPreferredSize(new Dimension(0, 100));

        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(button);
        bottomPanel.add(Box.createHorizontalGlue());

        // Container panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(headerPanel, BorderLayout.PAGE_START);
        panel.add(lineStartPanel, BorderLayout.LINE_START);
        panel.add(scrollCenter, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.PAGE_END);

        // Card 8
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(panel);
    }
}
