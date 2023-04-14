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
            instance.prepopulateDatabase(context);
        }
        return instance;
    }

    private void prepopulateDatabase(Context context) {
        UserDao userDao = userDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String adminUsername = "admin2";
                String testUserUsername = "testuser1";

                if (userDao.login(adminUsername, adminUsername) == null) {
                    User admin = new User();
                    admin.setUserId(adminUsername);
                    admin.setPassword(adminUsername);
                    userDao.registerUser(admin);
                }
                if (userDao.login(testUserUsername, testUserUsername) == null) {
                    User testUser = new User();
                    testUser.setUserId(testUserUsername);
                    testUser.setPassword(testUserUsername);
                    userDao.registerUser(testUser);
                }
            }
        }).start();
    }
}
