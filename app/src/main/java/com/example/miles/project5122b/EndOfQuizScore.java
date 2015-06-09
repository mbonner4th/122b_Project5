package com.example.miles.project5122b;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class EndOfQuizScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_quiz_score);
        setFeilds();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_end_of_quiz_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setFeilds(){
        TextView correct = (TextView)findViewById(R.id.correct);
        TextView incorrect = (TextView)findViewById(R.id.incorrect);
        TextView timeField = (TextView)findViewById(R.id.timeField);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("GameStats", 0);
        int numAverageTime = pref.getInt("average_time",0);

        int numCorrect = pref.getInt("local_correct", 0);
        int numIncorrect = pref.getInt("local_incorrect",0);
        int numLocalTime = pref.getInt("local_average_time", 0);

        correct.setText(Integer.toString(numCorrect));
        incorrect.setText(Integer.toString(numIncorrect));

        int aveSeconds = (int)((numLocalTime / 1000)/(numCorrect+numIncorrect));
        int minutes = aveSeconds / 60;
        int seconds     = aveSeconds % 60;

        if (seconds < 10) {
            timeField.setText("" + minutes + ":0" + seconds);
        } else {
            timeField.setText("" + minutes + ":" + seconds);
        }

        SharedPreferences.Editor editor = pref.edit();

        int newAverage;
        if (numAverageTime == 0){
            newAverage = aveSeconds*1000;
        }
        else {
            newAverage = (numAverageTime + aveSeconds*1000)/2;
        }

        editor.putInt("average_time", newAverage);
        editor.commit();
        //.//
    }

    public void onClick(View view){

        SharedPreferences pref = getApplicationContext().getSharedPreferences("GameStats", 0);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt("local_average_time", 0);
        editor.putInt("local_correct", 0);
        editor.putInt("local_incorrect", 0);

        editor.commit();
        Intent nextScreen;

        switch (view.getId()){

            case R.id.mainMenu:
                nextScreen = new Intent(getApplicationContext(), P5Game.class);
                startActivity(nextScreen);
                break;

            case R.id.playAgain:
                nextScreen = new Intent(getApplicationContext(), MainQuizActivity.class);
                startActivity(nextScreen);
                break;
        }
    }
}
