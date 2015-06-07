package com.example.miles.project5122b;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class StatisticsPageActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_page);
        setFeilds();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics_page, menu);
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

        TextView totalQuizesFeild = (TextView)findViewById(R.id.quizzesTaken);
        TextView correctAnswers = (TextView)findViewById(R.id.correctAnswers);
        TextView incorrectAnswers = (TextView)findViewById(R.id.incorrectAnswers);
        TextView averageTime = (TextView)findViewById(R.id.averageTime);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("GameStats", 0);

        totalQuizesFeild.setText(Integer.toString(pref.getInt("total_quizes", 0)));
        correctAnswers.setText(Integer.toString(pref.getInt("correct_answers", 0)));
        incorrectAnswers.setText(Integer.toString(pref.getInt("incorrect_answers", 0)));

        int seconds = (int)(pref.getInt("average_time", 0) / 1000);
        int minutes = seconds / 60;
        seconds     = seconds % 60;

        if (seconds < 10) {
            averageTime.setText("" + minutes + ":0" + seconds);
        } else {
            averageTime.setText("" + minutes + ":" + seconds);
        }

    }


    //all the clicking things go here
    public void mainMenu(View view)
    {
        Intent nextScreen = new Intent(getApplicationContext(), P5Game.class);

        startActivity(nextScreen);
    }

    public void clearStats(View view){
    //resets stats
        SharedPreferences pref = getApplicationContext().getSharedPreferences("GameStats", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("total_quizes", 0);
        editor.putInt("correct_answers", 0);
        editor.putInt("incorrect_answers", 0);
        editor.putInt("average_time", 0);
        editor.commit();

        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
