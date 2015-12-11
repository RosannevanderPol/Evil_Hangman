package com.rosanne.evil_hangman;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HighscoreActivity extends AppCompatActivity {

    public String name;
    public int Score;

    private void filllist() {
        SharedPreferences settings = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
        SharedPreferences Highscores = getSharedPreferences("Highscores", Context.MODE_PRIVATE);
        int nameList = Highscores.getInt(String.valueOf(name), Score);

        TextView scorelijst = (TextView) findViewById(R.id.TextViewList);
        scorelijst.setText(String.valueOf(nameList));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        filllist();
    }
}