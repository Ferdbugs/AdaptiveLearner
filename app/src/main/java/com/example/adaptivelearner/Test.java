package com.example.adaptivelearner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adaptivelearner.Provider.UserDB;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Test extends AppCompatActivity {

    private ListView questions;
    private CustomAdapter customAdapter;
    private QuizConstructor quizConstructor;
    private List<QuizQuestions> quiz;
    private String topic;
    private Learner learner,prevLearner;
    private TextView title;

    private class CustomAdapter extends ArrayAdapter<QuizQuestions> {

        boolean[] clickedFlag;
        private String[] Answer;
        int counter;
        public Learner learner;


        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<QuizQuestions> objects) {
            super(context, resource, objects);
            clickedFlag = new boolean[objects.size()];
            Answer = new String[objects.size()];
        }

        private class holdView {
            TextView Question;            //Initialize textViews
            Button option1,option2,option3,option4;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            final holdView holder;
            final String Correct = "Correct!";
            final String Wrong = "Wrong!";

            final QuizQuestions Question = getItem(position);

            final String cOption = Question.getCorrectOption();
            String wOption1 = Question.getOption1();
            String wOption2 = Question.getOption2();
            String wOption3 = Question.getOption3();
            String wOption4 = Question.getOption4();
            final String difficulty = Question.difficulty;

            holder = new holdView();
            convertView =  getLayoutInflater().inflate(R.layout.test_list_item, null);
            holder.Question = convertView.findViewById(R.id.question);
            holder.option1 = convertView.findViewById(R.id.option1);
            holder.option2 = convertView.findViewById(R.id.option2);
            holder.option3 = convertView.findViewById(R.id.option3);
            holder.option4 = convertView.findViewById(R.id.option4);

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;

                    if(button.getText()==cOption){
                        button.setText(Correct);
                        button.setBackgroundColor(getResources().getColor(R.color.lime));
                        Answer[position]= "Correct";
                    }
                    else{
                        button.setText(Wrong);
                        Answer[position]= "Wrong";
                        button.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                    clickedFlag[position]=true;
                    holder.option1.setEnabled(false);
                    holder.option2.setEnabled(false);
                    holder.option3.setEnabled(false);
                    holder.option4.setEnabled(false);
                    counter++;
                    if(counter==Answer.length){
                        learner = Learner.get();
                        Intent Evaluate = new Intent(Test.this,Evaluation.class);
                        Evaluate.putExtra("Answers", Answer);
                        learner.setCurrentDifficulty(difficulty);
                        learner.setCurrentTopic(topic);
                        startActivity(Evaluate);
                    }

                }
            };

            holder.option1.setOnClickListener(listener);
            holder.option2.setOnClickListener(listener);
            holder.option3.setOnClickListener(listener);
            holder.option4.setOnClickListener(listener);


            if(Question!=null){
                holder.Question.setText(Question.getQuestion());
                holder.option1.setText(wOption1);
                holder.option2.setText(wOption2);
                holder.option3.setText(wOption3);
                holder.option4.setText(wOption4);

            }

            if(clickedFlag[position]){
                holder.option1.setEnabled(false);
                holder.option2.setEnabled(false);
                holder.option3.setEnabled(false);
                holder.option4.setEnabled(false);
            }
            return convertView;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        learner = Learner.get();
        prevLearner = new Learner();
        questions = findViewById(R.id.testQuestions);
        quizConstructor = new QuizConstructor();
        topic = learner.getCurrentTopic();
        title = findViewById(R.id.testTitle);

        title.setText(learner.getCurrentTopic());

        if(topic!=null) {
            if (topic.equals("Communication And Transmission Media")) {
                prevLearner = UserDB.getInstance(getApplicationContext()).getLearner(0);
                if(prevLearner.getCurrentDifficulty()==null){

                    quiz = quizConstructor.getCommunicationEvaluateQuiz();
                    Collections.shuffle(quiz);
                }
                else if(prevLearner.getCurrentDifficulty().equals("Easy")){

                    quiz = quizConstructor.getCommunicationEasyQuiz();
                    Collections.shuffle(quiz);
                }
                else if(prevLearner.getCurrentDifficulty().equals("Medium")){

                    quiz = quizConstructor.getCommunicationMediumQuiz();
                    Collections.shuffle(quiz);
                }
                else
                {
                    quiz = quizConstructor.getCommunicationHardQuiz();
                    Collections.shuffle(quiz);
                }
            }
            if (topic.equals("Computer Networks")) {
                prevLearner = UserDB.getInstance(getApplicationContext()).getLearner(1);
                if(prevLearner.getCurrentDifficulty()==null){

                    quiz = quizConstructor.getNetworkEvaluateQuiz();
                    Collections.shuffle(quiz);
                }
                else if(prevLearner.getCurrentDifficulty().equals("Easy")){

                    quiz = quizConstructor.getNetworksEasyQuiz();
                    Collections.shuffle(quiz);
                }
                else if(prevLearner.getCurrentDifficulty().equals("Medium")){

                    quiz = quizConstructor.getNetworkMediumQuiz();
                    Collections.shuffle(quiz);
                }
                else
                {
                    quiz = quizConstructor.getNetworkHardQuiz();
                    Collections.shuffle(quiz);
                }
            }
        }

        customAdapter = new CustomAdapter(this,android.R.layout.simple_list_item_1,quiz);
        questions.setAdapter(customAdapter);
    }


}
