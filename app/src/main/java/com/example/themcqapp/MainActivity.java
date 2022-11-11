package com.example.themcqapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int numberOfQuestions = 4, currentQuestionAsked = -1, numberofOptions = 4;
    Question[] questions;

    //views
    ImageView objectImage;
    TextView objectName;
    Button nextQuestionBtn;
    Button[] options;

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
    private void initializeOptions() {
        char rightAnswer = this.questions[currentQuestionAsked].rightAnswer;
        Random rand = new Random();
        for (int i = 0; i < numberofOptions; i++) {
            int tempNumber = rand.nextInt(26) + 65;
            char tempOption = (char) tempNumber;
            this.options[i].setText(Character.toString(tempOption));    //typecasting char to strings
            this.options[i].setBackgroundColor(Color.WHITE);
        }
        this.options[rand.nextInt(4)].setText(Character.toString(rightAnswer));
    }

    private void generateNextQuestion(){
        currentQuestionAsked ++;
        if(currentQuestionAsked == numberOfQuestions) currentQuestionAsked = 0;
        this.objectImage.setImageResource(this.questions[currentQuestionAsked].imageId);
        this.objectName.setText(this.questions[currentQuestionAsked].question);
        this.initializeOptions();
    }
    private void toggleOptionButtons(boolean enable){
        /*this function disables option buttons and enable next button.
                                    OR
        this function enable option buttons and disable next button.*/

        for(int i=0;i<this.numberofOptions;i++){
            options[i].setEnabled(enable);
        }
        this.nextQuestionBtn.setEnabled(!enable);
        String color = enable?"#cccccc":"#FA9E40";
        this.nextQuestionBtn.setBackgroundColor(Color.parseColor(color));
    }
    private void checkAnswer(int id){
        String rightAnswer = Character.toString(questions[currentQuestionAsked].rightAnswer);
        for(int i=0;i<numberofOptions;i++){
            String answer = options[i].getText().toString();
            if(answer.equals(rightAnswer)){
                options[i].setBackgroundColor(Color.parseColor("#80d089"));
            }
            if(options[i].getId() == id && !answer.equals(rightAnswer)){
                options[i].setBackgroundColor(Color.parseColor("#f7351b"));
            }
        }
        this.toggleOptionButtons(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing views
        this.objectImage = findViewById(R.id.objectImage);
        this.objectName = findViewById(R.id.objectName);
        this.nextQuestionBtn = findViewById(R.id.nextQuestionBtn);
        this.nextQuestionBtn.setOnClickListener(this);
        this.nextQuestionBtn.setEnabled(false);
        this.options = new Button[numberofOptions];
        this.options[0] = findViewById(R.id.optionA);
        this.options[0].setOnClickListener(this);
        this.options[1] = findViewById(R.id.optionB);
        this.options[1].setOnClickListener(this);
        this.options[2] = findViewById(R.id.optionC);
        this.options[2].setOnClickListener(this);
        this.options[3] = findViewById(R.id.optionD);
        this.options[3].setOnClickListener(this);

        //starting The MCQS App
        this.generateNextQuestion();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nextQuestionBtn) {
            this.generateNextQuestion();
            this.toggleOptionButtons(true);
        } else {
            this.checkAnswer(view.getId());
        }
    }
}