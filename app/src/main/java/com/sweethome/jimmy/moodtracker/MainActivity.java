package com.sweethome.jimmy.moodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img = (ImageView) findViewById(R.id.MainActivity_Smiley_ImageView);
        LinearLayout background = (LinearLayout) findViewById(R.id.MainActivity_Background_LinearLayout);



        img.setImageResource(R.drawable.ic_comment_black_48px);
        background.getResources().getColor(R.color.light_sage);
    }
}
