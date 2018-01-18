package com.gmail.xlifehd;

public class Question {
    final private int points;
    final private String question;
    final private String[] answers;
    final private String category;
    final private int id;

    private Question(int points, String question, String answers, String category, int id) {
        this.points = points;
        this.question = question;
        this.answers = convertAnswersString(answers);
        this.category = category;
        this.id = id;
    }

    public Question randomQuestion() {



        return null;
    }

    private String[] convertAnswersString(String answers) {
        //TODO: Implement convertAnswersString()
        return null;
    }

    public boolean isCorrect(String answer) {
        //TODO: Implement isCorrect()
        return false;
    }



}
