package com.example.adaptivelearner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuizConstructor {

    List<QuizQuestions> quiz = new ArrayList<>();

    public List<QuizQuestions> getCommunicationEasyQuiz(){

        quiz.add(new QuizQuestions("Data Communication deals with the ____ of signals.","types","transmission","price","colors","transmission"));
        quiz.add(new QuizQuestions("What is a Communication?","Hello","CA","Hello","Hello","CA"));
        quiz.add(new QuizQuestions("What is a Statistics?","CA","Hello","Hello","Hello","CA"));
        quiz.add(new QuizQuestions("What is a Oreo?","Hello","CA","Hello","Hello","CA"));
        quiz.add(new QuizQuestions("What is a Kitkat?","Hello","Hello","Hello","CA","CA"));
        quiz.add(new QuizQuestions("What is a Poland?","Hello","Hello","CA","Hello","CA"));
        quiz.add(new QuizQuestions("What is a Russia?","Hello","Hello","CA","Hello","CA"));

        return  quiz;
    }

}
