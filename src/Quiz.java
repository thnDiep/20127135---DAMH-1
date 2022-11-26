import java.util.*;

public class Quiz {
    private final Dictionary dictionary;
    private ArrayList<String> slangWordL;
    private ArrayList<String> definitionL;
    private Map<String, ArrayList<String>> quiz;
    private int correctAnswer, wrongAnswer, numberQuestion;

    public Quiz(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.slangWordL = new ArrayList<String>();
        this.definitionL = new ArrayList<String>();
        this.quiz = new HashMap<String, ArrayList<String>>();

        for (Map.Entry<String, Set<String>> entry : this.dictionary.getDictionary().entrySet()) {
            this.slangWordL.add(entry.getKey());
            Set<String> values = entry.getValue();

            for (int i = 0; i < values.size(); i++) {
                this.definitionL.add(values.toArray()[0].toString());
            }
        }
    }

    public Map<String, ArrayList<String>> createQuizForFeature9(int numberQ){
        quiz.clear();
        numberQuestion = numberQ;
        correctAnswer = 0;
        wrongAnswer = 0;

        for (int i = 0; i < this.numberQuestion; i++) {
            Random random = new Random();

            // random question (slang word)
            String question = this.slangWordL.get(random.nextInt(this.slangWordL.size()));
            while(quiz.containsKey(question)){
                question = this.slangWordL.get(random.nextInt(this.slangWordL.size()));
            }

            // random 1 correct answer (if this slang word has many definitions)
            Set<String> correctAnswers = this.dictionary.getDictionary().get(question);
            String correctAnswer = correctAnswers.toArray()[random.nextInt(correctAnswers.size())].toString();

            // The correct answer always has index 0 in array list
            ArrayList<String> answers = new ArrayList<String>(4);
            answers.add(0, correctAnswer);

            // random other answers
            for (int j = 1; j < 4; j++) {
                String wrongAnswer = this.definitionL.get(random.nextInt(this.definitionL.size()));
                while (answers.contains(wrongAnswer)) {
                    wrongAnswer = this.definitionL.get(random.nextInt(this.definitionL.size()));
                }

                answers.add(j, wrongAnswer);
            }

            quiz.put(question, answers);
        }

        return quiz;
    }

    public Map<String, ArrayList<String>> createQuizForFeature10(int numberQ){
        quiz.clear();
        numberQuestion = numberQ;
        correctAnswer = 0;
        wrongAnswer = 0;

        for (int i = 0; i < this.numberQuestion; i++) {
            Random random = new Random();

            // random slang word
            String slangWord = this.slangWordL.get(random.nextInt(this.slangWordL.size()));

            // The correct answer always has index 0 in array list
            ArrayList<String> answers = new ArrayList<String>(4);
            answers.add(0, slangWord);

            // random other answers
            for (int j = 1; j < 4; j++) {
                String wrongAnswer = this.slangWordL.get(random.nextInt(this.slangWordL.size()));
                while (answers.contains(wrongAnswer)) {
                    wrongAnswer = this.slangWordL.get(random.nextInt(this.slangWordL.size()));
                }

                answers.add(j, wrongAnswer);
            }

            // random question
            String questions = this.dictionary.getDictionary().get(slangWord).toArray()[0].toString();

            quiz.put(questions, answers);
        }

        return quiz;
    }

    public boolean checkAnswer(String question, String answer){
        if(quiz.get(question).get(0) == answer){
            correctAnswer++;
            return true;
        }
        wrongAnswer++;
        return false;
    }

    public static ArrayList<Integer> randomNumbers(int length) {
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

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int getWrongAnswer() {
        return wrongAnswer;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }
}
