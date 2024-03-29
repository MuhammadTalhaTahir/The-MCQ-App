package com.example.themcqapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseContext extends SQLiteOpenHelper {
    public DataBaseContext(@Nullable Context context){
        super(context, "theMCQApp", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createResultsTable = "CREATE table results(" +
                "  id integer PRIMARY key AUTOINCREMENT," +
                "  sessionId integer NOT NULL," +
                "  question varchar," +
                "  rightAnswer char," +
                "  selectedAnswer char" +
                "  )";
        sqLiteDatabase.execSQL(createResultsTable);
    }
    public void insertQuestion(Question question, int sessionId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("sessionId", sessionId);
        cv.put("question", question.question);
        cv.put("rightAnswer", Character.toString(question.rightAnswer));
        cv.put("selectedAnswer", Character.toString(question.selectedAnswer));
        db.insert("results", null, cv);
    }
    public int getMaxSessionId(){
        String getSessionId = "select max(sessionId) from results";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(getSessionId, null);
        cursor.moveToNext();
        return cursor.getInt(0);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public ArrayList<Session> getSessions(){
        ArrayList<Session> result = new ArrayList<Session>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor sessionsList = db.rawQuery("select sessionId, count(question) from results group by sessionId;", null);
        while(sessionsList.moveToNext()){
            result.add(new Session(sessionsList.getInt(0), sessionsList.getInt(1)));
        }
        return result;
    }
    public ArrayList<Question> getQuestions(int sessionId){
        String query = "select  question, rightAnswer, selectedAnswer  from results where sessionId =" + Integer.toString(sessionId);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Question> result = new ArrayList<Question>();
        while(cursor.moveToNext()){
            result.add(new Question(
                    cursor.getString(0),
                    cursor.getString(1).charAt(0),
                    cursor.getString(2).charAt(0)
            ));
        }
        return result;
    }
}
