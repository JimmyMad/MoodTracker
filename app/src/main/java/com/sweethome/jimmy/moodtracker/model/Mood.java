package com.sweethome.jimmy.moodtracker.model;

public class Mood {

    private int moodId;
    private String moodName;
    private int colorId;

    Mood(int moodId, String moodName, int colorId) {
        this.moodId = moodId;
        this.moodName = moodName;
        this.colorId = colorId;
    }

    public String getMoodName() {
        return moodName;
    }

    public int getMoodId() {
        return this.moodId;
    }

    public int getColorId() {
        return this.colorId;
    }
}
