package com.example.adaptivelearner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Test extends AppCompatActivity {

    private ListView questions;
    private CustomAdapter customAdapter;
    private QuizConstructor quizConstructor;
    private List<QuizQuestions> quiz;


    private class CustomAdapter extends ArrayAdapter<QuizQuestions> {

        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<QuizQuestions> objects) {
            super(context, resource, objects);
        }

        private class holdView {
            TextView Question;            //Initialize textViews
            Button option1,option2,option3,option4;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            holdView holder;

            if (convertView == null) {
                holder = new holdView();
                convertView =  getLayoutInflater().inflate(R.layout.test_list_item, null);
                holder.Question = convertView.findViewById(R.id.question);
                holder.option1 = convertView.findViewById(R.id.option1);
                holder.option2 = convertView.findViewById(R.id.option2);
                holder.option3 = convertView.findViewById(R.id.option3);
                holder.option4 = convertView.findViewById(R.id.option4);

                convertView.setTag(holder);

            } else {
                holder = (Test.CustomAdapter.holdView) convertView.getTag();
            }

            QuizQuestions Question = getItem(position);
            if(Question!=null){
                holder.Question.setText(Question.getQuestion());
                holder.option1.setText(Question.getCorrectOption());
                holder.option2.setText(Question.getWrongOption1());
                holder.option3.setText(Question.getWrongOption2());
                holder.option4.setText(Question.getWrongOption3());
            }
            return convertView;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        questions = findViewById(R.id.testQuestions);
        quizConstructor = new QuizConstructor();
        quiz = quizConstructor.getEasyQuiz();

        customAdapter = new CustomAdapter(this,android.R.layout.simple_list_item_1,quiz);
        questions.setAdapter(customAdapter);

    }
}
