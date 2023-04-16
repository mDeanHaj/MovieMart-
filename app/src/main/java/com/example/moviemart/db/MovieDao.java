package com.example.moviemart.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moviemart.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    void insert(Movie movie);

    @Query("SELECT * FROM movie_table ORDER BY title COLLATE NOCASE ASC")
    List<Movie> getAllMovies();

    @Query("SELECT COUNT(*) FROM movie_table WHERE title = :title AND year = :year AND genre = :genre")
    int countMovieWithTitleYearGenre(String title, int year, String genre);

    @Query("SELECT * FROM movie_table WHERE id = :id")
    Movie getMovieById(int id);
}
