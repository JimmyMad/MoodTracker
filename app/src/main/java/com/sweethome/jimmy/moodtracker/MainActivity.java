package com.sweethome.jimmy.moodtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private GestureDetector mGestureDetector;
    private ImageView mMoodImage;
    private ImageView mAddCommentImage;
    private ImageView mHistoricImage;
    private LinearLayout mBackgroundColor;

    private MoodBank mMoodBank;

    private Mood mCurrentMood;

    public static final int SAVED_MOOD_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mMoodBank = new MoodBank();
        Mood moodTable[] = mMoodBank.getMoodTable();

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("SAVED_MOOD_ID", );

        if(SAVED_MOOD_ID == 0) {
            mCurrentMood = moodTable[3];
        } else {
            for (Mood aMood : moodTable) {
                if(SAVED_MOOD_ID == aMood.getMoodId()) {
                    mCurrentMood = aMood;
                }
            }
        }

        ImageView mMoodImage = findViewById(R.id.MainActivity_Smiley_ImageView);
        ImageView mAddCommentImage = findViewById(R.id.MainActivity_NewComment_ImageView);
        ImageView mHistoricImage = findViewById(R.id.MainActivity_Historic_ImageView);
        LinearLayout mBackgroundColor = findViewById(R.id.MainActivity_Background_LinearLayout);

        mMoodImage.setImageResource(mCurrentMood.getMoodId());
        mBackgroundColor.setBackgroundResource(mCurrentMood.getColorId());


        /*
        img.setImageResource(R.drawable.ic_comment_black_48px);
        background.setBackgroundResource(R.color.colorAccent);
        */



    }
}
