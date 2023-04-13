package com.example.moviemart.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.moviemart.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<Movie> movies);

    @Query("SELECT * FROM movie WHERE title LIKE :searchQuery")
    List<Movie> searchMovies(String searchQuery);

    @Query("SELECT * FROM movie")
    List<Movie> getAllMovies();
}
