package com.example.moviemart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.moviemart.db.MovieDatabase;

import java.util.List;

public class SearchMovie extends AppCompatActivity implements MovieAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private MovieDatabase movieDatabase;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new MovieAdapter(this, this);
        recyclerView.setAdapter(adapter);

        movieDatabase = MovieDatabase.getInstance(this);
        MovieDatabaseInitializer.populateAsync(movieDatabase);

        new Thread(() -> {
            movies = movieDatabase.movieDao().getAllMovies();
            runOnUiThread(() -> adapter.setMovies(movies));
        }).start();
    }

    @Override
    public void onItemClick(int position) {
        Movie selectedMovie = movies.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order Movie");
        builder.setMessage("Would you like to order " + selectedMovie.getTitle() + "?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            // Perform action when the user clicks "Yes"
            Toast.makeText(SearchMovie.this, "You have ordered " + selectedMovie.getTitle(), Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            // Perform action when the user clicks "No"
            Toast.makeText(SearchMovie.this, "You have not ordered " + selectedMovie.getTitle(), Toast.LENGTH_SHORT).show();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
