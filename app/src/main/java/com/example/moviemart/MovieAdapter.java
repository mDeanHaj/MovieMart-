package com.example.moviemart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies;

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.titleTextView.setText(movie.getTitle());
        holder.yearTextView.setText(String.valueOf(movie.getYear()));
        holder.genreTextView.setText(movie.getGenre());
        holder.lengthTextView.setText(String.valueOf(movie.getLength()));
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView yearTextView;
        TextView genreTextView;
        TextView lengthTextView;

        public MovieViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            genreTextView = itemView.findViewById(R.id.genreTextView);
            lengthTextView = itemView.findViewById(R.id.lengthTextView);
        }
    }
}