package com.sweethome.jimmy.moodtracker;


public class MoodBank {


    private Mood moodTable[];

    public MoodBank() {
        this.moodTable = generateMoodTable();
    }

    /**
     * Creates all the moods and store them in a table
     * @return a table of moods
     */
    private Mood[] generateMoodTable() {
        Mood sadMood = new Mood(R.drawable.smiley_sad, R.color.faded_red,0);
        Mood disappointedMood = new Mood(R.drawable.smiley_disappointed, R.color.warm_grey,1);
        Mood normalMood = new Mood(R.drawable.smiley_normal, R.color.cornflower_blue_65,2);
        Mood happyMood = new Mood(R.drawable.smiley_happy, R.color.light_sage, 3);
        Mood superHappyMood = new Mood(R.drawable.smiley_super_happy, R.color.banana_yellow, 4);

        Mood moodTab[] = {sadMood, disappointedMood, normalMood, happyMood, superHappyMood};
        return moodTab;
    }

    public Mood[] getMoodTable() {
        return moodTable;
    }
}