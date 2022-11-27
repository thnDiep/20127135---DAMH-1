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
    private JButton startBtn, nextButton;
    private JLabel slangWordLabel, numQuestionLabel, numCorrectLabel, numWrongLabel;
    ButtonGroup answersGroup;
    private JRadioButton rBtnA, rBtnB, rBtnC, rBtnD;

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startBtn) {
            startQuiz();
        } else if (ae.getSource() == nextButton) {
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
            nextButton.setEnabled(false);

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
        else {
            if(rBtnA.isSelected() || rBtnB.isSelected() || rBtnC.isSelected() || rBtnD.isSelected())
                nextButton.setEnabled(true);
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
        // Button
        startBtn = new JButton("START QUIZ");
        startBtn.setFont(App.LARGE_FONT);
        startBtn.setPreferredSize(new Dimension(250, 60));
        startBtn.setMaximumSize(new Dimension(250, 60));
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.addActionListener(this);

        // Label
        JLabel label = new JLabel("The QUIZ displays 1 random slang word" +
                "\n with 4 answers (definition) for you chooses");
        label.setFont(App.SMALL_FONT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Card 9 (1)
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(startBtn);
        pane.add(Box.createRigidArea(new Dimension(0, 20)));
        pane.add(label);
    }

    public void createQuizGUI(JPanel pane) {
        // Question (PAGE_START)
        JLabel questionLabel = new JLabel("Select definition of ");
        slangWordLabel = new JLabel();

        questionLabel.setFont(App.LARGE_FONT);
        slangWordLabel.setFont(App.HEADING_FONT);

        JPanel slangWordPanel = new JPanel();
        slangWordPanel.add(slangWordLabel);

        JScrollPane scrollPane = new JScrollPane(slangWordPanel,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(0, 75));

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.add(questionLabel);
        questionPanel.add(scrollPane);

        // Answer (CENTER - 1)
        JLabel answerLabel = new JLabel("The answer: ");
        answerLabel.setFont(App.LARGE_FONT);

        rBtnA = new JRadioButton();
        rBtnB = new JRadioButton();
        rBtnC = new JRadioButton();
        rBtnD = new JRadioButton();

        rBtnA.setFont(App.SMALL_FONT);
        rBtnB.setFont(App.SMALL_FONT);
        rBtnC.setFont(App.SMALL_FONT);
        rBtnD.setFont(App.SMALL_FONT);

        rBtnA.addActionListener(this);
        rBtnB.addActionListener(this);
        rBtnC.addActionListener(this);
        rBtnD.addActionListener(this);

        answersGroup = new ButtonGroup();
        answersGroup.add(rBtnA);
        answersGroup.add(rBtnB);
        answersGroup.add(rBtnC);
        answersGroup.add(rBtnD);

        // Button (CENTER - 2)
        nextButton = new JButton("Next");
        nextButton.setFont(App.LARGE_FONT);
        nextButton.addActionListener(this);
        nextButton.setEnabled(false);

        // CENTER
        JPanel answerPanel = new JPanel();
        answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));

        answerPanel.add(answerLabel);

        answerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        answerPanel.add(rBtnA);

        answerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        answerPanel.add(rBtnB);

        answerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        answerPanel.add(rBtnC);

        answerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        answerPanel.add(rBtnD);

        answerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        answerPanel.add(nextButton);

        // Align left (LINE_START)
        JPanel leftAlign = new JPanel();
        leftAlign.setPreferredSize(new Dimension(50, 0));

        // Status (PAGE_END)
        numQuestionLabel = new JLabel();
        numCorrectLabel = new JLabel();
        numWrongLabel = new JLabel();
        numQuestionLabel.setFont(App.SMALL_FONT);
        numCorrectLabel.setFont(App.SMALL_FONT);
        numWrongLabel.setFont(App.SMALL_FONT);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        statusPanel.setPreferredSize(new Dimension(0, 50));

        statusPanel.add(Box.createHorizontalGlue());
        statusPanel.add(numQuestionLabel);
        statusPanel.add(Box.createRigidArea(new Dimension(65, 0)));
        statusPanel.add(numCorrectLabel);
        statusPanel.add(Box.createRigidArea(new Dimension(65, 0)));
        statusPanel.add(numWrongLabel);
        statusPanel.add(Box.createHorizontalGlue());

        // Card 9 (2)
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
            ArrayList<Integer> randomNumbers = Quiz.randomNumbers(4);
            rBtnA.setText(answer.get(randomNumbers.get(0)));
            rBtnB.setText(answer.get(randomNumbers.get(1)));
            rBtnC.setText(answer.get(randomNumbers.get(2)));
            rBtnD.setText(answer.get(randomNumbers.get(3)));

            numQuestionLabel.setText("Question:    " + (index + 1) + "/" + myQuiz.getNumberQuestion());
        }

        numCorrectLabel.setText("Correct answer:   " + myQuiz.getCorrectAnswer());
        numWrongLabel.setText("Wrong answer:   " + myQuiz.getWrongAnswer());
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
