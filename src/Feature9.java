import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Feature9 extends JPanel implements ActionListener {
    private final Quiz myQuiz;
    private Map<String, ArrayList<String>> quiz;
    private String question;
    private ArrayList<String> answer;
    private int index;

    private JPanel quizPanel, startPanel;
    private JButton startBtn;
    private JLabel slangWordLabel, numQuestionLabel, numCorrectLabel, numWrongLabel;
    ButtonGroup answersGroup;
    private JRadioButton rBtnA, rBtnB, rBtnC, rBtnD;

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startBtn) {
            startQuiz();
        } else {
            index++;
            if (rBtnA.isSelected()) {
                myQuiz.checkAnswer(question, rBtnA.getText());
            }
            if (rBtnB.isSelected()) {
                myQuiz.checkAnswer(question, rBtnB.getText());
            }
            if (rBtnC.isSelected()) {
                myQuiz.checkAnswer(question, rBtnC.getText());
            }
            if (rBtnD.isSelected()) {
                myQuiz.checkAnswer(question, rBtnD.getText());
            }
            updateQuestion();

            // Reset new quiz
            if (index >= myQuiz.getNumberQuestion()) {
                int result = JOptionPane.showConfirmDialog(this,
                        "Congratulations!!!!" +
                                "\nYou just completed QUIZ GAME with "
                                + myQuiz.getCorrectAnswer() + "/"
                                + myQuiz.getNumberQuestion() + " score. " +
                                "\nDo you want to start new QUIZ GAME?",
                        "Finish QUIZ GAME",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    startQuiz();
                } else {
                    exitQuiz();
                }
            }
        }
    }

    public Feature9(Dictionary dictionary) {
        myQuiz = new Quiz(dictionary);
        quiz = myQuiz.createQuizForFeature9(10);

        startPanel = new JPanel();
        createStartGUI(startPanel);

        quizPanel = new JPanel();
        createQuizGUI(quizPanel);
        quizPanel.setVisible(false);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());
        add(startPanel);
        add(quizPanel);
        add(Box.createVerticalGlue());
    }

    public void createStartGUI(JPanel pane){
        startBtn = new JButton("START QUIZ");
        startBtn.addActionListener(this);
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.setPreferredSize(new Dimension(150, 50));
        startBtn.setMaximumSize(new Dimension(150, 50));

        JLabel label = new JLabel("The QUIZ displays 1 random slang word\n with 4 answers for you chooses");
        label.setFont(new Font("Roboto", Font.PLAIN, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(startBtn);
        pane.add(Box.createRigidArea(new Dimension(0, 20)));
        pane.add(label);
    }

    public void createQuizGUI(JPanel pane) {
        // Question (PAGE_START)
        slangWordLabel = new JLabel("", SwingConstants.CENTER);
        slangWordLabel.setFont(new Font("Roboto", Font.BOLD, 40));
        slangWordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("Select definition of ");
        label.setFont(new Font("Roboto", Font.PLAIN, 30));

        JPanel questionPanel = new JPanel(new FlowLayout());
        questionPanel.add(label);
        questionPanel.add(slangWordLabel);

        // Answer (CENTER - 1)
        rBtnA = new JRadioButton();
        rBtnB = new JRadioButton();
        rBtnC = new JRadioButton();
        rBtnD = new JRadioButton();

        answersGroup = new ButtonGroup();
        answersGroup.add(rBtnA);
        answersGroup.add(rBtnB);
        answersGroup.add(rBtnC);
        answersGroup.add(rBtnD);

        // Button (CENTER - 2)
        JButton button = new JButton("Next");
        button.setPreferredSize(new Dimension(0, 50));
        button.addActionListener(this);

        // CENTER
        JPanel answerPanel = new JPanel();
        answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));
        answerPanel.add(rBtnA);
        answerPanel.add(rBtnB);
        answerPanel.add(rBtnC);
        answerPanel.add(rBtnD);
        answerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        answerPanel.add(button);

        // Align left (LINE_START)
        JPanel leftAlign = new JPanel();
        leftAlign.setPreferredSize(new Dimension(10, 0));

        // Status (PAGE_END)
        numQuestionLabel = new JLabel();
        numCorrectLabel = new JLabel();
        numWrongLabel = new JLabel();

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        statusPanel.setPreferredSize(new Dimension(0, 50));

        statusPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        statusPanel.add(numQuestionLabel);
        statusPanel.add(Box.createRigidArea(new Dimension(65, 0)));
        statusPanel.add(numCorrectLabel);
        statusPanel.add(Box.createRigidArea(new Dimension(65, 0)));
        statusPanel.add(numWrongLabel);

        // Container
        pane.setLayout(new BorderLayout());
        pane.add(questionPanel, BorderLayout.PAGE_START);
        pane.add(leftAlign, BorderLayout.LINE_START);
        pane.add(answerPanel, BorderLayout.CENTER);
        pane.add(statusPanel, BorderLayout.PAGE_END);
    }

    public void updateQuestion() {
        if (index < myQuiz.getNumberQuestion()) {
            answersGroup.clearSelection();

            question = quiz.keySet().toArray()[index].toString();
            answer = quiz.get(question);

            slangWordLabel.setText(question);
            ArrayList<Integer> randomNumbers = randomNumbers(4);
            rBtnA.setText(answer.get(randomNumbers.get(0)));
            rBtnB.setText(answer.get(randomNumbers.get(1)));
            rBtnC.setText(answer.get(randomNumbers.get(2)));
            rBtnD.setText(answer.get(randomNumbers.get(3)));

            numQuestionLabel.setText("Question:    " + (index + 1) + "/" + myQuiz.getNumberQuestion());
        }

        numCorrectLabel.setText("Correct answer:   " + myQuiz.getCorrectAnswer());
        numWrongLabel.setText("Wrong answer:   " + myQuiz.getWrongAnswer());
    }

    public ArrayList<Integer> randomNumbers(int length) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        while (numbers.size() < length) {
            int randomNumber = random.nextInt(length);
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }

        return numbers;
    }

    public void startQuiz() {
        startPanel.setVisible(false);
        quizPanel.setVisible(true);
        index = 0;
        quiz = myQuiz.createQuizForFeature9(10);
        updateQuestion();
    }

    public void exitQuiz() {
        startPanel.setVisible(true);
        quizPanel.setVisible(false);
    }
}
