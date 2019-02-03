package com.sweethome.jimmy.moodtracker.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    TextView mHistory1;
    TextView mHistory2;
    TextView mHistory3;
    TextView mHistory4;
    TextView mHistory5;
    TextView mHistory6;
    TextView mHistory7;
    LinkedList<Mood> moodHistory;

    private Gson gson = new Gson();
    Bundle bundle;
    private String sMoodHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            sMoodHistory =bundle.getString("history");
        }
        Type linkedListType = new TypeToken<LinkedList<Mood>>(){}.getType();
        moodHistory = gson.fromJson(sMoodHistory, linkedListType);

        mHistory1 = findViewById(R.id.HistoryActivity_Row_TextView1);
        mHistory2 = findViewById(R.id.HistoryActivity_Row_TextView2);
        mHistory3 = findViewById(R.id.HistoryActivity_Row_TextView3);
        mHistory4 = findViewById(R.id.HistoryActivity_Row_TextView4);
        mHistory5 = findViewById(R.id.HistoryActivity_Row_TextView5);
        mHistory6 = findViewById(R.id.HistoryActivity_Row_TextView6);
        mHistory7 = findViewById(R.id.HistoryActivity_Row_TextView7);

        TextView[] tvTable = {mHistory1, mHistory2, mHistory3, mHistory4, mHistory5, mHistory6, mHistory7};
        String[] sTable = {getString(R.string.Row_TextView1), getString(R.string.Row_TextView2),
                getString(R.string.Row_TextView3), getString(R.string.Row_TextView4),
                getString(R.string.Row_TextView5), getString(R.string.Row_TextView6),
                getString(R.string.Row_TextView7)};

        if (moodHistory.size() > 0) {
            LinearLayout.LayoutParams paramsSad = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1
            );
            paramsSad.setMargins(0, 0, 480, 0);
            LinearLayout.LayoutParams paramsDisappointed = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1
            );
            paramsDisappointed.setMargins(0, 0, 360, 0);
            LinearLayout.LayoutParams paramsNormal = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1
            );
            paramsNormal.setMargins(0, 0, 240, 0);
            LinearLayout.LayoutParams paramsHappy = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1
            );
            paramsHappy.setMargins(0, 0, 120, 0);
            LinearLayout.LayoutParams paramsSuperHappy = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1
            );
            paramsSuperHappy.setMargins(0, 0, 0, 0);

            for (int i = 0; i < moodHistory.size(); i++) {
                tvTable[i].setClickable(false);
                tvTable[i].setText(sTable[i]);
                tvTable[i].setBackgroundColor(getResources().getColor(moodHistory.get(i).getColorId()));
                switch (moodHistory.get(i).getMoodName()) {
                    case "Sad":
                        tvTable[i].setLayoutParams(paramsSad);
                        break;
                    case "Disappointed" :
                        tvTable[i].setLayoutParams(paramsDisappointed);
                        break;
                    case "Normal" :
                        tvTable[i].setLayoutParams(paramsNormal);
                        break;
                    case "Happy" :
                        tvTable[i].setLayoutParams(paramsHappy);
                        break;
                    case "SuperHappy" :
                        tvTable[i].setLayoutParams(paramsSuperHappy);
                        break;
                }
                if (!moodHistory.get(i).getComment().equals("")) {
                    tvTable[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_comment_black_48px,0);
                    tvTable[i].setClickable(true);
                    final int finalI = i;
                    tvTable[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(HistoryActivity.this, moodHistory.get(finalI).getComment(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }
    }
}
