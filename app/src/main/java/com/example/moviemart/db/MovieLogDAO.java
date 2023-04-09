package com.example.moviemart.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.moviemart.MovieLog;

@Dao
public interface MovieLogDAO {

    @Insert
    void insert(MovieLog... movieLog);

    @Update
    void update(MovieLog... movieLog);

    @Delete
    void delete(MovieLog movieLog);
}
