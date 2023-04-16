package com.example.moviemart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviemart.db.MovieDatabase;

public class AddMovie extends AppCompatActivity {

    private EditText movieTitleEditText;
    private EditText movieYearEditText;
    private EditText movieGenreEditText;
    private EditText movieLengthEditText;
    private Button addMovieButton;
    private MovieDatabase movieDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        movieTitleEditText = findViewById(R.id.movieTitleEditText);
        movieYearEditText = findViewById(R.id.movieYearEditText);
        movieGenreEditText = findViewById(R.id.movieGenreEditText);
        movieLengthEditText = findViewById(R.id.movieLengthEditText);
        addMovieButton = findViewById(R.id.addMovieButton);

        movieDatabase = MovieDatabase.getInstance(this);

        addMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = movieTitleEditText.getText().toString();
                String genre = movieGenreEditText.getText().toString();
                int year = Integer.parseInt(movieYearEditText.getText().toString());
                int length = Integer.parseInt(movieLengthEditText.getText().toString());

                Movie movie = new Movie(title, year, genre, length);
                insertMovie(movie);
            }
        });
    }

    private void insertMovie(final Movie movie) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                movieDatabase.movieDao().insert(movie);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AddMovie.this, "Movie added successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    public void onReturnButtonClick(View view) {
        finish();
    }
}
