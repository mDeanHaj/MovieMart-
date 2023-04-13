package com.example.moviemart.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviemart.Login;

@Database(entities = {Login.class}, version = 1)
public abstract class LoginDatabase extends RoomDatabase {
    private static LoginDatabase instance;

    public abstract LoginDao userDao();

    public static synchronized LoginDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            LoginDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
