package com.example.moviemart.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviemart.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static MovieDatabase instance;

    public static synchronized MovieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MovieDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
