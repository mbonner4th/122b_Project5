package com.example.miles.project5122b;

import java.util.ArrayList;

/**
 * Created by Lawrence on 6/8/2015.
 */
public class Movie {
    private int id;
    private String title;
    private String year;
    private String director;
    private ArrayList<String> starIDs;

    public ArrayList<String> getStarIDs() {
        return starIDs;
    }

    public Movie(int id, String title, String year, String director, ArrayList<String> starIDs) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.starIDs = starIDs;
    }

    public void addMovieID(String toAdd)
    {
        this.starIDs.add(toAdd);
    }
    public void setStarIDs(ArrayList<String> starIDs) {
        this.starIDs = starIDs;
    }

    public Movie(int id, String title, String year, String director) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.starIDs = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }


    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
