package com.example.miles.project5122b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter extends SQLiteOpenHelper {

    private static final String DATABASE_NAME       = "quizDb";
    private static final int    DATABASE_VERSION    = 1;
    private static final String PROGRAM_STAMP   = "122B_QUIZ_GAME";
    private static final String DROP_IF_EXISTS  = "DROP TABLE IF EXISTS ";
    private static final String CT          = " CREATE TABLE ";
    private static final String PRIMARY_ID  = "_id";
    private static final String TNN         = " TEXT NOT NULL";
    private static final String END         = ";";
    private static final String IPKA        = " INTEGER PRIMARY KEY";
    private static final String FK          = " FOREIGN KEY ";
    private static final String RF          = " REFERENCES ";
    private static final String INT         = " INTEGER ";

    //STARS
    private static final String STAR_FILE_NAME      = "stars.csv";
    private static final String STARS_TABLE_NAME    = "stars";
    private static final String FIRST_NAME  = "first_name";
    private static final String LAST_NAME   = "last_name";
    private static final String DOB         = "dob";
    private static final String CREATE_STARS_TABLE =
            CT + STARS_TABLE_NAME +
                    enclose(
                            PRIMARY_ID + IPKA + ", "
                            + FIRST_NAME + TNN + ", "
                            + LAST_NAME  + TNN + ", "
                            + DOB + TNN)
                    + END;


    //MOVIES
    private static final String MOVIE_FILE_NAME     = "movies.csv";
    private static final String MOVIES_TABLE_NAME   = "movies";
    private static final String TITLE       = "title";
    private static final String YEAR        = "year";
    private static final String DIRECTOR    = "director";
    private static final String CREATE_MOVIES_TABLE =
            CT + MOVIES_TABLE_NAME +
                    enclose(
                            PRIMARY_ID  + IPKA + ", "
                            + TITLE     + TNN  + ", "
                            + YEAR      + TNN  + ", "
                            + DIRECTOR  + TNN)
                    + END;


    //STARS_IN_MOVIES
    private static final String STARS_IN_MOVIES_FILE_NAME   = "stars_in_movies.csv";
    private static final String STARS_IN_MOVIES_TABLE_NAME  = "stars_in_movies";
    private static final String STAR_ID_NAME    = "star_id";
    private static final String MOVIE_ID_NAME   = "movie_id";
    private static final String CREATE_STARS_IN_MOVIES_TABLE =
            CT + STARS_IN_MOVIES_TABLE_NAME +
                    enclose(
                            STAR_ID_NAME    + INT + ", "
                            + MOVIE_ID_NAME + INT + ", "
                            + FK + enclose(STAR_ID_NAME)  + RF + STARS_TABLE_NAME  + enclose(PRIMARY_ID) + ", "
                            + FK + enclose(MOVIE_ID_NAME) + RF + MOVIES_TABLE_NAME + enclose(PRIMARY_ID) + ", "
                            + "PRIMARY KEY" + enclose(STAR_ID_NAME + ", " + MOVIE_ID_NAME)
                    ) + END;


    private SQLiteDatabase mDb;
    private Context mContext;

    public static String enclose(String str_to_enclose) {
        return "( " + str_to_enclose + " )";
    }

    public DbAdapter(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = ctx;

        this.mDb = getWritableDatabase();
        onCreate(mDb);
    }

    private void initDb()
    {
        this.mDb = null;
        boolean dbExists;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        createStarsTable(db);
        createMoviesTable(db);
        createStarsInMoviesTable(db);

        // populate database
        ArrayList<String> starsColumns = new ArrayList<String>(){{add(PRIMARY_ID);  add(FIRST_NAME);add(LAST_NAME); add(DOB);}};
        readFileToDatabase(STAR_FILE_NAME, STARS_TABLE_NAME,starsColumns ,db);

        ArrayList<String> moviesColumns = new ArrayList<String>(){{add(PRIMARY_ID); add(TITLE);     add(YEAR);      add(DIRECTOR);}};
        readFileToDatabase(MOVIE_FILE_NAME, MOVIES_TABLE_NAME,moviesColumns ,db);

        ArrayList<String> starsInMoviesColumns = new ArrayList<String>(){{add(STAR_ID_NAME);   add(MOVIE_ID_NAME);}};
        readFileToDatabase(STARS_IN_MOVIES_FILE_NAME, STARS_IN_MOVIES_TABLE_NAME, starsInMoviesColumns ,db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTableIfExists(db, STARS_TABLE_NAME);
        dropTableIfExists(db, MOVIES_TABLE_NAME);
        dropTableIfExists(db, STARS_IN_MOVIES_TABLE_NAME);
        onCreate(db);
    }


    private void readFileToDatabase(String filename, String tableName, ArrayList<String> columnNames, SQLiteDatabase db) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(mContext.getAssets().open(filename)));
            String line;
            //skips the comment line at the beginning of the file
            in.readLine();
            while ((line = in.readLine()) != null) {
                ContentValues values = new ContentValues();
                ArrayList<String>  lineCols= new ArrayList<>(Arrays.asList(line.split(",")));
                for ( int i=0; i<columnNames.size(); ++i){
                    values.put(columnNames.get(i), lineCols.get(i).replaceAll("\"", ""));
                }
                db.insert(tableName, null, values);
            }
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }


    private void createStarsTable(SQLiteDatabase db) {
        dropTableIfExists(db, STARS_TABLE_NAME);
        Log.d(PROGRAM_STAMP,"Creating Stars table:\n" + CREATE_STARS_TABLE);
        wrapExecuteSql(db, CREATE_STARS_TABLE);
    }


    private void createMoviesTable(SQLiteDatabase db) {
        dropTableIfExists(db, MOVIES_TABLE_NAME);
        Log.d(PROGRAM_STAMP, "Creating movies table:\n" + CREATE_MOVIES_TABLE);
        wrapExecuteSql(db, CREATE_MOVIES_TABLE);
    }


    private void createStarsInMoviesTable(SQLiteDatabase db) {
        dropTableIfExists(db, STARS_IN_MOVIES_TABLE_NAME);
        Log.d(PROGRAM_STAMP, "Creating Stars in movies table:\n" + CREATE_STARS_IN_MOVIES_TABLE);
        wrapExecuteSql(db, CREATE_STARS_IN_MOVIES_TABLE);
    }


    private void dropTableIfExists(SQLiteDatabase db, String table_to_drop)
    {
        Log.d(PROGRAM_STAMP, "Dropping:" + table_to_drop);
        wrapExecuteSql(db, "DROP TABLE IF EXISTS " + table_to_drop);
    }

    private void wrapExecuteSql(SQLiteDatabase db, String SQL)
    {
        try {
            db.execSQL(SQL);
        } catch (Exception e) {
            Log.e(PROGRAM_STAMP,"Error:\n" + e.toString());
        }
    }

    //Who directed the movie X?


}
