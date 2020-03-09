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
    String Completed= "[Completed]";
    String CompleteCom = ComAndTrans+Completed;
    String CompleteNetwork = CompNetworks+Completed;
    Learner learner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_selection);

        learner = Learner.get();
        Communication = findViewById(R.id.CommsAndTransmission);
        Networks = findViewById(R.id.CompNetworks);

        if(learner.getCompleted()!=null) {
            if (learner.getCompleted().contains("A") && learner.getCompleted().contains("B") && learner.getCompleted().contains("C")) {
                Communication.setText(CompleteCom);
            }
            if (learner.getCompleted().contains("D") && learner.getCompleted().contains("E") && learner.getCompleted().contains("F")) {
                Networks.setText(CompleteNetwork);
            }
        }

        Communication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Test.class);
                learner.setCurrentTopic(ComAndTrans);
                startActivity(intent);
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
