package com.example.themcqapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DetailView extends AppCompatActivity {
    ListView questionsList;
    int sessionId;
    DataBaseContext db;
    Button shareButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        sessionId = getIntent().getIntExtra("sessionId" , -1);
        db = new DataBaseContext(this);
        shareButton = findViewById(R.id.shareBtn);
        questionsList = findViewById(R.id.questionsListView);
        ArrayList<Question> data = db.getQuestions(sessionId);
        ArrayAdapter<Question> adpt = new ArrayAdapter<Question>(this, android.R.layout.simple_list_item_1, data);
        questionsList.setAdapter(adpt);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("xD This is THE MCQS APP");
                Intent intent = new Intent(Intent.ACTION_SEND,uri);
                startActivity(intent);
            }
        });
    }
}