package com.example.themcqapp;

public class Session {
    public int sessionId;
    public int attemptedQuesitons;
    public float percentage;
    public Session(int sessionId, int attemptedQuesitons){
        this.sessionId = sessionId;
        this.attemptedQuesitons = attemptedQuesitons;
    }
    @Override
    public String toString(){
        return sessionId + ". " + "Attempted Questions: "+attemptedQuesitons +" Total Questions: 10";
    }
}
