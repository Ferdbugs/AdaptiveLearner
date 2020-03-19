package com.example.adaptivelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Evaluation extends AppCompatActivity {

    boolean[] result;
    TextView score,title,difficulty;
    String topic,testDifficulty;
    Button contn;
    public Learner learner;
    float finalResult,correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        learner = Learner.get();
        score = findViewById(R.id.Score);
        contn = findViewById(R.id.Continue);
        title = findViewById(R.id.evaluationTitle);
        difficulty = findViewById(R.id.Difficulty);

        result = getIntent().getBooleanArrayExtra("Answers");
        topic = learner.getCurrentTopic();
        testDifficulty = learner.getCurrentDifficulty();

        title.setText(topic);
        difficulty.setText(testDifficulty);

        for(int i = 0; i< result.length; i++){

            if(result[i]){
                correct++;
            }
        }

        finalResult = correct/result.length;
        Log.d("finalResult", String.valueOf(finalResult));
        Log.d("correct", String.valueOf(correct));
        Log.d("length", String.valueOf(result.length));
        String Marks = correct +"/"+ result.length;
        score.setText(Marks);
        if(learner.getCompleted()==null){
            learner.setCompleted("");
        }

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

        if(finalResult<.3){
            learner.setPerformance("Poor");
        }
        else if(finalResult<.6){
            learner.setPerformance("Moderate");
        }
        else if(finalResult<.7){
            learner.setPerformance("Good");
        }
        else if(finalResult<.9){
            learner.setPerformance("VeryGood");
        }
        else{
            learner.setPerformance("Excellent");
            if(testDifficulty.equals("Easy")) {
                if(learner.getCurrentTopic().equals("Communication And Transmission Media")){
                    if((learner.getCompleted()).length()<3) {
                        learner.appendCompleted("A");
                    }
                }
                if(learner.getCurrentTopic().equals("Computer Networks")){
                    if((learner.getCompleted()).length()<3) {
                        learner.appendCompleted("D");
                    }
                }
            }
            if (testDifficulty.equals("Medium")){
                if(learner.getCurrentTopic().equals("Communication And Transmission Media")){
                    if((learner.getCompleted()).length()<3) {
                        learner.appendCompleted("B");
                    }
                }
                if(learner.getCurrentTopic().equals("Computer Networks")){
                    if((learner.getCompleted()).length()<3) {
                        learner.appendCompleted("E");
                    }
                }
            }
            if(testDifficulty.equals("Hard")){
                if(learner.getCurrentTopic().equals("Communication And Transmission Media")){
                    if((learner.getCompleted()).length()<3) {
                        learner.appendCompleted("C");
                    }
                }
                if(learner.getCurrentTopic().equals("Computer Networks")){
                    if((learner.getCompleted()).length()<3) {
                        learner.appendCompleted("F");
                    }
                }
            }
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
