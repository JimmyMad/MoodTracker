package com.sweethome.jimmy.moodtracker.model;


import com.sweethome.jimmy.moodtracker.R;

public class MoodBank {


    private Mood moodTable[];

    /**
     * Creates all the moods and store them in a table
     */
    public MoodBank() {
        Mood sadMood = new Mood(R.drawable.smiley_sad, "Sad", R.color.faded_red);
        Mood disappointedMood = new Mood(R.drawable.smiley_disappointed, "Disappointed", R.color.warm_grey);
        Mood normalMood = new Mood(R.drawable.smiley_normal, "Normal", R.color.cornflower_blue_65);
        Mood happyMood = new Mood(R.drawable.smiley_happy, "Happy", R.color.light_sage);
        Mood superHappyMood = new Mood(R.drawable.smiley_super_happy, "SuperHappy", R.color.banana_yellow);

        this.moodTable = new Mood[]{sadMood, disappointedMood, normalMood, happyMood, superHappyMood};
    }

    public Mood[] getMoodTable() {
        return moodTable;
    }
}