package com.sweethome.jimmy.moodtracker;

public class Mood {

    private int moodId;
    private int colorId;

    public Mood(int moodId, int colorId) {
        this.moodId = moodId;
        this.colorId = colorId;
    }

    public int getMoodId() {
        return this.moodId;
    }

    public int getColorId() {
        return this.colorId;
    }
}
