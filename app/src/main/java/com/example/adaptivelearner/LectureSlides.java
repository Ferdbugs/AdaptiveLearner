package com.example.adaptivelearner;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class LectureSlides extends AppCompatActivity {

    String difficulty, topic, performance,learnerState,contentComplexity,beginner,intermediate,expert;
    public Learner learner;
    TextView recommended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_slides);

        learner = Learner.get();
        recommended = findViewById(R.id.recommendation);
        topic = learner.getCurrentTopic();
        difficulty = learner.getCurrentDifficulty();
        performance = learner.getPerformance();
        learnerState = learner.getLearnerState();
        beginner= "Your Score is below the intermediate level. It is strongly recommended you start from the basics.";
        intermediate= "Your score is average, intermediate content is recommended for you.";
        expert= "You are well versed in this course. We recommend the expert difficulty content for you to fine tune your skills";

        connectServer();

    }

    void setContentComplexity(){
        if(contentComplexity.equals("Low")){
            recommended.setText(beginner);
        }
        else if(contentComplexity.equals("Medium")){
            recommended.setText(intermediate);
        }
        else{
            recommended.setText(expert);
        }
    }

    void connectServer() {

        String postUrl = "http://10.163.14.154:5000/";
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("performance", performance)
                .addFormDataPart("learnerState", learnerState)
                .build();

        postRequest(postUrl, requestBody);
    }

    void postRequest(String postUrl, RequestBody postBody) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(postUrl)
                .post(postBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Cancel the post on failure.
                call.cancel();

                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("CONNECTION", "FAILED!");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) {
                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            contentComplexity = response.body().string();
                            setContentComplexity();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }
}