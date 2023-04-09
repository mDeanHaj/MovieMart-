package com.example.moviemart;

public class MovieLog {

    private String mMovie;
    private int mStock;

    public MovieLog(String movie, int stock) {
        mMovie = movie;
        mStock = stock;
    }

    public String getMovie() {
        return mMovie;
    }

    public void setMovie(String movie) {
        mMovie = movie;
    }

    public int getStock() {
        return mStock;
    }

    public void setStock(int stock) {
        mStock = stock;
    }

    @Override
    public String toString() {
        return "MovieLog{" +
                "mMovie='" + mMovie + '\'' +
                ", mStock=" + mStock +
                '}';
    }
}
