package com.sweethome.jimmy.moodtracker.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sweethome.jimmy.moodtracker.model.Mood;
import com.sweethome.jimmy.moodtracker.model.MoodBank;
import com.sweethome.jimmy.moodtracker.R;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetectorCompat detectGesture;

    private ImageView mMoodImage;
    ImageView mAddCommentImage;
    ImageView mHistoryImage;
    private LinearLayout mBackgroundColor;

    Date date;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy" , Locale.getDefault());

    private MoodBank mMoodBank = new MoodBank();
    private int currentMoodIndex;
    public LinkedList<Mood> moodHistory;

    private Mood mCurrentMood;

    private SharedPreferences sharedPref;

    MediaPlayer mp;

    Gson gson = new Gson();

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

        mAddCommentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(MainActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Commentaire")
                        .setView(editText);
                if (mCurrentMood != null) {
                    if (!mCurrentMood.getComment().equals("")) {
                        editText.setText(mCurrentMood.getComment());
                        editText.setSelection(editText.getText().length());
                    }
                }
                builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!editText.getText().toString().equals("")){
                            mCurrentMood.setComment(editText.getText().toString());
                        }
                        closeContextMenu();
                    }
                })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                closeContextMenu();
                            }
                        })
                        .create()
                        .show();
            }
        });

        mHistoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMoodToMoodHistory();
                sharedPref.edit().putString("KEY_MOOD_HISTORY_LIST", gson.toJson(moodHistory)).apply();
                sharedPref.edit().putString("KEY_LAST_MOOD_OBJECT", gson.toJson(mCurrentMood)).apply();
                Intent activityChangeIntent = new Intent(MainActivity.this, HistoryActivity.class);
                activityChangeIntent.putExtra("history", sharedPref.getString("KEY_MOOD_HISTORY_LIST", ""));
                MainActivity.this.startActivity(activityChangeIntent);
            }
        });

        setSimpleDateFormat();
        String sList = sharedPref.getString("KEY_MOOD_HISTORY_LIST", "");
        System.out.println("Dabugage : sList" + sharedPref.getString("KEY_MOOD_HISTORY_LIST", ""));
        if (sList.equals("")) {
            moodHistory = new LinkedList<>();
            mCurrentMood = new Mood(mMoodBank.getMoodTable()[currentMoodIndex], date);
            System.out.println("Dabugage : if list true");
        } else {
            Type linkedListType = new TypeToken<LinkedList<Mood>>(){}.getType();
            moodHistory = gson.fromJson(sList, linkedListType);
            System.out.println("Dabugage : if list false");
            //System.out.println("Dabugage :" + moodHistory.getFirst().getDate());
            mCurrentMood = gson.fromJson(sharedPref.getString("KEY_LAST_MOOD_OBJECT", ""), Mood.class);

            currentMoodIndex = -1;
            for (Mood aMood : mMoodBank.getMoodTable()) {
                currentMoodIndex++;
                System.out.println("Dabugage :" + currentMoodIndex);
                if (mCurrentMood.getMoodName().equals(aMood.getMoodName())) {
                    return;
                }
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSimpleDateFormat();
        if (date.getTime() - mCurrentMood.getDate().getTime() >= 86400000) {
            addMoodToMoodHistory();
            currentMoodIndex = 3;
            mCurrentMood = new Mood(mMoodBank.getMoodTable()[currentMoodIndex], date);
        }
        updateCurrentMoodOnScreen();
    }

    @Override
    protected void onPause() {
        super.onPause();
        addMoodToMoodHistory();
        sharedPref.edit().putString("KEY_MOOD_HISTORY_LIST", gson.toJson(moodHistory)).apply();
        sharedPref.edit().putString("KEY_LAST_MOOD_OBJECT", gson.toJson(mCurrentMood)).apply();
    }

    private void addMoodToMoodHistory() {
        if (date.getTime() - mCurrentMood.getDate().getTime() >= 86400000) {
            moodHistory.addFirst(mCurrentMood);
        } else if (moodHistory.size() > 0) {
            if (mCurrentMood.getDate().getTime() - moodHistory.getFirst().getDate().getTime() > 86400000) {
                int nbDayToAddDefaultMood = (int) (mCurrentMood.getDate().getTime() - moodHistory.getFirst().getDate().getTime());
                nbDayToAddDefaultMood = nbDayToAddDefaultMood / 86400000;
                nbDayToAddDefaultMood--;
                if (nbDayToAddDefaultMood >= 1) {
                    if (nbDayToAddDefaultMood > 7) {
                        nbDayToAddDefaultMood = 6;
                    }
                    for (int j = nbDayToAddDefaultMood; j > 0; j--) {
                        Date d = new Date();
                        d.setTime(date.getTime() - (86400000 * j));
                        moodHistory.addFirst(new Mood(mMoodBank.getMoodTable()[3], d));
                    }
                }
            }
        }
        if ( moodHistory.size() > 7) {
            for (int j = moodHistory.size() ; j > 7 ; j--) {
                moodHistory.removeLast();
            }
        }
    }

    private void setSimpleDateFormat() {
        date = new Date();
        String sDate = dateFormatter.format(date);
        try {
            date = dateFormatter.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the current mood on screen and saves it
     */
    private void setCurrentMood() {
        mCurrentMood.setColorId(mMoodBank.getMoodTable()[currentMoodIndex].getColorId());
        mCurrentMood.setMoodId(mMoodBank.getMoodTable()[currentMoodIndex].getMoodId());
        mCurrentMood.setMoodName(mMoodBank.getMoodTable()[currentMoodIndex].getMoodName());
        mCurrentMood.setSoundId(mMoodBank.getMoodTable()[currentMoodIndex].getSoundId());
    }

    /**
     * Update the ImageView and the background color of the Layout
     * so it matches the current mood
     */
    private void updateCurrentMoodOnScreen() {
        mMoodImage.setImageResource(mCurrentMood.getMoodId());
        mBackgroundColor.setBackgroundResource(mCurrentMood.getColorId());
        Toast.makeText(this, date.toString(), Toast.LENGTH_LONG).show();
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
            setCurrentMood();
            updateCurrentMoodOnScreen();
            mp = MediaPlayer.create(this, mCurrentMood.getSoundId());
            mp.start();
        }
        if (e1.getRawY() < e2.getRawY() && currentMoodIndex > 0) {
            currentMoodIndex--;
            setCurrentMood();
            updateCurrentMoodOnScreen();
            mp = MediaPlayer.create(this, mCurrentMood.getSoundId());
            mp.start();
        }

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detectGesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}