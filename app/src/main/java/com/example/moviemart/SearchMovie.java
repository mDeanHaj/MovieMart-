package com.example.moviemart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.moviemart.Order;

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

        int loggedInUserId = LoggedInUser.getInstance().getUserIdFromPreferences(this);
        if (loggedInUserId != -1) {
            new Thread(() -> {
                User user = movieDatabase.userDao().getUserById(loggedInUserId);
                LoggedInUser.getInstance().setUser(user);
            }).start();
        }

        new Thread(() -> {
            movies = movieDatabase.movieDao().getAllMovies();
            runOnUiThread(() -> adapter.setMovies(movies));
        }).start();
    }

    @Override
    public void onItemClick(int position) {
        Movie selectedMovie = movies.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(SearchMovie.this);
        builder.setTitle("Purchase Movie");
        builder.setMessage("Do you want to buy this movie?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        User loggedInUser = LoggedInUser.getInstance().getUser();
                        int loggedInUserId = loggedInUser.getId();
                        Order order = new Order();
                        order.setUserId(loggedInUserId);
                        order.setMovieId(selectedMovie.getId());
                        movieDatabase.orderDao().insertOrder(order);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SearchMovie.this, "Movie ordered successfully!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            Toast.makeText(SearchMovie.this, "You have not ordered " + selectedMovie.getTitle(), Toast.LENGTH_SHORT).show();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void onReturnButtonClick(View view) {
        finish();
    }
}
