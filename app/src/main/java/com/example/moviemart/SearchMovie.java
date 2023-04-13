package com.example.moviemart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.moviemart.db.MovieDatabase;

import java.util.List;

public class SearchMovie extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private MovieDatabase movieDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);

        movieDatabase = MovieDatabase.getInstance(this);
        MovieDatabaseInitializer.populateAsync(movieDatabase);

        new Thread(() -> {
            List<Movie> movies = movieDatabase.movieDao().getAllMovies();
            runOnUiThread(() -> adapter.setMovies(movies));
        }).start();
    }
}
