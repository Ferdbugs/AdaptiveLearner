package com.example.adaptivelearner;

public class QuizQuestions {

    String question,correctOption,wrongOption1,wrongOption2,wrongOption3;

    public QuizQuestions(String question,String correctOption, String wrongOption1, String wrongOption2, String wrongOption3){

        this.question = question;
        this.correctOption = correctOption;
        this.wrongOption1 = wrongOption1;
        this.wrongOption2 = wrongOption2;
        this.wrongOption3 = wrongOption3;

    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setWrongOption1(String wrongOption1) {
        this.wrongOption1 = wrongOption1;
    }

    public String getWrongOption1() {
        return wrongOption1;
    }

    public void setWrongOption2(String wrongOption2) {
        this.wrongOption2 = wrongOption2;
    }

    public String getWrongOption2() {
        return wrongOption2;
    }

    public void setWrongOption3(String wrongOption3) {
        this.wrongOption3 = wrongOption3;
    }

    public String getWrongOption3() {
        return wrongOption3;
    }
}
