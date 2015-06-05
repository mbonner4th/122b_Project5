package com.example.miles.project5122b;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_quiz_activity_layout);
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


    public void wrongAnswer(View view)
    {
        Context context = getApplicationContext();
        Toast.makeText(this, "BZZZZZT WRONG", Toast.LENGTH_LONG).show();
    }

    public void correctAnswer(View view)
    {
        Context context = getApplicationContext();
        Toast.makeText(this, "You answered correctly", Toast.LENGTH_LONG).show();
    }

}
