package com.example.adaptivelearner;

public class Learner {

    String currentTopic;
    String currentDifficulty;
    String performance;
    String learnerState;

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
