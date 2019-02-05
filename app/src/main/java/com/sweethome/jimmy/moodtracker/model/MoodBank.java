package com.sweethome.jimmy.moodtracker.model;


import com.sweethome.jimmy.moodtracker.R;

public class MoodBank {


    private Mood moodTable[];

    /**
     * Create all the moods and store them in a table
     */
    public MoodBank() {
        Mood sadMood = new Mood(R.drawable.smiley_sad, "Sad", R.color.faded_red, R.raw.sad);
        Mood disappointedMood = new Mood(R.drawable.smiley_disappointed, "Disappointed", R.color.warm_grey, R.raw.disappointed);
        Mood normalMood = new Mood(R.drawable.smiley_normal, "Normal", R.color.cornflower_blue_65, R.raw.normal);
        Mood happyMood = new Mood(R.drawable.smiley_happy, "Happy", R.color.light_sage, R.raw.happy);
        Mood superHappyMood = new Mood(R.drawable.smiley_super_happy, "SuperHappy", R.color.banana_yellow, R.raw.superhappy);

        this.moodTable = new Mood[]{sadMood, disappointedMood, normalMood, happyMood, superHappyMood};
    }

    public Mood[] getMoodTable() {
        return moodTable;
    }
}