package com.example.miles.project5122b;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Math;
import java.util.ArrayList;

public class MainQuizActivity extends AppCompatActivity {

    private TextView mTimeLabel;
    private Handler mHandler = new Handler();
    private long mStart;
    private static final long duration = 20000;
    private DbAdapter mdb;
    private QuizQuestion question;

    private final Toast toastObject = null;

    private Runnable updateTask = new Runnable() {
        public void run() {
            long now = SystemClock.uptimeMillis();
            long elapsed = duration - (now - mStart);

            if (elapsed > 0) {
                int seconds = (int) (elapsed / 1000);
                int minutes = seconds / 60;
                seconds     = seconds % 60;

                if (seconds < 10) {
                    mTimeLabel.setText("" + minutes + ":0" + seconds);
                } else {
                    mTimeLabel.setText("" + minutes + ":" + seconds);
                }

                mHandler.postAtTime(this, now + 1000);
            }
            else {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("GameStats", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("local_average_time", (int)duration);
                editor.commit();

                mHandler.removeCallbacks(this);
                finish();

                Intent nextScreen = new Intent(getApplicationContext(), EndOfQuizScore.class);
                startActivity(nextScreen);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mdb = new DbAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_quiz_activity_layout);

        mTimeLabel = (TextView)this.findViewById(R.id.timer);
        mStart = SystemClock.uptimeMillis();
        mHandler.post(updateTask);

        createQuestionAndAnswers();
    }

    public void createQuestionAndAnswers()
    {
//        int questionToDisplay = (int)(Math.random()*7)+1;
        int questionToDisplay = (int)(Math.random()*2)+1;
//        int questionToDisplay = 2;
        int correctAnswerNumber = (int)(Math.random()*4)+1;
        TextView question = (TextView)findViewById(R.id.Question);
        Button answer1 = (Button)findViewById(R.id.Answer1);
        Button answer2 = (Button)findViewById(R.id.Answer2);
        Button answer3 = (Button)findViewById(R.id.Answer3);
        Button answer4 = (Button)findViewById(R.id.Answer4);

        String rightAnswer = "";
        ArrayList<String> wrongAnswers = new ArrayList<String>();
        questionToDisplay = 6;
        this.question = new QuizQuestion(questionToDisplay, this.mdb);

        ArrayList<QuizAnswers> MyQuizAnswers = this.question.getAnswers();
        for(QuizAnswers QA: MyQuizAnswers) {
            if (QA.isCorrectAnswer()) {
                rightAnswer = QA.getAnswer();
            } else {
                wrongAnswers.add(QA.getAnswer());
            }
        }

        ArrayList<String> descriptors = this.question.getDescriptors();

        switch(questionToDisplay)
        {
            case 1:

                question.setText("Who directed the movie "+descriptors.get(0)+"?");
                //Generate the answers here

                break;
            case 2:
                question.setText("When was the movie "+ descriptors.get(0)+" released?");
                //Generate the answers here
                break;
            case 3:
                question.setText("Which star was in the movie" +
                        " "+descriptors.get(0)+"?");
                //Generate the answers here
                break;
            case 4:
                question.setText("In which movie did the stars" +
                        " " +descriptors.get(0)+" and "+ descriptors.get(1)+" appear together?");
                //Generate the answers here
                break;
            case 5:
                question.setText("Who directed the star" +
                        " "+descriptors.get(0)+"?");
                //Generate the answers here
                break;
            case 6:
                question.setText("Which star appears in both the movies " +
                        ""+descriptors.get(0)+" and " +
                        ""+descriptors.get(1)+"?");
                //Generate the answers here
                break;
            case 7:
                question.setText("Which star did not appear in a movie with star" +
                        " "+descriptors.get(0)+"?");
                //Generate the answers here
                break;
            case 8:
                question.setText("Who directed the star" +
                        " "+descriptors.get(0)+" in year" +
                        " "+descriptors.get(1)+"?");
                //Generate the answers here
                break;
        }
        if(rightAnswer.equals("")){
            rightAnswer = "correct";
        }
        if(wrongAnswers.size() > 3) {
            wrongAnswers.add("Wrong");
            wrongAnswers.add("Wrong");
            wrongAnswers.add("Wrong");
        }
        setOnClickListeners(answer1, answer2, answer3, answer4, correctAnswerNumber, rightAnswer, wrongAnswers);
    }


//
    private void setOnClickListeners(Button answer1, Button answer2, Button answer3,
                                     Button answer4, int correctAnswerNumber,
                                     String rightAnswer, ArrayList<String> wrongAnswers)
    {
        switch(correctAnswerNumber)
        {
            case 1:
                answer1.setText(rightAnswer);
                answer2.setText(wrongAnswers.get(0));
                answer3.setText(wrongAnswers.get(1));
                answer4.setText(wrongAnswers.get(2));
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

                answer1.setText(wrongAnswers.get(0));
                answer2.setText(rightAnswer);
                answer3.setText(wrongAnswers.get(1));
                answer4.setText(wrongAnswers.get(2));

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

                answer1.setText(wrongAnswers.get(0));
                answer2.setText(wrongAnswers.get(1));
                answer3.setText(rightAnswer);
                answer4.setText(wrongAnswers.get(2));

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

                answer1.setText(wrongAnswers.get(0));
                answer2.setText(wrongAnswers.get(1));
                answer3.setText(wrongAnswers.get(2));
                answer4.setText(rightAnswer);

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

        SharedPreferences pref = getApplicationContext().getSharedPreferences("GameStats", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("incorrect_answers", pref.getInt("incorrect_answers", 0) + 1); //Storing integer
        editor.putInt("local_incorrect", pref.getInt("local_incorrect", 0) + 1 );
        editor.apply();
        Context context = getApplicationContext();
        Toast.makeText(this, "BZZZZZT WRONG", Toast.LENGTH_SHORT).show();
        createQuestionAndAnswers();
    }

    public void correctAnswer()
    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("GameStats", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("correct_answers", pref.getInt("correct_answers", 0)+1); //Storing integer
        editor.putInt("local_correct", pref.getInt("local_correct", 0) + 1 );
        editor.apply();
        Context context = getApplicationContext();
        Toast.makeText(this, "You answered correctly", Toast.LENGTH_SHORT).show();
        createQuestionAndAnswers();
    }

}
