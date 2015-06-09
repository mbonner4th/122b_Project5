package com.example.miles.project5122b;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Lawrence on 6/8/2015.
 */
public class QuizQuestion
{
    private static final int NUMBER_OF_CORRECT_ANSWERS  = 1;
    private static final int NUMBER_OF_ANSWERS          = 4;

    private ArrayList<String> descriptors = new ArrayList<>();
    private ArrayList<QuizAnswers> answers = new ArrayList<>();
    private DbAdapter mdb;

    public  QuizQuestion(int question_code, DbAdapter db){
        this.mdb = db;

        Movie correctMovie;
        ArrayList<Movie> incorrect_movies;
        switch(question_code)
        {
            case 1:
                //"Who directed the movie X?"movie
                correctMovie = mdb.get_movies_where(null,1).get(0);
                answers.add(new QuizAnswers(correctMovie.getDirector(), true));
                incorrect_movies = mdb.get_movies_not_directed_by(correctMovie.getDirector());
                for (Movie m: incorrect_movies)
                {
                    answers.add(new QuizAnswers(m.getDirector(),false));
                }
                descriptors.add(correctMovie.getTitle());

                break;
            case 2:
                //"When was the movie X released?"movie
                correctMovie = mdb.get_movies_where(null,1).get(0);
                answers.add(new QuizAnswers(correctMovie.getYear(), true));
                incorrect_movies = mdb.get_movies_not_from_the_year(correctMovie.getYear());
                for (Movie m: incorrect_movies)
                {
                    answers.add(new QuizAnswers(m.getDirector(),false));
                }
                descriptors.add(correctMovie.getTitle());

                break;
            case 3:
                //"Which star was in the movie X?"movie
                //Generate the answers here
                break;
            case 4:
                //"In which movie did the stars X and Y appear together?" movie+star+star
                //Generate the answers here
                break;
            case 5:
                //"Who directed the star X?" movie+star
                //Generate the answers here
                break;
            case 6:
                //"Which star appears in both the movies X and Y?"movies+star+star
                //Generate the answers here
                break;
            case 7:
                //"Which star did not appear in a movie with star X?"Star+star
                //Generate the answers here
                break;
            case 8:
                //"Who directed the star X in year Y?"movie+star
                //Generate the answers here
                break;
        }
    }

    private void who_directed_movie()
    {

        //get correct answer and descriptors
        //set correct answer and descriptors

        //get 3 incorrect answers
        //set 3 incorrect answers

    }

    private void when_was_movie_released()
    {

    }

    private void which_star_was_in_movie()
    {

    }

    private void in_which_movie_did_x_and_y_star()
    {

    }

    private void who_directed_star_x()
    {

    }

    private void which_star_appears_in_x_and_y()
    {

    }

    private void which_star_didnt_star_with_x()
    {

    }

    private void who_directed_star_x_in_year_y()
    {

    }
}
