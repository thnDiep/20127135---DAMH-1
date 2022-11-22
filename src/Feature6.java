import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Feature6 extends JPanel implements ActionListener {
    private JFrame frame;
    private Dictionary dictionary;
    private JTextField slangWord;

    public void actionPerformed(ActionEvent ae) {
        int isConfirm = JOptionPane.showConfirmDialog(frame,
                "Are you confirm the deletion of the slang word?",
                "Confirm Dialog",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if(isConfirm == JOptionPane.YES_OPTION){
            boolean isEdited = dictionary.delete(slangWord.getText());

            if (isEdited) {
                JOptionPane.showMessageDialog(frame,
                        "You just successfully deleted the slang word",
                        "Successful Dialog",
                        JOptionPane.INFORMATION_MESSAGE);

                slangWord.setText("");
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Deleting failure: The slang word does not exist",
                        "Failed Dialog",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Feature6(JFrame frame, Dictionary dictionary) {
        this.frame = frame;
        this.dictionary = dictionary;
        this.slangWord = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton button = new JButton("Delete");

        slangWord.setMaximumSize(new Dimension(900, 24));

        button.addActionListener(this);

        panel.add(new JLabel("Enter the slang word you want to delete: "));
        panel.add(slangWord);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(button);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createRigidArea(new Dimension(15, 0)));
        add(panel);
    }
}
