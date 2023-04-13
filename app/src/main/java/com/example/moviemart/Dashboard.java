package com.example.moviemart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moviemart.Movie;
import com.example.moviemart.db.MovieDao;
import com.example.moviemart.db.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    TextView mName;
<<<<<<< HEAD
    Button mLogoutButton, mAdminButton, mSearchButton;
=======
    Button mLogoutButton, mAdminButton;
    Button mSearchMovieButton;
>>>>>>> parent of 3c6cf7e (Revert "testing")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mName = findViewById(R.id.name);
        mLogoutButton = findViewById(R.id.logoutButton);
        mAdminButton = findViewById(R.id.adminButton);
        mSearchButton = findViewById(R.id.searchButton);
        String user = getIntent().getStringExtra("name");
        mName.setText(user);

        if (user.equals("admin2")) {
            mAdminButton.setVisibility(View.VISIBLE);
        } else {
            mAdminButton.setVisibility(View.INVISIBLE);
        }

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, SearchMovie.class));
            }
        });

        mAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, AdminActivity.class));
            }
        });

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(Dashboard.this, LoginActivity.class));
            }
        });

        mSearchMovieButton = findViewById(R.id.searchMovie);

        mSearchMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertPredefinedMovies();
                // Add code to search and display movies
            }
        });
    }

    private void insertPredefinedMovies() {
        MovieDao movieDao = UserDatabase.getUserDatabase(this).mMovieDao();
        List<Movie> predefinedMovies = new ArrayList<>();

        // Add Movie List
        predefinedMovies.add(new Movie("Movie Title 1", "Director 1", "Genre 1", 2021));
        predefinedMovies.add(new Movie("Movie Title 2", "Director 2", "Genre 2", 2020));

        new Thread(new Runnable() {
            @Override
            public void run() {
                movieDao.insertMovies(predefinedMovies);
            }
        }).start();
    }
}