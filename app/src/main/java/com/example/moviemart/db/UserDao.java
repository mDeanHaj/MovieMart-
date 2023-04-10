package com.example.moviemart.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moviemart.User;

@Dao
public interface UserDao {

    @Insert
    void registerUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * from user_table where userId=(:userId) and password=(:password)")
    User login(String userId, String password);
}
