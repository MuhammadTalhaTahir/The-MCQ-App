package com.example.themcqapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}
