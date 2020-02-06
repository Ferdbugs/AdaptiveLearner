package com.example.adaptivelearner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuizConstructor {

    List<QuizQuestions> quiz = new ArrayList<>();

    public List<QuizQuestions> getEasyQuiz(){

        quiz.add(new QuizQuestions("What is a Network?","Hello","Hello","Hello","Hello"));
        quiz.add(new QuizQuestions("What is a Communication?","Hello","Hello","Hello","Hello"));
        quiz.add(new QuizQuestions("What is a Statistics?","Hello","Hello","Hello","Hello"));
        quiz.add(new QuizQuestions("What is a Oreo?","Hello","Hello","Hello","Hello"));
        quiz.add(new QuizQuestions("What is a Kitkat?","Hello","Hello","Hello","Hello"));
        quiz.add(new QuizQuestions("What is a Poland?","Hello","Hello","Hello","Hello"));
        quiz.add(new QuizQuestions("What is a Russia?","Hello","Hello","Hello","Hello"));

        return  quiz;
    }

}
