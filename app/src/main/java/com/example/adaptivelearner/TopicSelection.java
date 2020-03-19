package com.example.adaptivelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class TopicSelection extends AppCompatActivity {

    Button Communication,Networks;
    String ComAndTrans= "Communication And Transmission Media";
    String CompNetworks= "Computer Networks";
    String Completed= "You have completed the course! Congrats!!";
    Learner learner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_selection);

        learner = Learner.get();
        Communication = findViewById(R.id.CommsAndTransmission);
        Networks = findViewById(R.id.CompNetworks);
        Networks.setVisibility(View.INVISIBLE);


        Communication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(learner.getCompleted()!=null){
                    if (learner.getCompleted().contains("A") && learner.getCompleted().contains("B") && learner.getCompleted().contains("C")) {
                        Communication.setText(Completed);
                        Communication.setEnabled(false);
                    }
                    else{
                        Intent intent = new Intent(getBaseContext(), Test.class);
                        learner.setCurrentTopic(ComAndTrans);
                        startActivity(intent);
                    }
                    finish();
                }
                else {
                    Intent intent = new Intent(getBaseContext(), Test.class);
                    learner.setCurrentTopic(ComAndTrans);
                    startActivity(intent);
                    finish();
                }
            }
        });

        Networks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Test.class);
                learner.setCurrentTopic(CompNetworks);
                startActivity(intent);
            }
        });

    }
}
