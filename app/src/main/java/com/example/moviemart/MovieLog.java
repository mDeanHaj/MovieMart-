package com.example.moviemart;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.moviemart.db.AppDataBase;

@Entity(tableName = AppDataBase.MOVIELOG_TABLE)
public class MovieLog {

    @PrimaryKey(autoGenerate = true)
    private int mLogId;

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
                "mLogId='" + mLogId + '\'' +
                "mMovie='" + mMovie + '\'' +
                ", mStock=" + mStock +
                '}';
    }

    public int getLogId() {
        return mLogId;
    }

    public void setLogId(int logId) {
        mLogId = logId;
    }
}
