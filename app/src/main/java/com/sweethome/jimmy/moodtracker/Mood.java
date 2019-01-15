package com.sweethome.jimmy.moodtracker;

public class Mood {

    private int moodId;
    private int colorId;
    private int indexNb;

    public Mood(int moodId, int colorId, int index) {
        this.moodId = moodId;
        this.colorId = colorId;
        this.indexNb = index;
    }

    public int getMoodId() {
        return moodId;
    }

    public int getColorId() {
        return colorId;
    }

    public int getIndexNb() {
        return indexNb;
    }
}
