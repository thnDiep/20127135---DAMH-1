import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Feature5 extends JPanel implements ActionListener {
    private final Dictionary dictionary;
    private final JTextField slangWordTextField, definitionTextField;

    public void actionPerformed(ActionEvent ae) {
        boolean isEdited = dictionary.edit(slangWordTextField.getText(), definitionTextField.getText());

        if (isEdited) {
            JOptionPane.showMessageDialog(this,
                    "You just successfully edited the slang word",
                    "Successful Dialog",
                    JOptionPane.INFORMATION_MESSAGE);

            slangWordTextField.setText("");
            definitionTextField.setText("");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Editing failure (The slang word doesn't exist in dictionary)" +
                            "\nPlease try again",
                    "Failed Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Feature5(Dictionary dictionary) {
        this.dictionary = dictionary;

        // TextField
        // TextField
        slangWordTextField = new JTextField();
        slangWordTextField.setFont(App.SMALL_FONT);
        slangWordTextField.setMaximumSize(
                new Dimension(1000, App.TEXTFIELD_HEIGH));

        definitionTextField = new JTextField();
        definitionTextField.setFont(App.SMALL_FONT);
        definitionTextField.setMaximumSize(
                new Dimension(1000, App.TEXTFIELD_HEIGH));

        // Label
        JLabel headerLabel =
                new JLabel("Edit a slang word in dictionary", SwingUtilities.CENTER);
        headerLabel.setFont(App.HEADING_FONT);

        JLabel slangWordLabel =
                new JLabel("Enter the slang word you want to edit");
        slangWordLabel.setFont(App.SMALL_FONT);

        JLabel definitionLabel =
                new JLabel("Enter the definition of the slang word");
        definitionLabel.setFont(App.SMALL_FONT);

        // Button
        JButton button = new JButton("Edit");
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

        // Card 5
        setLayout(new BorderLayout());
        add(headerLabel, BorderLayout.NORTH);
        add(leftAlign, BorderLayout.LINE_START);
        add(formPanel, BorderLayout.CENTER);
    }
}
