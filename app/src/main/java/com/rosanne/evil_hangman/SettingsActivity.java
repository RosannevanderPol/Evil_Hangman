package com.rosanne.evil_hangman;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsActivity extends Activity {

    private static SeekBar wordLengthBar, guessesBar;
    private static Switch EvilModeSwitch;
    private TextView guessesValTextView, wordLengthTextView;
    private int guessesVal, wordLengthVal;
    private boolean EvilGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        wordLengthBar = (SeekBar)findViewById(R.id.wordLengthSlider);
        guessesBar = (SeekBar)findViewById(R.id.guessesSlider);
        wordLengthBar = (SeekBar)findViewById(R.id.wordLengthSlider);
        EvilModeSwitch = (Switch)findViewById(R.id.evilModeSwitch);
        guessesValTextView = (TextView)findViewById(R.id.guessesValTextView);
        wordLengthTextView = (TextView)findViewById(R.id.wordLengthTextView);

        SharedPreferences settings = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
        guessesVal = settings.getInt("guesses", 8);
        wordLengthVal = settings.getInt("wordLength", 4);
        EvilGame = settings.getBoolean("EvilGame", false);

        wordLengthBar.setProgress(wordLengthVal);
        guessesBar.setProgress(guessesVal);
        EvilModeSwitch.setChecked(EvilGame);
        guessesValTextView.setText(String.valueOf(guessesVal));
        wordLengthTextView.setText(String.valueOf(wordLengthVal));

        guessesBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    progress = 1;
                }
                String progressValString = String.valueOf(progress);
                guessesValTextView.setText(progressValString);
                guessesVal = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        wordLengthBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    progress = 1;
                }
                String progressValString = String.valueOf(progress);
                wordLengthTextView.setText(progressValString);
                wordLengthVal = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void SaveButtonOnClick(View view) {
        if(EvilModeSwitch.isChecked()) {
            EvilGame = true;
        }
        else {
            EvilGame = false;
        }
        SharedPreferences settings = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= settings.edit();
        editor.putInt("guesses", guessesVal);
        editor.putInt("wordLength", wordLengthVal);
        editor.putBoolean("EvilGame", EvilGame);
        editor.commit();
        finish();
    }

    /** if the back button is pressed **/
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

