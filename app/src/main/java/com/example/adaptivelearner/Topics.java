package com.example.adaptivelearner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


import com.example.adaptivelearner.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.security.AccessController.getContext;

public class Topics extends AppCompatActivity {

    private ListView topics;
    private CustomAdapter customAdapter;
    private Spinner dropdownList;
    private String path;

    private class CustomAdapter extends ArrayAdapter<File> {
        File[] data;

        public CustomAdapter(@NonNull Context context, int resource, @NonNull File[] objects) {
            super(context, resource, objects);
            data = objects;
        }

        private class holdView {
            TextView titleCard;            //Initialize textViews
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            holdView holder;

            if (convertView == null) {
                holder = new holdView();
                convertView =  getLayoutInflater().inflate(R.layout.list_item, null);
                holder.titleCard = convertView.findViewById(R.id.title);

                convertView.setTag(holder);

            } else {
                holder = (holdView) convertView.getTag();
            }
            String text = data[position].getName();
            holder.titleCard.setText(text.substring(0, text.length() - 4));
            return convertView;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        topics = findViewById(R.id.topicList);
        dropdownList = findViewById(R.id.dropdownTopic);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Topics.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.dropdown));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownList.setAdapter(arrayAdapter);

        File topicDir = new File(
                getExternalFilesDir(null)+"/Adaptive Learner Materials/" + dropdownList.getSelectedItem().toString() + "/");
        File list[] = topicDir.listFiles();
        if(list!=null){
            customAdapter = new CustomAdapter(this, android.R.layout.simple_list_item_1, list);
            customAdapter.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.toString().compareTo(o2.toString());
                }
            });
            topics.setAdapter(customAdapter);
        }

        dropdownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                File topicDir = new File(
                        getExternalFilesDir(null) + "/Adaptive Learner Materials/" + dropdownList.getSelectedItem().toString() + "/");
                File list[] = topicDir.listFiles();
                if(list!=null){
                    customAdapter = new CustomAdapter(Topics.this, android.R.layout.simple_list_item_1, list);
                    customAdapter.sort(new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            return o1.toString().compareTo(o2.toString());
                        }
                    });
                    topics.setAdapter(customAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(topics!=null)
        {
            topics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String Title = topics.getItemAtPosition(position).toString();
                    File file = new File(Title);
                    Uri contentUri = FileProvider.getUriForFile(Topics.this, "com.example.adaptivelearner.fileprovider", file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(contentUri);
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intent);
                }
            });
        }


    }

}
