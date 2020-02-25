package com.example.adaptivelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


import com.example.adaptivelearner.Provider.UserDB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button library,learn,pickup;
    Learner learner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        library = findViewById(R.id.Learn);
        learn = findViewById(R.id.Exercises);
        pickup = findViewById(R.id.PickUp);
        learner = new Learner();

        pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learner = UserDB.getInstance(getApplicationContext()).getLatest();
                pickup.setText(learner.getCurrentTopic());
            }
        });

        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Lib = new Intent(MainActivity.this,Topics.class);
                startActivity(Lib);
            }
        });

        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Topic = new Intent(MainActivity.this,TopicSelection.class);
                startActivity(Topic);
            }
        });

        moveAssetToStorageDir("Adaptive Learner Materials");
    }

    public void moveAssetToStorageDir(String path){
        File file = getExternalFilesDir(null);
        String rootPath = file.getPath() + "/";
        File mainDir = new File (rootPath + path);
        if(!mainDir.exists()){
            mainDir.mkdir();
        }

        try{
            String [] paths = getAssets().list(path);
            for(int i=0; i<paths.length; i++){
                if(!paths[i].contains(".")){
                    File dir = new File(rootPath + path +"/"+ paths[i]+"/");
                    boolean made=dir.mkdir();
                    if(made){
                        moveAssetToStorageDir( path +"/"+ paths[i]);
                    }
                }else {
                    File dest = null;
                    InputStream in = null;
                    if(path.length() == 0) {
                        dest = new File(rootPath + paths[i]);
                        in = getAssets().open(paths[i]);
                    }else{
                        dest = new File(rootPath + path + "/" + paths[i]);
                        in = getAssets().open(path + "/" + paths[i]);
                    }
                    dest.createNewFile();
                    FileOutputStream out = new FileOutputStream(dest);
                    byte [] buff = new byte[in.available()];
                    in.read(buff);
                    out.write(buff);
                    out.close();
                    in.close();
                }
            }
        }catch (Exception exp){
            exp.printStackTrace();
        }
    }
}
