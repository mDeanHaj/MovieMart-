package com.example.moviemart.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviemart.MovieLog;
import com.example.moviemart.User;

@Database(entities = {MovieLog.class, User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public static final String DATABASE_NAME = "MovieLog.db";
    public static final String MOVIELOG_TABLE = "movielog_table";

    public static final String USER_TABLE = "user_table";
    private static AppDataBase appDataBase;

    public static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

//    public abstract MovieLogDAO Mo();

    public static AppDataBase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

    public static synchronized AppDataBase getAppDataBase(Context context){
        if(appDataBase == null){
            appDataBase = Room.databaseBuilder(context, AppDataBase.class, USER_TABLE)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDataBase;
    }

    public abstract MovieLogDAO mMovieLogDAO();
}
