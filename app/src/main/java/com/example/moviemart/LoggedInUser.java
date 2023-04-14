package com.example.moviemart;

import android.content.Context;
import android.content.SharedPreferences;

public class LoggedInUser {
    private static LoggedInUser instance;
    private User user;

    private LoggedInUser() {
    }

    public static LoggedInUser getInstance() {
        if (instance == null) {
            instance = new LoggedInUser();
        }
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void saveUserIdToPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MovieMart", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("loggedInUserId", user.getId());
        editor.apply();
    }

    public int getUserIdFromPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MovieMart", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("loggedInUserId", -1);
    }
}
