import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Feature6 extends JPanel implements ActionListener {
    private final Dictionary dictionary;
    private final JTextField slangWordTextField;

    public void actionPerformed(ActionEvent ae) {
        int isConfirm = JOptionPane.showConfirmDialog(this,
                "Are you confirm the deletion of the slang word?",
                "Confirm Dialog",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if(isConfirm == JOptionPane.YES_OPTION){
            boolean isDeleted = dictionary.delete(slangWordTextField.getText());

            if (isDeleted) {
                JOptionPane.showMessageDialog(this,
                        "You just successfully deleted the slang word",
                        "Successful Dialog",
                        JOptionPane.INFORMATION_MESSAGE);

                slangWordTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Deleting failure (The slang word doesn't exist in dictionary)" +
                                "\nPlease try again!",
                        "Failed Dialog",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Feature6(Dictionary dictionary) {
        this.dictionary = dictionary;

        // TextField
        slangWordTextField = new JTextField();
        slangWordTextField.setFont(App.SMALL_FONT);
        slangWordTextField.setMaximumSize(
                new Dimension(1000, App.TEXTFIELD_HEIGH));

        // Label
        JLabel headerLabel =
                new JLabel("Delete a slang word in dictionary", SwingUtilities.CENTER);
        headerLabel.setFont(App.HEADING_FONT);

        JLabel slangWordLabel =
                new JLabel("Enter the slang word you want to delete");
        slangWordLabel.setFont(App.SMALL_FONT);

        // Button
        JButton button = new JButton("Delete");
        button.setFont(App.NORMAL_FONT);
        button.addActionListener(this);

        // Form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        formPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        formPanel.add(slangWordLabel);
        formPanel.add(slangWordTextField);

        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(button);

        // Align left (LINE_START)
        JPanel leftAlign = new JPanel();
        leftAlign.setPreferredSize(new Dimension(50, 0));

        // Card 6
        setLayout(new BorderLayout());
        add(headerLabel, BorderLayout.NORTH);
        add(leftAlign, BorderLayout.LINE_START);
        add(formPanel, BorderLayout.CENTER);
    }
}
