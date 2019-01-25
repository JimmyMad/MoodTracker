package com.sweethome.jimmy.moodtracker.model;

import java.util.Date;

public class MoodInHistory {

    private Mood mood;
    private String comment;
    private boolean hasComment;
    private Date date;
    private int historyPosition;

    MoodInHistory(Mood aMood,String aComment, boolean hasAComment, Date aDate, int thePositionInHistory) {
        this.mood = aMood;
        this.comment = aComment;
        this.hasComment = hasAComment;
        this.date = aDate;
        this.historyPosition = thePositionInHistory;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Mood getMood() {
        return mood;
    }

    public String getComment() {
        return comment;
    }

    public boolean isHasComment() {
        return hasComment;
    }

    public Date getDate() {
        return date;
    }

    public int getHistoryPosition() {
        return historyPosition;
    }
}
