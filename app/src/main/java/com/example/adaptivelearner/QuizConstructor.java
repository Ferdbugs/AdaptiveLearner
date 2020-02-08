package com.example.adaptivelearner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuizConstructor {

    List<QuizQuestions> quiz = new ArrayList<>();

    public List<QuizQuestions> getCommunicationEasyQuiz(){

        quiz.add(new QuizQuestions("Data Communication deals with the ____ of signals.","Types","Transmission","Price","Colors","Transmission"));
        quiz.add(new QuizQuestions("Data communications is a subset of?","Data Science","Telecommunication","Concurrency","Networking","Telecommunication"));
        quiz.add(new QuizQuestions("Transmission media is classified as ?","guided/unguided","strong/weak","fast/slow","smooth/choppy","guided/unguided"));
        quiz.add(new QuizQuestions("With guided media the waves are guided along a ___ path.","physical","wireless","electric","electromagnetic","CA"));
        quiz.add(new QuizQuestions("Example of Guided Media:","Twisted pair","Antenna","Wi-FI","Radio","Twisted pair"));
        quiz.add(new QuizQuestions("Example of Unguided Media:","Coaxial Cable","Optical Fibre","Wi-Fi","Twisted Pair","Wi-Fi"));
        quiz.add(new QuizQuestions("How many device share link in point-to-point?","Four","Three","Five","Two","Two"));
        quiz.add(new QuizQuestions("How many device share link in multi-point?","Less than three","More than two","One","Two","More than two"));
        quiz.add(new QuizQuestions("Both stations transmit, but one at a time:","Simplex","Full Duplex","Half Duplex","Triplex","Half Duplex"));
        quiz.add(new QuizQuestions("Both stations transmit together:","Simplex","Full Duplex","Half Duplex","Triplex","Full Duplex"));

        return  quiz;
    }

}
