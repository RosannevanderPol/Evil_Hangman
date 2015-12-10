package com.rosanne.evil_hangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /** make the buttons go to the right places **/
    public void startGame(View view) {
        Intent start = new Intent(this, PlayActivity.class);

        startActivity(start);
    }

    public void startSettings (View view) {
        Intent settings = new Intent(this, SettingsActivity.class);
        startActivity(settings);
    }

    public void startHighscore(View view) {
        Toast.makeText(getApplicationContext(),
                "Sorry! Not implemented jet.",
                Toast.LENGTH_LONG).show();
    }

    public void EndApp(View view) {
        Toast.makeText(getApplicationContext(),
                "See you next time!",
                Toast.LENGTH_LONG).show();
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
