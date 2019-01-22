package com.sweethome.jimmy.moodtracker.controller;

import android.content.SharedPreferences;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sweethome.jimmy.moodtracker.model.Mood;
import com.sweethome.jimmy.moodtracker.model.MoodBank;
import com.sweethome.jimmy.moodtracker.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetectorCompat detectGesture;

    private ImageView mMoodImage;
    private ImageView mAddCommentImage;
    private ImageView mHistoricImage;
    private LinearLayout mBackgroundColor;

    public static Date date;
    String sDate;
    DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT);
    //public MoodBank moodHistoric = new MoodBank();

    MoodBank mMoodBank = new MoodBank();
    Mood moodTable[];
    int currentMoodIndex;

    private Mood mCurrentMood = null;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detectGesture = new GestureDetectorCompat(this, this);


        //moodHistoric.setMoodTable(new Mood[]{null, null, null, null, null, null, null});

        sharedPref = getPreferences(MODE_PRIVATE);
        getCurrentDate();

        mMoodBank.generateMoodTable();
        moodTable = mMoodBank.getMoodTable();

        System.out.println("Dabug!! Before SharedPref Method PASSED! ");

        System.out.println("Dabug!! Before IF Method PASSED! ");

        currentMoodIndex = 3;

        System.out.println("Dabug!! AfterIf Method PASSED! ");

        mMoodImage = findViewById(R.id.MainActivity_Smiley_ImageView);
        mAddCommentImage = findViewById(R.id.MainActivity_NewComment_ImageView);
        mHistoricImage = findViewById(R.id.MainActivity_Historic_ImageView);
        mBackgroundColor = findViewById(R.id.MainActivity_Background_LinearLayout);



        try {
            System.out.println("Dabug!! Try");
            loadCurrentMood();
            System.out.println("Dabug!! Tryed");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Dabug!! FUCKED");
        }
        setCurrentMood(currentMoodIndex);
        setMoodImageAndBackground();
        saveCurrentMood();

        System.out.println("Dabug!! OnCreate Method PASSED! ");
    }

    private void setCurrentMood(int index) {
        mCurrentMood = moodTable[index];
        System.out.println("Dabug!! SetCurrentMood Method PASSED! ");
    }

    private void setMoodImageAndBackground() {
        mMoodImage.setImageResource(mCurrentMood.getMoodId());
        mBackgroundColor.setBackgroundResource(mCurrentMood.getColorId());
        Toast.makeText(this, sDate, Toast.LENGTH_LONG).show();
        System.out.println("Dabug!! setMoodImageAndBackground Method PASSED! ");
    }

    private void saveCurrentMood() {
        sharedPref.edit().putInt("KEY_MOOD_ID", mCurrentMood.getMoodId()).apply();
        getCurrentDate();
        sharedPref.edit().putString("KEY_LAST_DATE_USED", sDate).apply();
        System.out.println("Dabug!! " + sharedPref.getString("KEY_LAST_DATE_USED", ""));
        System.out.println("Dabug!! saveCurrentMood Method PASSED! ");
    }

    private void loadCurrentMood() throws ParseException {
        int i = 0;
        if (!sharedPref.getString("KEY_LAST_DATE_USED", "").isEmpty()) {
            System.out.println("Dabug!! " + i++);
            System.out.println("Dabug!! " + sharedPref.getString("KEY_LAST_DATE_USED", ""));


            Date currentDate = formatter.parse(sDate);
            System.out.println("Dabug!! " + currentDate);
            System.out.println("Dabug!! " + i++);
            Date savedDate = formatter.parse(sharedPref.getString("KEY_LAST_DATE_USED", ""));
            System.out.println("Dabug!! " + i++);
            System.out.println("Dabug!! " + savedDate);
            System.out.println("Dabug!! " + currentDate);

            if((currentDate.getTime() - (long)(1000*60*60*24)) >= savedDate.getTime()) {
                setCurrentMood(currentMoodIndex);
            } else {
                if (sharedPref.getInt("KEY_MOOD_ID", 0) != 0) {
                    currentMoodIndex = -1;
                    for (Mood aMood:moodTable) {
                        currentMoodIndex++;
                        if (aMood.getMoodId() == sharedPref.getInt("KEY_MOOD_ID", 0)){
                            mCurrentMood = aMood;
                            return;
                        }
                    }
                }
            }
        }
    }

    private void getCurrentDate() {
        date = new Date();
        sDate = formatter.format(date);
        System.out.println("Dabug!! " + sDate);
        System.out.println("Dabug!! " + sharedPref.getString("KEY_LAST_DATE_USED", ""));
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if ( e1.getRawY() > e2.getRawY() && currentMoodIndex < 4) {
            currentMoodIndex++;
            System.out.println("currentIndexMood "+currentMoodIndex);

        } if (e1.getRawY() < e2.getRawY() && currentMoodIndex > 0) {
            currentMoodIndex--;
            System.out.println("currentIndexMood "+currentMoodIndex);
        }
        setCurrentMood(currentMoodIndex);
        saveCurrentMood();
        setMoodImageAndBackground();
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detectGesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
