package com.sweethome.jimmy.moodtracker.controller;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sweethome.jimmy.moodtracker.R;
import com.sweethome.jimmy.moodtracker.model.Mood;

import java.lang.reflect.Type;
import java.util.LinkedList;

public class HistoryActivity extends AppCompatActivity {

    LinkedList<Mood> moodHistory;

    private Gson gson = new Gson();
    Bundle bundle;
    private String sMoodHistory;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        linearLayout = findViewById(R.id.HistoryActivity_LinearLayout);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            sMoodHistory =bundle.getString("history");
        }
        Type linkedListType = new TypeToken<LinkedList<Mood>>(){}.getType();
        moodHistory = gson.fromJson(sMoodHistory, linkedListType);

        String[] sTable = {getString(R.string.Row_TextView1), getString(R.string.Row_TextView2),
                getString(R.string.Row_TextView3), getString(R.string.Row_TextView4),
                getString(R.string.Row_TextView5), getString(R.string.Row_TextView6),
                getString(R.string.Row_TextView7)};

        if (moodHistory.size() > 0) {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            LinearLayout.LayoutParams paramsSad = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1
            );
            paramsSad.setMargins(0, 0, (int) (width*0.8), 0);
            LinearLayout.LayoutParams paramsDisappointed = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1
            );
            paramsDisappointed.setMargins(0, 0, (int) (width*0.6), 0);
            LinearLayout.LayoutParams paramsNormal = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1
            );
            paramsNormal.setMargins(0, 0, (int) (width*0.4), 0);
            LinearLayout.LayoutParams paramsHappy = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1
            );
            paramsHappy.setMargins(0, 0, (int) (width*0.2), 0);
            LinearLayout.LayoutParams paramsSuperHappy = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1
            );

            for (int i = 0; i < moodHistory.size(); i++) {

                TextView historyTv = new TextView(this);
                historyTv.setClickable(false);
                historyTv.setText(sTable[i]);
                historyTv.setBackgroundColor(getResources().getColor(moodHistory.get(i).getColorId()));
                historyTv.setPadding(0, 0, 15, 0);
                historyTv.setTextSize(18);
                historyTv.setTypeface(null, Typeface.BOLD);
                historyTv.setTextColor(Color.BLACK);
                switch (moodHistory.get(i).getMoodName()) {
                    case "Sad":
                        historyTv.setLayoutParams(paramsSad);
                        break;
                    case "Disappointed" :
                        historyTv.setLayoutParams(paramsDisappointed);
                        break;
                    case "Normal" :
                        historyTv.setLayoutParams(paramsNormal);
                        break;
                    case "Happy" :
                        historyTv.setLayoutParams(paramsHappy);
                        break;
                    case "SuperHappy" :
                        historyTv.setLayoutParams(paramsSuperHappy);
                        break;
                }
                if (!moodHistory.get(i).getComment().equals("")) {
                    historyTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_comment_black_48px,0);
                    historyTv.setClickable(true);
                    final int finalI = i;
                    historyTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(HistoryActivity.this, moodHistory.get(finalI).getComment(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                linearLayout.addView(historyTv);
            }
        }
    }
}
