package com.example.moviemart.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moviemart.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void registerUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();

    @Query("SELECT * from user_table where userId=(:userId) and password=(:password)")
    User login(String userId, String password);

    @Query("SELECT * FROM user_table WHERE id = :id")
    User getUserById(int id);

    @Query("SELECT COUNT(*) FROM user_table WHERE userId = :userId")
    int countUsersWithUsername(String userId);
}
