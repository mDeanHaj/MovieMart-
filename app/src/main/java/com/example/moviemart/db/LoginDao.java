package com.example.moviemart.db;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.moviemart.Login;

@Dao
public interface LoginDao {
    @Insert
    void insert(Login login);
}
