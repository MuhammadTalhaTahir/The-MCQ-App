package com.example.themcqapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    int numberOfQuestions = 4;
    Question[] questions;

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
            this.questions[i].question.replace(this.questions[i].rightAnswer, '_');
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}