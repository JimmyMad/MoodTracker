package com.sweethome.jimmy.moodtracker.model;

import java.util.Date;

public class Mood {

    private int moodId;
    private int colorId;
    private Date date;

    Mood(int moodId, int colorId) {
        this.moodId = moodId;
        this.colorId = colorId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public int getMoodId() {
        return this.moodId;
    }

    public int getColorId() {
        return this.colorId;
    }
}
