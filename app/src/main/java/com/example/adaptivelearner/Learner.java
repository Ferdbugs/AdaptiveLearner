package com.example.adaptivelearner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Learner {

    private static Learner mLearner;

    public static Learner get(){
        if(mLearner == null){
            mLearner = new Learner();
        }
        return mLearner;
    }
    private String currentTopic,currentDifficulty,performance,learnerState;
    private long date;
    private int id;

    public void setDate(long date) {
        long trim = date % (24 * 3600 * 1000);
        this.date = date - trim;
    }

    public long getDate() {
        return date;
    }

    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(new Date(date));
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCurrentTopic(String currentTopic) {
        this.currentTopic = currentTopic;
    }

    public String getCurrentTopic() {
        return currentTopic;
    }

    public void setCurrentDifficulty(String currentDifficulty) {
        this.currentDifficulty = currentDifficulty;
    }

    public String getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getPerformance() {
        return performance;
    }

    public void setLearnerState(String learnerState) {
        this.learnerState = learnerState;
    }

    public String getLearnerState() {
        return learnerState;
    }
}
