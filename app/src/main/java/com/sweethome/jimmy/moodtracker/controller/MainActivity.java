package com.sweethome.jimmy.moodtracker.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
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
    ImageView mHistoryImage;
    private LinearLayout mBackgroundColor;

    Date date;
    private String sDate;
    private DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT);
    //public MoodBank moodHistoric = new MoodBank();

    private MoodBank mMoodBank = new MoodBank();
    private int currentMoodIndex;

    private Mood mCurrentMood = null;

    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detectGesture = new GestureDetectorCompat(this, this);

        sharedPref = getPreferences(MODE_PRIVATE);

        currentMoodIndex = 3;

        mMoodImage = findViewById(R.id.MainActivity_Smiley_ImageView);
        mAddCommentImage = findViewById(R.id.MainActivity_NewComment_ImageView);
        mHistoryImage = findViewById(R.id.MainActivity_History_ImageView);
        mBackgroundColor = findViewById(R.id.MainActivity_Background_LinearLayout);

        mHistoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(MainActivity.this, HistoryActivity.class);
                MainActivity.this.startActivity(activityChangeIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCurrentDate();
        try {
            loadCurrentMood();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setCurrentMood(currentMoodIndex);
    }

    private void setCurrentMood(int index) {
        mCurrentMood = mMoodBank.getMoodTable()[index];
        saveCurrentMood();
        setMoodImageAndBackground();
    }

    private void setMoodImageAndBackground() {
        mMoodImage.setImageResource(mCurrentMood.getMoodId());
        mBackgroundColor.setBackgroundResource(mCurrentMood.getColorId());
        Toast.makeText(this, sDate, Toast.LENGTH_LONG).show();
    }

    private void saveCurrentMood() {
        sharedPref.edit().putInt("KEY_MOOD_ID", mCurrentMood.getMoodId()).apply();
        getCurrentDate();
        sharedPref.edit().putString("KEY_LAST_DATE_USED", sDate).apply();
    }

    private void loadCurrentMood() throws ParseException {
        if (!sharedPref.getString("KEY_LAST_DATE_USED", "").isEmpty()) {
            Date currentDate = formatter.parse(sDate);
            Date savedDate = formatter.parse(sharedPref.getString("KEY_LAST_DATE_USED", ""));
            if((currentDate.getTime() - savedDate.getTime() >= (long)(1000*60*60*24))) currentMoodIndex = 3;
            else {
                if (sharedPref.getInt("KEY_MOOD_ID", 0) != 0) {
                    currentMoodIndex = -1;
                    for (Mood aMood : mMoodBank.getMoodTable()) {
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
        if ( e1.getRawY() > e2.getRawY() && currentMoodIndex < 4) currentMoodIndex++;
        if (e1.getRawY() < e2.getRawY() && currentMoodIndex > 0) currentMoodIndex--;
        setCurrentMood(currentMoodIndex);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detectGesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}