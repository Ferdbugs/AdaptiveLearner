package com.example.adaptivelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LectureSlides extends AppCompatActivity {

    String difficulty,topic,performance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_slides);

        topic = getIntent().getStringExtra("evalTopic");
        difficulty = getIntent().getStringExtra("evalDifficulty");
        performance = getIntent().getStringExtra("evalPerformance");
    }

}
