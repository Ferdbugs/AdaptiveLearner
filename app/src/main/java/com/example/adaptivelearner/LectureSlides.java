package com.example.adaptivelearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adaptivelearner.Provider.UserDB;

import java.io.File;
import java.io.IOException;

public class LectureSlides extends AppCompatActivity {

    String difficulty, topic, performance,learnerState,contentComplexity,beginner,intermediate,expert, complete;
    public Learner learner;
    TextView recommended,subject;
    Button material,contn,homeScreen;

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

        material = findViewById(R.id.Material);
        contn = findViewById(R.id.ContnTest);
        homeScreen = findViewById(R.id.homeScreen);
        subject = findViewById(R.id.subject);

        beginner= "Your Score is below the intermediate level. It is strongly recommended you start from the basics.";
        intermediate= "Your score is average, intermediate content is recommended for you.";
        expert= "You are well versed in this course. We recommend the expert difficulty content for you to fine tune your skills";
        complete = "You have completed the course! Well done!!";

        subject.setText(topic);

        homeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home = new Intent(LectureSlides.this,MainActivity.class);
                startActivity(Home);
            }
        });

        contn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Test = new Intent(LectureSlides.this,Test.class);
                startActivity(Test);
            }
        });

        material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File topicDir = new File(
                        getExternalFilesDir(null)+"/Adaptive Learner Materials/" + learner.getCurrentTopic() + "/");
                File list[] = topicDir.listFiles();
                if(list!=null){
                    if(contentComplexity.equals("Low")) {
                        Uri contentUri = FileProvider.getUriForFile(LectureSlides.this, "com.example.adaptivelearner.fileprovider", list[0]);
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(contentUri);
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivity(intent);
                    }
                    else if(contentComplexity.equals("Medium")){
                        Uri contentUri = FileProvider.getUriForFile(LectureSlides.this, "com.example.adaptivelearner.fileprovider", list[1]);
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(contentUri);
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivity(intent);
                    }
                    else{
                        Uri contentUri = FileProvider.getUriForFile(LectureSlides.this, "com.example.adaptivelearner.fileprovider", list[2]);
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(contentUri);
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivity(intent);
                    }
                }

            }
        });

        connectServer();

    }

    void setContentComplexity(){
        if(learner.getCompleted().contains("A") && learner.getCompleted().contains("B") && learner.getCompleted().contains("C")){
            recommended.setText(complete);
            material.setEnabled(false);
            contn.setEnabled(false);
        }
        if(contentComplexity.equals("Low")){
            if(!recommended.getText().equals(complete)){
                recommended.setText(beginner);
            }
            learner.setCurrentDifficulty("Easy");
        }
        else if(contentComplexity.equals("Medium")){
            if(!recommended.getText().equals(complete)){
                recommended.setText(beginner);
            }
            recommended.setText(intermediate);
            learner.setCurrentDifficulty("Medium");
        }
        else{
            if(!recommended.getText().equals(complete)){
                recommended.setText(beginner);
            }
            recommended.setText(expert);
            learner.setCurrentDifficulty("Hard");
        }
    }

    void saveUser(){
        UserDB.getInstance(getApplicationContext()).updateLearner(learner);
    }

    void connectServer() {

        String postUrl = "http://ferdbugs88.pythonanywhere.com/";
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
                            saveUser();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }
}