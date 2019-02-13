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
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ArrayList<Mood> moodHistory = new ArrayList<>(7);

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
            sMoodHistory = bundle.getString("history");
        }
        Type ArrayListType = new TypeToken<ArrayList<Mood>>(){}.getType();
        moodHistory = gson.fromJson(sMoodHistory, ArrayListType);

        String[] sTable = {getString(R.string.Row_TextView7), getString(R.string.Row_TextView6),
                getString(R.string.Row_TextView5), getString(R.string.Row_TextView4),
                getString(R.string.Row_TextView3), getString(R.string.Row_TextView2),
                getString(R.string.Row_TextView1)};

        if (!moodHistory.isEmpty()) {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;

            for (int i = 0; i < moodHistory.size(); i++) {

                if (moodHistory.get(i) == null) {
                    TextView historyTv = new TextView(this);
                    historyTv.setClickable(false);
                    LinearLayout.LayoutParams paramsSuperHappy = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            0,
                            1
                    );
                    historyTv.setLayoutParams(paramsSuperHappy);
                    linearLayout.addView(historyTv);
                } else {
                    TextView historyTv = new TextView(this);
                    historyTv.setClickable(false);
                    historyTv.setText(sTable[i]);
                    historyTv.setBackgroundColor(getResources().getColor(moodHistory.get(i).getColorId()));
                    historyTv.setPadding(0, 0, (int) (size.x * 0.05), 0);
                    historyTv.setTextSize(18);
                    historyTv.setTypeface(null, Typeface.BOLD);
                    historyTv.setTextColor(Color.BLACK);
                    switch (moodHistory.get(i).getMoodName()) {
                        case "Sad":
                            LinearLayout.LayoutParams paramsSad = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    0,
                                    1
                            );
                            paramsSad.setMargins(0, 0, (int) (width * 0.8), 0);
                            historyTv.setLayoutParams(paramsSad);
                            break;
                        case "Disappointed":
                            LinearLayout.LayoutParams paramsDisappointed = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    0,
                                    1
                            );
                            paramsDisappointed.setMargins(0, 0, (int) (width * 0.6), 0);
                            historyTv.setLayoutParams(paramsDisappointed);
                            break;
                        case "Normal":
                            LinearLayout.LayoutParams paramsNormal = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    0,
                                    1
                            );
                            paramsNormal.setMargins(0, 0, (int) (width * 0.4), 0);
                            historyTv.setLayoutParams(paramsNormal);
                            break;
                        case "Happy":
                            LinearLayout.LayoutParams paramsHappy = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    0,
                                    1
                            );
                            paramsHappy.setMargins(0, 0, (int) (width * 0.2), 0);
                            historyTv.setLayoutParams(paramsHappy);
                            break;
                        case "SuperHappy":
                            LinearLayout.LayoutParams paramsSuperHappy = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    0,
                                    1
                            );
                            historyTv.setLayoutParams(paramsSuperHappy);
                            break;
                    }
                    if (!moodHistory.get(i).getComment().equals("")) {
                        historyTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_comment_black_48px, 0);
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
}
