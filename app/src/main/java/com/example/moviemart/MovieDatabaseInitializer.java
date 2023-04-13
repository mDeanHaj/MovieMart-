package com.example.moviemart;

import android.os.AsyncTask;

import com.example.moviemart.db.MovieDao;
import com.example.moviemart.db.MovieDatabase;

public class MovieDatabaseInitializer {

    public static void populateAsync(final MovieDatabase movieDatabase) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                populateSync(movieDatabase);
                return null;
            }
        }.execute();
    }

    public static void populateSync(MovieDatabase movieDatabase) {
        MovieDao dao = movieDatabase.movieDao();

        insertIfNotExists(dao, new Movie("The Shawshank Redemption", 1994, "Drama", 142));
        insertIfNotExists(dao, new Movie("The Godfather", 1972, "Crime, Drama", 175));
        insertIfNotExists(dao, new Movie("The Dark Knight", 2008, "Action, Crime, Drama", 152));
        insertIfNotExists(dao, new Movie("Pulp Fiction", 1994, "Crime, Drama", 154));
        insertIfNotExists(dao, new Movie("Fight Club", 1999, "Drama", 139));
    }

    private static void insertIfNotExists(MovieDao dao, Movie movie) {
        int count = dao.countMovieWithTitleYearGenre(movie.getTitle(), movie.getYear(), movie.getGenre());
        if (count == 0) {
            dao.insert(movie);
        }
    }
}
