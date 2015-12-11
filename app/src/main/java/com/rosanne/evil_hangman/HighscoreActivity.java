package com.rosanne.evil_hangman;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HighscoreActivity extends AppCompatActivity {

    public String name;
    public int Score;
    public int Scores;
    public String Names;
    public int curLives;

    private void filllist() {
        SharedPreferences Highscores = getSharedPreferences("Highscores", Context.MODE_PRIVATE);
        Scores = Highscores.getInt("Score", Score);
        Names = Highscores.getString("name", name);

        TextView scorelijst = (TextView) findViewById(R.id.TextViewList);
        scorelijst.append(Names + Scores);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        filllist();
    }

    /** the home and next button **/
    public void goHome(View view) {
        Intent intentHome = new Intent(this, MainActivity.class);
        startActivity(intentHome);
    }

    public void nextOne(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("lives", curLives);
        startActivity(intent);
    }
}