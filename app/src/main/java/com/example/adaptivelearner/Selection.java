package com.example.adaptivelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selection extends AppCompatActivity {

    private Button Beginner,Intermediate,Advanced;
    private Learner learner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Beginner = findViewById(R.id.Beginner);
        Intermediate = findViewById(R.id.Intermediate);
        Advanced = findViewById(R.id.Advanced);

        Beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learner.currentDifficulty = "Beginner";
                Intent Beginner = new Intent(Selection.this,Beginner.class);
                startActivity(Beginner);
            }
        });

        Intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learner.currentDifficulty = "Intermediate";
            }
        });

        Advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learner.currentDifficulty = "Advanced";
            }
        });
    }

}
