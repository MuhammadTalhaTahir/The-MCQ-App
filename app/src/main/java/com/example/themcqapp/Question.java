package com.example.themcqapp;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Question {
    public int imageId;
    public String question;
    public char rightAnswer;
    public char selectedAnswer;
    public Question(int i, String q){
        this.imageId = i;
        this.question = q;
    }
    public Question(String question, char rightAnswer, char selectedAnswer){
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.selectedAnswer = selectedAnswer;
    }
    @Override
    public String toString(){
        String isCorrect = rightAnswer == selectedAnswer?" ✅":" ❌";
        return question+"\t\t\t\t\t\t\t\t\t\t\t\t"+rightAnswer+"\t\t\t\t"+selectedAnswer+"\t\t\t\t"+isCorrect;
    }
}
