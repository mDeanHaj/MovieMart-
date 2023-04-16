package com.example.moviemart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.moviemart.db.MovieDatabase;

import java.util.List;

public class RemoveMovie extends AppCompatActivity implements MovieAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private MovieDatabase movieDatabase;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_movie);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(RemoveMovie.this);
        builder.setTitle(selectedMovie.getTitle());
        builder.setMessage("Do you want to remove this movie from the database?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Thread(() -> {
                    movieDatabase.movieDao().deleteMovieById(selectedMovie.getId());
                    runOnUiThread(() -> {
                        movies.remove(position);
                        adapter.notifyItemRemoved(position);
                        Toast.makeText(RemoveMovie.this, "Movie removed successfully!", Toast.LENGTH_SHORT).show();
                    });
                }).start();
            }
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            Toast.makeText(RemoveMovie.this, "Movie not removed", Toast.LENGTH_SHORT).show();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void onReturnButtonClick(View view) {
        finish();
    }
}
