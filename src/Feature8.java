import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Set;

public class Feature8 extends JPanel implements ActionListener, ItemListener {
    private final Dictionary dictionary;
    private final JLabel keyLabel;
    private final JTextArea valueArea;
    private final JButton showBtn, randomBtn;
    private final JCheckBox checkBox;
    JScrollPane scrollValueArea;

    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == 1)
            showDefinition();
        else
            hideDefinition();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == showBtn) {
            showDefinition();
        } else if (ae.getSource() == randomBtn) {
            if(!checkBox.isSelected()){
                hideDefinition();
            }
            String randomKey = dictionary.randomSlangWord();
            Set<String> values = dictionary.getDictionary().get(randomKey);

            valueArea.setText("");
            for (String value : values) {
                valueArea.append("- " + value + "\n");
            }

            keyLabel.setText(randomKey);
        }
    }

    public Feature8(Dictionary dictionary) {
        this.dictionary = dictionary;

        // Label
        keyLabel = new JLabel("");
        JLabel slangWordLabel = new JLabel("Random slang word: ");
        JLabel definitionLabel = new JLabel("Definitions");

        keyLabel.setFont(App.HEADING_FONT);
        slangWordLabel.setFont(App.LARGE_FONT);
        definitionLabel.setFont(App.LARGE_FONT);

        // Text area
        valueArea = new JTextArea();
        valueArea.setFont(App.NORMAL_FONT);
        scrollValueArea = new JScrollPane(valueArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollValueArea.setVisible(false);

        // Buttons
        randomBtn = new JButton("Random");
        randomBtn.setFont(App.LARGE_FONT);
        randomBtn.addActionListener(this);

        showBtn = new JButton("Show definitions");
        showBtn.setFont(App.LARGE_FONT);
        showBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        showBtn.addActionListener(this);

        // Check box
        checkBox = new JCheckBox("Auto show definitions");
        checkBox.setFont(App.SMALL_FONT);
        checkBox.addItemListener(this);

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
        centerPanel.add(showBtn);
        centerPanel.add(scrollValueArea);

        // Line start panel
        JPanel lineStartPanel = new JPanel();
        lineStartPanel.setLayout(new BoxLayout(lineStartPanel, BoxLayout.X_AXIS));
        lineStartPanel.setPreferredSize(new Dimension(200, 0));
        lineStartPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        lineStartPanel.add(Box.createHorizontalGlue());
        lineStartPanel.add(definitionLabel);
        lineStartPanel.add(Box.createHorizontalGlue());

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setPreferredSize(new Dimension(0, 100));

        bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        bottomPanel.add(checkBox);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(randomBtn);
        bottomPanel.add(Box.createHorizontalGlue());

        // Container panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(headerPanel, BorderLayout.PAGE_START);
        panel.add(lineStartPanel, BorderLayout.LINE_START);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.PAGE_END);

        // Card 8
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(panel);
    }

    private void showDefinition(){
        showBtn.setVisible(false);
        scrollValueArea.setVisible(true);
    }

    private void hideDefinition(){
        showBtn.setVisible(true);
        scrollValueArea.setVisible(false);
    }
}
