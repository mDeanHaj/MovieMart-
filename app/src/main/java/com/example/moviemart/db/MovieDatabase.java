package com.example.moviemart.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviemart.Movie;
import com.example.moviemart.Order;
import com.example.moviemart.User;

@Database(entities = {Movie.class, User.class, Order.class}, version = 3)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
    public abstract UserDao userDao();
    public abstract OrderDao orderDao();

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
