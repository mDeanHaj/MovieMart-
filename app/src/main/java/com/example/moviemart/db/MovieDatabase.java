package com.example.moviemart.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviemart.User;

@Database(entities = {User.class}, version = 2)
public abstract class MovieDatabase extends RoomDatabase {

}