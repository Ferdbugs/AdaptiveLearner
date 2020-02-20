package com.example.adaptivelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class TopicSelection extends AppCompatActivity {

    Button Communication;
    String ComAndTrans= "Communication And Transmission Media";
    Learner learner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_selection);

        learner = Learner.get();
        Communication = findViewById(R.id.CommsAndTransmission);

        Communication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Test.class);
                learner.setCurrentTopic(ComAndTrans);
                startActivity(intent);
            }
        });

    }
}
