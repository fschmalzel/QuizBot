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

    public static Question randomQuestion() {
        //TODO: Get random Question from Database


        return null;
    }

    private static String[] convertAnswersString(String answers) {
        //TODO: Implement convertAnswersString()
        return null;
    }

    public boolean isCorrect(String answer) {
        //TODO: Implement isCorrect()
        return false;
    }

    public int getPoints() {
        return points;
    }

    public String getQuestion() {
        return question;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }
}
