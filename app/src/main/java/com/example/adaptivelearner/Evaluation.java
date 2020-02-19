package com.example.adaptivelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Evaluation extends AppCompatActivity {

    String[] result;
    int correct;
    TextView score,recommendation,title,difficulty;
    String beginner,intermediate,expert,topic,testDifficulty;
    Button contn;
    public Learner learner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        learner = Learner.get();
        score = findViewById(R.id.Score);
        recommendation = findViewById(R.id.Recommendation);
        contn = findViewById(R.id.Continue);
        title = findViewById(R.id.title);
        difficulty = findViewById(R.id.Difficulty);

        beginner= "Your Score is below the intermediate level. It is strongly recommended you start from the basics.";
        intermediate= "Your score is average, intermediate content is recommended for you.";
        expert= "You are well versed in this course. We recommend the expert difficulty content for you to fine tune your skills";

        result = getIntent().getStringArrayExtra("Answers");
        topic = learner.getCurrentTopic();
        testDifficulty = learner.getCurrentDifficulty();

        title.setText(topic);
        difficulty.setText(testDifficulty);

        for(int i = 0; i< result.length; i++){

            if(result[i].equals("Correct")){
                correct++;
            }
        }

        String Marks = correct +"/"+ result.length;
        score.setText(Marks);

        if(testDifficulty.equals("Evaluate")){
            learner.setLearnerState("N/A");
        }
        if(testDifficulty.equals("Easy")){
            learner.setLearnerState("Beginner");
        }
        if(testDifficulty.equals("Medium")){
            learner.setLearnerState("Intermediate");
        }
        if(testDifficulty.equals("Hard")){
            learner.setLearnerState("Expert");
        }

        if(correct<4){
            recommendation.setText(beginner);
            learner.setPerformance("Poor");
        }
        else if(correct<6){
            recommendation.setText(beginner);
            learner.setPerformance("Moderate");
        }
        else if(correct<7){
            recommendation.setText(intermediate);
            learner.setPerformance("Good");
        }
        else if(correct<9){
            recommendation.setText(intermediate);
            learner.setPerformance("Very Good");
        }
        else{
            recommendation.setText(expert);
            learner.setPerformance("Excellent");
        }

        contn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Material = new Intent(Evaluation.this,LectureSlides.class);
                startActivity(Material);
            }
        });

    }
}
