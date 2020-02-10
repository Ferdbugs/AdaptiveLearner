package com.example.adaptivelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class Evaluation extends AppCompatActivity {

    String[] Result;
    int correct;
    TextView score,recommendation;
    String beginner,intermediate,expert;
    Button contn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        score = findViewById(R.id.Score);
        recommendation = findViewById(R.id.Recommendation);
        contn = findViewById(R.id.Continue);

        beginner= "Your Score is below the intermediate level. It is strongly recommended you start from the basics.";
        intermediate= "Your score is average, intermediate content is recommended for you.";
        expert= "You are well versed in this course. We recommend the expert difficulty content for you to fine tune your skills";

        Result = getIntent().getStringArrayExtra("Answers");

        for(int i=0;i<Result.length;i++){

            if(Result[i].equals("Correct")){
                correct++;
            }
        }

        String Marks = correct +"/"+ Result.length;
        score.setText(Marks);

        if(correct<5){
            recommendation.setText(beginner);
        }
        else if(correct<8){
            recommendation.setText(intermediate);
        }
        else{
            recommendation.setText(expert);
        }

        contn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
