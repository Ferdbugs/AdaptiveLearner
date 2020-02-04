package com.example.adaptivelearner;

public class Learner {

    String currentTopic;
    String currentDifficulty;
    String typeofContent;
    LearningStyle learningStyle;

    public class LearningStyle {
        long timePractice;
        long timeTest;
        int novReading;
        int novPractice;
        int novExamples;
    }

}
