import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Feature4 extends JPanel implements ActionListener {
    private JFrame frame;
    private Dictionary dictionary;
    private JTextField slangWord, definition;

    public void actionPerformed(ActionEvent ae) {
        boolean isAdded = dictionary.add(slangWord.getText(), definition.getText(), null); // Check key

        if (!isAdded) {
            // Show dialog confirm
            JOptionPane optionPane = new JOptionPane("The slang word has already exist. Are you want to overwrite or duplicate it?");
            optionPane.setOptions(dictionary.getOptions());
            JDialog dialog = optionPane.createDialog(frame, "Confirm dialog");
            dialog.setVisible(true);

            isAdded = dictionary.add(slangWord.getText(), definition.getText(), (String) optionPane.getValue());
        }

        if (isAdded) {
            JOptionPane.showMessageDialog(frame,
                    "You just successfully added a new slang word",
                    "Successful Dialog",
                    JOptionPane.INFORMATION_MESSAGE);

            slangWord.setText("");
            definition.setText("");
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Adding failure. Please try again",
                    "Failed Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Feature4(JFrame frame, Dictionary dictionary) {
        this.frame = frame;
        this.dictionary = dictionary;
        this.slangWord = new JTextField();
        this.definition = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton button = new JButton("Add");

        slangWord.setMaximumSize(new Dimension(900, 24));
        definition.setMaximumSize(new Dimension(900, 24));

        button.addActionListener(this);

        panel.add(new JLabel("Enter the new slang word: "));
        panel.add(slangWord);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(new JLabel("Enter the definition of the new slang word part: "));
        panel.add(definition);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(button);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createRigidArea(new Dimension(15, 0)));
        add(panel);
    }
}
