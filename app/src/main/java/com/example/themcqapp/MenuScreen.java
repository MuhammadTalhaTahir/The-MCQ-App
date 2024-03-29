package com.example.themcqapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuScreen extends AppCompatActivity implements View.OnClickListener{
    Button startButton, resultButton, linkButton;
    DataBaseContext db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        startButton = findViewById(R.id.startQuiz);
        resultButton = findViewById(R.id.viewResult);
        linkButton = findViewById(R.id.gitRepoLink);
        startButton.setOnClickListener(this);
        resultButton.setOnClickListener(this);
        linkButton.setOnClickListener(this);
        db = new DataBaseContext(this);
    }
    private int generateSession(){
        return db.getMaxSessionId() + 1;
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case(R.id.startQuiz): {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("sessionId", this.generateSession());
                startActivity(intent);
                break;
            }
            case(R.id.viewResult): {
                Intent intent = new Intent(this, Result.class);
                startActivity(intent);
                break;
            }
            case(R.id.gitRepoLink): {
                Uri repoLink = Uri.parse("https://github.com/MuhammadTalhaTahir/The-MCQ-App/");
                Intent intent = new Intent(Intent.ACTION_VIEW, repoLink);
                startActivity(intent);
                break;
            }
        }
    }
}