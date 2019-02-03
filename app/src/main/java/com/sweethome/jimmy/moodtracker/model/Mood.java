package com.sweethome.jimmy.moodtracker.model;

import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import java.util.Date;

public class Mood {

    private int moodId;
    private String moodName;
    private int colorId;
    private Date date;
    private String comment = "";
    private int soundId;

    public Mood(int moodId, String moodName, int colorId, int soundId) {
        this.moodId = moodId;
        this.moodName = moodName;
        this.colorId = colorId;
        this.soundId = soundId;
    }

    public Mood(@NonNull Mood aMood, Date date) {
        this.moodId = aMood.getMoodId();
        this.moodName = aMood.getMoodName();
        this.colorId = aMood.getColorId();
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }

    public void setMoodName(String moodName) {
        this.moodName = moodName;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSoundId() {
        return soundId;
    }

    public void setSoundId(int soundId) {
        this.soundId = soundId;
    }
}
