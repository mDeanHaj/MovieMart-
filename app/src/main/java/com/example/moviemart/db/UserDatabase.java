package com.example.moviemart.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviemart.Movie;
import com.example.moviemart.User;

@Database(entities = {User.class, Movie.class}, version = 3)
public abstract class UserDatabase extends RoomDatabase {

    private static final String dbName = "user";
    private static UserDatabase userDatabase;

    public static synchronized UserDatabase getUserDatabase(Context context) {
        if (userDatabase == null) {
            userDatabase = Room.databaseBuilder(context, UserDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return userDatabase;
    }

    public abstract UserDao mUserDao();
    public abstract MovieDao mMovieDao();

    public void updateUser(User user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mUserDao().updateUser(user);
            }
        }).start();
    }

    public void deleteUser(User user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mUserDao().deleteUser(user);
            }
        }).start();
    }


}
