package com.example.miles.project5122b;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class P5Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbAdapter db = new DbAdapter(this);
        setContentView(R.layout.activity_p5_game);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_p5_game, menu);
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

    public void startQuiz(View view)
    {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("GameStats", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("quizzes_taken", pref.getInt("quizzes_taken", 0)+1); //Storing integer

        editor.commit();

        Intent nextScreen = new Intent(getApplicationContext(), MainQuizActivity.class);

        startActivity(nextScreen);
    }

    public void openStatsPage(View view)
    {
        Intent nextScreen = new Intent(getApplicationContext(), StatisticsPageActivity.class);

        startActivity(nextScreen);
    }
}
