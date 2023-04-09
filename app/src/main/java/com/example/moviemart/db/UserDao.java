package com.example.moviemart.db;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.moviemart.User;

@Dao
public interface UserDao {

    @Insert
    void registerUser(User user);
}
