package com.rosanne.evil_hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends Activity {

    private Random randomGenerator = new Random();
    private final String[] easylist = {"cat", "sun", "cup","ghost","pie","cow"};
    private String[] AllWords;
    private final ArrayList<String> easyWords = new ArrayList<String>(Arrays.asList(easylist));
    private ArrayList<String> Words;
    public int curLives;
    private ArrayList<Boolean> curAnswer;
    private String key;
    public boolean isContain;
    public int wordLengthVal;
    private TextView guessesValTextView, wordLengthTextView;



    public PlayActivity() {
    }
    /** check if the word contains the inputted letter **/
    private void inputLetter(char c)
    {

        for(int i =0; i < key.length(); ++i)
        {
            char ans = key.charAt(i);
            if(c == ans)
            {
                isContain = true;
                curAnswer.set(i, true);
                disableLetter(c);

                Log.d("test", "curLives" + curLives);
            }
            if (c != ans)
            {
                isContain = false;
                disableLetter(c);
                curLives -= 1;
                Log.d("test","curLives"+curLives);
            }
        }
    }

    /** if a letter is guessed, disable that button **/
    private void disableLetter(char c)
    {
        char C = Character.toUpperCase(c);
        String buttonID = "button" + C;
        int resID = getResources().getIdentifier(buttonID, "id", "com.rosanne.evil_hangman");
        Button b = (Button) findViewById(resID);
        b.setEnabled(false);
    }

    /** check what the current answer is **/
    private String getCurAnswer()
    {
        String result = new String();
        for (int i=0;i<curAnswer.size(); ++i)
        {
            if(curAnswer.get(i))
            {
                result += (key.charAt(i)+" ");
            }
            else{
                result += "_ ";
            }
        }
        Log.d("test", result);
        return result;
    }

    /** check how many guesses **/
    private void numGuesses() {
        SharedPreferences settings = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
        int guessesVal = settings.getInt("guesses", 8);
        curLives = guessesVal * 3;
    }

    /** choose a word to play with **/
    private void selectKey()
    {

        Resources res = getResources();
        String[] AllWords = res.getStringArray(R.array.wordsSmall);
        Words = new ArrayList<String>(Arrays.asList(AllWords));
        //key = Words.get(randomGenerator.nextInt(Words.size())).toLowerCase();


        SharedPreferences settings = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
        wordLengthVal = settings.getInt("wordLength", 4);

        // fills list with words of chosen length.
        List<String> possibleWords = new ArrayList<String>();
        for (String aWord : Words) {
            if (aWord.length() == wordLengthVal) {
                possibleWords.add(aWord);
            }
        }
        key = possibleWords.get(randomGenerator.nextInt(possibleWords.size())).toLowerCase();

        Log.d("test",key);

        curAnswer = new ArrayList<Boolean>();
        for (int i = 0; i < key.length(); i++)
        {
            curAnswer.add(false);
        }

        HashSet<Character> letterSet = new HashSet<Character>();
        for(int i=0;i<key.length();++i)
        {
            letterSet.add(key.charAt(i));
        }
        Log.d("test","curLives"+curLives);
    }

    /** check if the word is guessed/complete **/
    private void checkResult()
    {
        boolean isComplete = false;
        if (!getCurAnswer().contains("_"))
        {
            isComplete = true;
        }

        ImageView imageHanging = (ImageView) findViewById(R.id.imageHanging);
        TextView textFill = (TextView) findViewById(R.id.textFill);

        //complete
        if (isComplete)
        {
            for (int i = 0; i < 26; i++) {
                char c = (char) ('a' + i);
                disableLetter(c);
            }
            imageHanging.setImageResource(R.drawable.hangwon);
            Toast.makeText(getApplicationContext(),
                    "You won!",
                    Toast.LENGTH_LONG).show();
            textFill.setText(getCurAnswer());
            return;
        }

        //not complete
        if (!isComplete)
        {
            textFill.setText(getCurAnswer());
        }
            switch (curLives)
            {
                case 36:
                    imageHanging.setImageResource(R.drawable.hang1);
                    Toast.makeText(getApplicationContext(),
                            "12 guesses left!",
                            Toast.LENGTH_LONG).show();
                case 33:
                    imageHanging.setImageResource(R.drawable.hang1);
                    Toast.makeText(getApplicationContext(),
                            "11 guesses left!",
                            Toast.LENGTH_LONG).show();
                case 30:
                    imageHanging.setImageResource(R.drawable.hang1);
                    Toast.makeText(getApplicationContext(),
                            "10 guesses left!",
                            Toast.LENGTH_LONG).show();
                case 27:
                    imageHanging.setImageResource(R.drawable.hang1);
                    Toast.makeText(getApplicationContext(),
                            "9 guesses left!",
                            Toast.LENGTH_LONG).show();
                case 24:
                    imageHanging.setImageResource(R.drawable.hang2);
                    Toast.makeText(getApplicationContext(),
                            "8 guesses left!",
                            Toast.LENGTH_LONG).show();
                    break;
                case 21:
                    imageHanging.setImageResource(R.drawable.hang3);
                    Toast.makeText(getApplicationContext(),
                            "7 guesses left!",
                            Toast.LENGTH_LONG).show();
                    break;
                case 18:
                    imageHanging.setImageResource(R.drawable.hang4);
                    Toast.makeText(getApplicationContext(),
                            "6 guesses left!",
                            Toast.LENGTH_LONG).show();
                    break;
                case 15:
                    imageHanging.setImageResource(R.drawable.hang5);
                    Toast.makeText(getApplicationContext(),
                            "5 guesses left!",
                            Toast.LENGTH_LONG).show();
                    break;
                case 12:
                    imageHanging.setImageResource(R.drawable.hang6);
                    Toast.makeText(getApplicationContext(),
                            "4 guesses left!",
                            Toast.LENGTH_LONG).show();
                    break;
                case 9:
                    imageHanging.setImageResource(R.drawable.hang7);
                    Toast.makeText(getApplicationContext(),
                            "3 guesses left!",
                            Toast.LENGTH_LONG).show();
                    break;
                case 6:
                    imageHanging.setImageResource(R.drawable.hang8);
                    Toast.makeText(getApplicationContext(),
                            "2 guesses left!",
                            Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    imageHanging.setImageResource(R.drawable.hang9);
                    Toast.makeText(getApplicationContext(),
                            "1 guesses left!",
                            Toast.LENGTH_LONG).show();
                    break;
    }

    // game over
    if (curLives <= 0)
    {
        for (int j = 0; j < 26; j++)
        {
            char c = (char) ('a' + j);
            disableLetter(c);
        }
        imageHanging.setImageResource(R.drawable.hanglose);
        Toast.makeText(getApplicationContext(),
        "Game over!",
        Toast.LENGTH_LONG).show();
    }
}

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setContentView(R.layout.activity_play);

        TextView textLevel= (TextView)findViewById(R.id.textLevel);
        TextView textFill = (TextView)findViewById(R.id.textFill);

        numGuesses();
        selectKey();

        textFill.setText(getCurAnswer());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** check witch letter is clicked **/
    public void clickLetter(View view)
    {
        switch (view.getId())
        {
            case R.id.buttonA:  inputLetter('a');
                break;
            case R.id.buttonB:  inputLetter('b');
                break;
            case R.id.buttonC:  inputLetter('c');
                break;
            case R.id.buttonD:  inputLetter('d');
                break;
            case R.id.buttonE:  inputLetter('e');
                break;
            case R.id.buttonF:  inputLetter('f');
                break;
            case R.id.buttonG:  inputLetter('g');
                break;
            case R.id.buttonH:  inputLetter('h');
                break;
            case R.id.buttonI:  inputLetter('i');
                break;
            case R.id.buttonJ:  inputLetter('j');
                break;
            case R.id.buttonK:  inputLetter('k');
                break;
            case R.id.buttonL:  inputLetter('l');
                break;
            case R.id.buttonM:  inputLetter('m');
                break;
            case R.id.buttonN:  inputLetter('n');
                break;
            case R.id.buttonO:  inputLetter('o');
                break;
            case R.id.buttonP:  inputLetter('p');
                break;
            case R.id.buttonQ:  inputLetter('q');
                break;
            case R.id.buttonR:  inputLetter('r');
                break;
            case R.id.buttonS:  inputLetter('s');
                break;
            case R.id.buttonT:  inputLetter('t');
                break;
            case R.id.buttonU:  inputLetter('u');
                break;
            case R.id.buttonV:  inputLetter('v');
                break;
            case R.id.buttonW:  inputLetter('w');
                break;
            case R.id.buttonX:  inputLetter('x');
                break;
            case R.id.buttonY:  inputLetter('y');
                break;
            case R.id.buttonZ:  inputLetter('z');
                break;
        }
        checkResult();
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

    /** if the back button is pressed **/
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
