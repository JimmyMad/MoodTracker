package com.sweethome.jimmy.moodtracker;

public class Mood {

    private int moodId;
    private int colorId;
    private String moodName;

    public Mood(int moodId, int colorId, String moodName) {
        this.moodId = moodId;
        this.colorId = colorId;
        this.moodName = moodName;
    }

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getMoodName() {
        return moodName;
    }

    public void setMoodName(String moodName) {
        this.moodName = moodName;
    }
}
