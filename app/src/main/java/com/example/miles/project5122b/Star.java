package com.example.miles.project5122b;

import java.util.ArrayList;

/**
 * Created by Lawrence on 6/8/2015.
 */
public class Star {

    private int id;
    private String first_name;
    private String last_name;
    private String dob;
    private ArrayList<String> movieIDs;

    public ArrayList<String> getMovieIDs() {
        return movieIDs;
    }

    public void addMovieId(String toAdd)
    {
        this.movieIDs.add(toAdd);
    }
    
    public void setMovieIDs(ArrayList<String> movieIDs) {
        this.movieIDs = movieIDs;
    }

    public Star(int id, String first_name, String last_name, String dob) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
    }

    public Star(int id, String first_name, String last_name, String dob, ArrayList<String> movieIDs) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.movieIDs = movieIDs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Star{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
