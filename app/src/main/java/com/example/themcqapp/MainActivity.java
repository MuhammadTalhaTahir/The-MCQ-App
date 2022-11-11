package com.example.themcqapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int numberOfQuestions = 4, currentQuestionAsked = 0;
    Question[] questions;

    //views
    ImageView objectImage;
    TextView objectName;

    public MainActivity(){
        this.questions = new Question[this.numberOfQuestions];
        //initializing questions
        this.questions[0] = new Question(R.drawable.cat, "CAT");
        this.questions[1] = new Question(R.drawable.dog, "DOG");
        this.questions[2] = new Question(R.drawable.car, "CAR");
        this.questions[3] = new Question(R.drawable.orange, "ORANGE");
        //shuffling the array.
        this.shuffle();
        //initializing the array with right answers based on randomly selected missing letter in the question statement.
        this.initializeRightAnswers();
    }
    private void initializeRightAnswers(){
        int missingIndex;
        Random rand = new Random();
        for(int i =0;i <this.numberOfQuestions;i++){
            //randomly selecting an index from the question statement based on its length.
            missingIndex = rand.nextInt(this.questions[i].question.length());
            this.questions[i].rightAnswer = this.questions[i].question.charAt(missingIndex);
            this.questions[i].question = this.questions[i].question.replace(this.questions[i].rightAnswer, '_');
        }
    }
    private void shuffle(){
        int index;
        Random rand = new Random();
        for(int i = this.numberOfQuestions-1; i > 0 ; i--){
            index = rand.nextInt(i + 1);
            Question temp = this.questions[index];
            this.questions[index] = this.questions[i];
            this.questions[i] = temp;
        }
    }
    private void generateNextQuestion(){
        this.objectImage.setImageResource(this.questions[currentQuestionAsked].imageId);
        this.objectName.setText(this.questions[currentQuestionAsked].question);
        currentQuestionAsked ++;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing views
        this.objectImage = findViewById(R.id.objectImage);
        this.objectName = findViewById(R.id.objectName);
        this.generateNextQuestion();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nextQuestionBtn:{
                this.generateNextQuestion();
                break;
            }

        }
    }
}