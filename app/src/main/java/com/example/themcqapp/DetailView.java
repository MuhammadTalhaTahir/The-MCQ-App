package com.example.themcqapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailView extends AppCompatActivity {
    ListView questionsList;
    int sessionId;
    DataBaseContext db;
    Button shareButton;
    TextView raView, perView;
    int totalRightAnswers;
    double percentage;
    int PASSING_PERCENTAGE = 60;
    public void calculateTotalRightAnswers(ArrayList<Question> questions){
        this.totalRightAnswers = 0;
        for(Question q : questions){
            if(q.selectedAnswer == q.rightAnswer) this.totalRightAnswers++;
        }
        this.percentage = (totalRightAnswers * 100)/10.0;
        if(raView != null && perView != null){
            raView.setText(Integer.toString(this.totalRightAnswers)+"/10");
            perView.setText(Double.toString(this.percentage));
        }
    }
    public String createShareAbleResult(){
        String result = "Score: " + this.totalRightAnswers +"/10\n" +
                "Percentage: "+ this.percentage+"\n"+
                "Status: " + (this.percentage >= PASSING_PERCENTAGE?"Pass 🎉":"Fail 😢")+"\n"+
                "\n\n~ _*The Mcq App*_";
        return result;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        sessionId = getIntent().getIntExtra("sessionId" , -1);
        db = new DataBaseContext(this);
        shareButton = findViewById(R.id.shareBtn);
        questionsList = findViewById(R.id.questionsListView);
        raView = findViewById(R.id.totalRightQuestionsView);
        perView = findViewById(R.id.percentageView);
        ArrayList<Question> data = db.getQuestions(sessionId);
        this.calculateTotalRightAnswers(data);
        ArrayAdapter<Question> adpt = new ArrayAdapter<Question>(this, android.R.layout.simple_list_item_1, data);
        questionsList.setAdapter(adpt);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, createShareAbleResult());
                intent.setType("text/plain");
                startActivity(intent);
            }
        });
    }
}