package com.example.adaptivelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.adaptivelearner.R;

public class MainActivity extends AppCompatActivity {

    Button learn,exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        learn = findViewById(R.id.Learn);
        exercises = findViewById(R.id.Exercises);

        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Learn = new Intent(MainActivity.this,Topics.class);
                startActivity(Learn);
            }
        });

    }
}
