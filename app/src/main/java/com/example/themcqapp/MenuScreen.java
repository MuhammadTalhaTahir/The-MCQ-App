package com.example.themcqapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuScreen extends AppCompatActivity implements View.OnClickListener{
    Button startButton, resultButton, linkButton;
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
    }
    private int generateSession(){
        return -1;
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case(R.id.startQuiz):
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("sessionId", this.generateSession());
                startActivity(intent);
                break;
            case(R.id.viewResult):
                break;
            case(R.id.gitRepoLink):
                break;
        }
    }
}