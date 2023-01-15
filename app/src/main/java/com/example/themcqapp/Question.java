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
}
