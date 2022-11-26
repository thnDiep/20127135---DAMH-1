import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Feature4 extends JPanel implements ActionListener {
    private final Dictionary dictionary;
    private final JTextField slangWordTextField, definitionTextField;

    public void actionPerformed(ActionEvent ae) {
        boolean isAdded = dictionary.add(slangWordTextField.getText(), definitionTextField.getText(), null); // Check key

        // if the slang word has existed
        if (!isAdded) {
            // Show dialog confirm
            JOptionPane optionPane = new JOptionPane("The slang word has already exist. Are you want to overwrite or duplicate it?");
            optionPane.setOptions(dictionary.getOptions());
            JDialog dialog = optionPane.createDialog(this, "Confirm dialog");
            dialog.setVisible(true);

            isAdded = dictionary.add(slangWordTextField.getText(), definitionTextField.getText(), (String)optionPane.getValue());
        }

        if (isAdded) {
            JOptionPane.showMessageDialog(this,
                    "You just successfully added a new slang word",
                    "Successful Dialog",
                    JOptionPane.INFORMATION_MESSAGE);

            slangWordTextField.setText("");
            definitionTextField.setText("");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Adding failure (The operation is cancelled)" +
                            "\nPlease try again",
                    "Failed Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Feature4(Dictionary dictionary) {
        this.dictionary = dictionary;

        // TextField
        slangWordTextField = new JTextField();
        definitionTextField = new JTextField();
        slangWordTextField.setMaximumSize(
                new Dimension(App.MIN_WIDTH, App.TEXTFIELD_HEIGH));
        definitionTextField.setMaximumSize(
                new Dimension(App.MIN_WIDTH, App.TEXTFIELD_HEIGH));

        // Label
        JLabel headerLabel =
                new JLabel("Add the new slang word to dictionary", SwingUtilities.CENTER);
        JLabel slangWordLabel =
                new JLabel("Enter the new slang word");
        JLabel definitionLabel =
                new JLabel("Enter the definition of the new slang word part");
        headerLabel.setFont(App.HEADING_FONT);
        slangWordLabel.setFont(App.SMALL_FONT);
        definitionLabel.setFont(App.SMALL_FONT);

        // Button
        JButton button = new JButton("Add");
        button.setFont(App.NORMAL_FONT);
        button.addActionListener(this);

        // Form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        formPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        formPanel.add(slangWordLabel);
        formPanel.add(slangWordTextField);

        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(definitionLabel);
        formPanel.add(definitionTextField);

        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(button);

        // Align left (LINE_START)
        JPanel leftAlign = new JPanel();
        leftAlign.setPreferredSize(new Dimension(50, 0));

        // Card 4
        setLayout(new BorderLayout());
        add(headerLabel, BorderLayout.PAGE_START);
        add(leftAlign, BorderLayout.LINE_START);
        add(formPanel, BorderLayout.CENTER);
    }
}
