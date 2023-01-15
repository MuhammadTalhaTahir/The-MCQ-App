package com.example.themcqapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DetailView extends AppCompatActivity {
    ListView questionsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        questionsList = findViewById(R.id.questionsListView);
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(getIntent().getIntExtra("sessionId" , -1));
        ArrayAdapter<Integer> adpt = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, data);
        questionsList.setAdapter(adpt);
    }
}