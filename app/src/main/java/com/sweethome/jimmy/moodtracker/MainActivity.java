package com.sweethome.jimmy.moodtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetectorCompat detectGesture;

    private ImageView mMoodImage;
    private ImageView mAddCommentImage;
    private ImageView mHistoricImage;
    private LinearLayout mBackgroundColor;

    MoodBank mMoodBank;
    Mood moodTable[];
    int currentMoodIndex;

    private Mood mCurrentMood = null;

    SharedPreferences sharedPref;
    public static final int KEY_MOOD_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detectGesture = new GestureDetectorCompat(this, this);


        mMoodBank = new MoodBank();
        moodTable = mMoodBank.getMoodTable();

        System.out.println("Dabug!! Before SharedPref Method PASSED! ");
        sharedPref = getPreferences(MODE_PRIVATE);
        System.out.println("Dabug!! Before IF Method PASSED! ");

        currentMoodIndex = 3;

        System.out.println("Dabug!! AfterIf Method PASSED! ");

        mMoodImage = findViewById(R.id.MainActivity_Smiley_ImageView);
        mAddCommentImage = findViewById(R.id.MainActivity_NewComment_ImageView);
        mHistoricImage = findViewById(R.id.MainActivity_Historic_ImageView);
        mBackgroundColor = findViewById(R.id.MainActivity_Background_LinearLayout);

        loadCurrentMood();
        setCurrentMood(currentMoodIndex);
        setMoodImageAndBackground();

        System.out.println("Dabug!! OnCreate Method PASSED! ");
    }

    private void setCurrentMood(int index) {
        mCurrentMood = moodTable[index];
        System.out.println("Dabug!! SetCurrentMood Method PASSED! ");
    }

    private void setMoodImageAndBackground() {
        mMoodImage.setImageResource(mCurrentMood.getMoodId());
        mBackgroundColor.setBackgroundResource(mCurrentMood.getColorId());
        System.out.println("Dabug!! setMoodImageAndBackground Method PASSED! ");
    }

    private void saveCurrentMood() {
        sharedPref.edit().putInt("KEY_MOOD_ID", mCurrentMood.getMoodId()).apply();
        System.out.println("Dabug!! saveCurrentMood Method PASSED! ");
    }

    private void loadCurrentMood() {
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
            setCurrentMood(currentMoodIndex);
            saveCurrentMood();
            setMoodImageAndBackground();
        } if (e1.getRawY() < e2.getRawY() && currentMoodIndex > 0) {
            currentMoodIndex--;
            System.out.println("currentIndexMood "+currentMoodIndex);
            setCurrentMood(currentMoodIndex);
            saveCurrentMood();
            setMoodImageAndBackground();
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detectGesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
