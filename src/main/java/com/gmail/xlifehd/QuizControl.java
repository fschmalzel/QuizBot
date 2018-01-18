package com.gmail.xlifehd;

import java.util.HashSet;

public class QuizControl {
    private static GAME_STATE state;
    private static HashSet<Player> players;
    private static Question curQuest;

    public static void init() {
        Main.getQuery().getApi().sendChannelMessage("Quizbot aktiviert!");
    }

    public static void askNewQuestion() {

        Question tmp = curQuest;
        do {
            curQuest = Question.randomQuestion();
        } while (curQuest.getId() == tmp.getId());

    }


}