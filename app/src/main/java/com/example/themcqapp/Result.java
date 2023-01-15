package com.example.themcqapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Result extends AppCompatActivity {
    ListView sessionsListView;
    DataBaseContext db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        sessionsListView = findViewById(R.id.sessionsList);
        db = new DataBaseContext(this);
        ArrayList<Session> data = db.getSessions();
        ArrayAdapter<Session> adpt = new ArrayAdapter<Session>(this, android.R.layout.simple_list_item_1, data);
        sessionsListView.setAdapter(adpt);
        sessionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new
            }
        });
    }
}