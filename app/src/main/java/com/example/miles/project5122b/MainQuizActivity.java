package com.example.miles.project5122b;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Math;

public class MainQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_quiz_activity_layout);
        createQuestionAndAnswers();
    }

    public void createQuestionAndAnswers()
    {
        int questionToDisplay = (int)(Math.random()*7)+1;
        int correctAnswerNumber = (int)(Math.random()*4)+1;
        TextView question = (TextView)findViewById(R.id.Question);
        Button answer1 = (Button)findViewById(R.id.Answer1);
        Button answer2 = (Button)findViewById(R.id.Answer2);
        Button answer3 = (Button)findViewById(R.id.Answer3);
        Button answer4 = (Button)findViewById(R.id.Answer4);

        setOnClickListeners(answer1, answer2, answer3, answer4, correctAnswerNumber);

        switch(questionToDisplay)
        {
            case 1:
                question.setText("Who directed the movie X?");
                //Generate the answers here
                break;
            case 2:
                question.setText("When was the movie X released?");
                //Generate the answers here
                break;
            case 3:
                question.setText("Which star was in the movie X?");
                //Generate the answers here
                break;
            case 4:
                question.setText("In which movie did the stars X and Y appear together?");
                //Generate the answers here
                break;
            case 5:
                question.setText("Who directed the star X?");
                //Generate the answers here
                break;
            case 6:
                question.setText("Which star appears in both the movies X and Y?");
                //Generate the answers here
                break;
            case 7:
                question.setText("Which star did not appear in a movie with star X?");
                //Generate the answers here
                break;
            case 8:
                question.setText("Who directed the star X in year Y?");
                //Generate the answers here
                break;
        }
    }

    private void setOnClickListeners(Button answer1, Button answer2, Button answer3, Button answer4, int correctAnswerNumber)
    {
        switch(correctAnswerNumber)
        {
            case 1:
                answer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        correctAnswer();
                    }
                });
                answer2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wrongAnswer();
                    }
                });
                answer3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wrongAnswer();
                    }
                });
                answer4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wrongAnswer();
                    }
                });
                break;
            case 2:
                answer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wrongAnswer();
                    }
                });
                answer2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        correctAnswer();
                    }
                });
                answer3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wrongAnswer();
                    }
                });
                answer4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wrongAnswer();
                    }
                });
                break;
            case 3:
                answer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wrongAnswer();
                    }
                });
                answer2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wrongAnswer();
                    }
                });
                answer3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        correctAnswer();
                    }
                });
                answer4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wrongAnswer();
                    }
                });
                break;
            case 4:
                answer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wrongAnswer();
                    }
                });
                answer2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wrongAnswer();
                    }
                });
                answer3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wrongAnswer();
                    }
                });
                answer4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        correctAnswer();
                    }
                });
                break;
        }
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


    public void wrongAnswer()
    {
        Context context = getApplicationContext();
        Toast.makeText(this, "BZZZZZT WRONG", Toast.LENGTH_LONG).show();
        createQuestionAndAnswers();
    }

    public void correctAnswer()
    {
        Context context = getApplicationContext();
        Toast.makeText(this, "You answered correctly", Toast.LENGTH_LONG).show();
        createQuestionAndAnswers();
    }

}
