package com.example.adaptivelearner;

public class Learner {

    String currentTopic;
    String typeofContent;
    LearningStyle learningStyle;

    public class LearningStyle {
        long timeReading;
        long timeVisual;
        long timePractice;
        long timeTest;
        int novReading;
        int novPractice;
        int novExamples;
    }

}
