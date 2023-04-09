package com.example.moviemart.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moviemart.User;

@Dao
public interface UserDao {

    @Insert
    void registerUser(User user);

    @Query("SELECT * from user_table where userId=(:userId) and password=(:password)")
    User login(String userId, String password);
}
