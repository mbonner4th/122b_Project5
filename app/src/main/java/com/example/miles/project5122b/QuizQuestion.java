package com.example.miles.project5122b;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

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

    public ArrayList<String> getDescriptors(){
        return descriptors;
    }

    public ArrayList<QuizAnswers> getAnswers(){
        return answers;
    }

    public  QuizQuestion(int question_code, DbAdapter db){
        this.mdb = db;
        int rnd;
        int prevRnd;
        Movie correctMovie;
        ArrayList<Movie> incorrect_movies;
        Star correctStar;
        ArrayList<Star> incorrect_stars;
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
                    answers.add(new QuizAnswers(m.getYear(),false));
                }
                descriptors.add(correctMovie.getTitle());

                break;
            case 3:
                //"Which star was in the movie X?"movie
                Log.d(DbAdapter.PROGRAM_STAMP, "creating question 3");
                correctMovie = mdb.get_movies_with_ids_where(null,1).get(0);
                descriptors.add(correctMovie.getTitle());
                //Generate the answers here
                Log.d(DbAdapter.PROGRAM_STAMP, correctMovie.toString());
                rnd = new Random().nextInt(correctMovie.getStarIDs().size());
                //select random star from movie id's
                correctStar = mdb.get_star_from_id(correctMovie.getStarIDs().get(rnd)).get(0);
                answers.add(new QuizAnswers(correctStar.getFirst_name()+" "+correctStar.getLast_name(), true));

                //find all the stars who don't have the same sid or mid
                incorrect_stars = mdb.get_stars_not_in_movie_who_arent(Integer.toString(correctMovie.getId()),Integer.toString(correctStar.getId()));
                for (Star s: incorrect_stars)
                {
                    answers.add(new QuizAnswers(s.getFirst_name()+ " " +s.getLast_name(),false));
                }


                break;
            case 4:
                Log.d(DbAdapter.PROGRAM_STAMP, "creating question 4");
                correctMovie = mdb.get_movies_with_two_or_more_stars().get(0);
                Log.d(DbAdapter.PROGRAM_STAMP, "correct movie:"+correctMovie.toString());
                //"In which movie did the stars X and Y appear together?" movie+star+star
                //randomly select the two stars

                rnd = new Random().nextInt(correctMovie.getStarIDs().size());
                prevRnd = rnd;
                Star star1 = mdb.get_star_from_id(correctMovie.getStarIDs().get(rnd)).get(0);
                while(prevRnd ==rnd){
                    rnd= new Random().nextInt(correctMovie.getStarIDs().size());
                }
                Star star2 = mdb.get_star_from_id(correctMovie.getStarIDs().get(rnd)).get(0);
                //Generate the answers here
                answers.add(new QuizAnswers(correctMovie.getTitle(),true));
                descriptors.add(star1.getFirst_name()+" "+ star1.getLast_name());
                descriptors.add(star2.getFirst_name()+" "+ star2.getLast_name());
                incorrect_movies = mdb.get_movies_where_x_and_y_dont_appear_together(Integer.toString(star1.getId()),Integer.toString(star2.getId()));
                for (Movie m: incorrect_movies)
                {
                    answers.add(new QuizAnswers(m.getTitle(),false));
                    answers.add(new QuizAnswers(m.getTitle(),false));
                    answers.add(new QuizAnswers(m.getTitle(),false));
                }

                break;
            case 5:
                //"Who directed the star X?" movie+star
                //pick a random movie
                //select a random star from that movie
                //select
                //Generate the answers here
                break;
            case 6:
                correctStar = mdb.get_stars_in_more_than_one_movie().get(0);
                descriptors.add(mdb.get_movie_from_id(correctStar.getMovieIDs().get(0)).get(0).getTitle());
                descriptors.add(mdb.get_movie_from_id(correctStar.getMovieIDs().get(1)).get(0).getTitle());
                answers.add(new QuizAnswers(correctStar.getFirst_name()+" "+correctStar.getLast_name(),true));
                //"Which star appears in both the movies X and Y?"movies+star+star
                incorrect_stars = mdb.get_stars_in_le_one_movie();
                for (Star s :incorrect_stars)
                {
                    answers.add(new QuizAnswers(s.getFirst_name()+" "+s.getLast_name(),false));
                }
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

}
