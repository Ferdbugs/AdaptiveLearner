package com.example.adaptivelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TopicSelection extends AppCompatActivity {

    Button Communication;
    String ComAndTrans= "Communication And Transmission";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_selection);

        Communication = findViewById(R.id.CommsAndTransmission);

        Communication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Test.class);
                intent.putExtra("Topic", ComAndTrans);
                startActivity(intent);
            }
        });

    }
}
