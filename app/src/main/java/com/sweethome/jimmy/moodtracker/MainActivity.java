package com.sweethome.jimmy.moodtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetectorCompat detectGestiure;

    private ImageView mMoodImage;
    private ImageView mAddCommentImage;
    private ImageView mHistoricImage;
    private LinearLayout mBackgroundColor;

    MoodBank mMoodBank;
    int currentMoodIndex;

    private Mood mCurrentMood;

    public static final int SAVED_MOOD_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detectGestiure = new GestureDetectorCompat(this, this);


        mMoodBank = new MoodBank();
        Mood moodTable[] = mMoodBank.getMoodTable();

        /*SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();*/
        //editor.putInt("SAVED_MOOD_ID", );

        //if(sharedPref.getInt("SAVED_MOOD_ID", 0) == 0) {
            mCurrentMood = moodTable[3];
            currentMoodIndex = mCurrentMood.getIndexNb();
        /*} else {
            for (Mood aMood : moodTable) {
                if(sharedPref.getInt("SAVED_MOOD_ID", 0) == aMood.getMoodId()) {
                    mCurrentMood = aMood;
                    currentMoodIndex = mCurrentMood.getIndexNb();
                }
            }
        }*/

        ImageView mMoodImage = findViewById(R.id.MainActivity_Smiley_ImageView);
        ImageView mAddCommentImage = findViewById(R.id.MainActivity_NewComment_ImageView);
        ImageView mHistoricImage = findViewById(R.id.MainActivity_Historic_ImageView);
        LinearLayout mBackgroundColor = findViewById(R.id.MainActivity_Background_LinearLayout);

        mMoodImage.setImageResource(mCurrentMood.getMoodId());
        mBackgroundColor.setBackgroundResource(mCurrentMood.getColorId());





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
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detectGestiure.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
