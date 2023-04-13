package com.example.moviemart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.moviemart.db.MovieDao;
import com.example.moviemart.db.UserDatabase;

import java.util.List;

public class SearchMovie extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        loadMovies();
    }

    private void loadMovies() {
        new GetAllMoviesAsyncTask().execute();
    }

    private class GetAllMoviesAsyncTask extends AsyncTask<Void, Void, List<Movie>> {
        MovieDao movieDao;

        GetAllMoviesAsyncTask() {
            movieDao = UserDatabase.getUserDatabase(getApplicationContext()).mMovieDao();
        }

        @Override
        protected List<Movie> doInBackground(Void... voids) {
            return movieDao.getAllMovies();
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            movieAdapter = new MovieAdapter(movies);
            recyclerView.setAdapter(movieAdapter);
        }
    }
}
