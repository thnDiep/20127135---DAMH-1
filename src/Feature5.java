import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Feature5 extends JPanel implements ActionListener {
    private JFrame frame;
    private Dictionary dictionary;
    private JTextField slangWord, definition;

    public void actionPerformed(ActionEvent ae) {
        boolean isEdited = dictionary.edit(slangWord.getText(), definition.getText());

        if (isEdited) {
            JOptionPane.showMessageDialog(frame,
                    "You just successfully edited the slang word",
                    "Successful Dialog",
                    JOptionPane.INFORMATION_MESSAGE);

            slangWord.setText("");
            definition.setText("");
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Editing failure: The slang word does not exist",
                    "Failed Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Feature5(JFrame frame, Dictionary dictionary) {
        this.frame = frame;
        this.dictionary = dictionary;
        this.slangWord = new JTextField();
        this.definition = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton buttonEdit = new JButton("Edit");

        slangWord.setMaximumSize(new Dimension(900, 24));
        definition.setMaximumSize(new Dimension(900, 24));

        buttonEdit.addActionListener(this);

        panel.add(new JLabel("Enter the slang word you want to edit: "));
        panel.add(slangWord);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(new JLabel("Enter the definition of the slang word: "));
        panel.add(definition);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(buttonEdit);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createRigidArea(new Dimension(15, 0)));
        add(panel);
    }
}
