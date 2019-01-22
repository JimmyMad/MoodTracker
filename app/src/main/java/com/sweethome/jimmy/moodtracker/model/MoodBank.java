package com.sweethome.jimmy.moodtracker.model;


import com.sweethome.jimmy.moodtracker.R;

import java.util.LinkedList;

public class MoodBank {


    private LinkedList<Mood> moodLinkedList = new LinkedList<>();

    /**
     * Creates all the moods and store them in a table
     */
    public void generateMoodTable() {
        Mood sadMood = new Mood(R.drawable.smiley_sad, R.color.faded_red);
        Mood disappointedMood = new Mood(R.drawable.smiley_disappointed, R.color.warm_grey);
        Mood normalMood = new Mood(R.drawable.smiley_normal, R.color.cornflower_blue_65);
        Mood happyMood = new Mood(R.drawable.smiley_happy, R.color.light_sage);
        Mood superHappyMood = new Mood(R.drawable.smiley_super_happy, R.color.banana_yellow);

        this.moodLinkedList.addFirst(superHappyMood);
        System.out.println("currentIndexMood "+this.moodLinkedList.getFirst().getMoodId());
        this.moodLinkedList.addFirst(happyMood);
        System.out.println("currentIndexMood "+this.moodLinkedList.getFirst().getMoodId());
        this.moodLinkedList.addFirst(normalMood);
        System.out.println("currentIndexMood "+this.moodLinkedList.getFirst().getMoodId());
        this.moodLinkedList.addFirst(disappointedMood);
        System.out.println("currentIndexMood "+this.moodLinkedList.getFirst().getMoodId());
        this.moodLinkedList.addFirst(sadMood);
        System.out.println("currentIndexMood "+this.moodLinkedList.getFirst().getMoodId());
    }

    public LinkedList<Mood> getMoodLinkedList() {
        return this.moodLinkedList;
    }

    /*public void addMoodToList(Mood aMood) {
        int currentMoodDay = aMood.getDate().getDay();
        int currentMoodMonth = aMood.getDate().getMonth();
        int currentMoodYear = aMood.getDate().getYear();
        int calendarDay = MainActivity.calendar.getTime().getDay();
        int calendarMonth = MainActivity.calendar.getTime().getMonth();
        int calendarYear = MainActivity.calendar.getTime().getYear();

        if (aMood.getDate() == null ||
                ((currentMoodDay <= calendarDay) && currentMoodMonth <= calendarMonth)  ) {

        }
    }*/
}