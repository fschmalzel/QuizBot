package com.gmail.xlifehd;

public class Player {
    private final String uid;
    private String name;
    private int totalAnswers;
    private int totalCorrectAnswers;
    private int avgTime;
    private boolean synced;

    private Player(String uid, String name, int totalAnswers, int totalCorrectAnswers, int avgTime) {
        this.uid = uid;
        this.name = name;
        this.totalAnswers = totalAnswers;
        this.totalCorrectAnswers = totalCorrectAnswers;
        this.avgTime = avgTime;
        synced = true;
    }

    public boolean updateRecords() {
        //synced = true;
        if (!synced) {

            //TODO: MySQL Statements


            return false;
        } else
            return true;
    }

    public void answered(boolean correct, int time) {
        synced = false;
        totalAnswers++;
        if (correct) {
            avgTime = (int) ((((long) avgTime * (long) totalCorrectAnswers) + time) / (long) (totalCorrectAnswers + 1));
            totalCorrectAnswers++;
        }
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public int getTotalAnswers() {
        return totalAnswers;
    }

    public int getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }

    public int getAvgTime() {
        return avgTime;
    }

    protected void finalize() {
        updateRecords();
    }

}
