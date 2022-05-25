package com.example.applesugar.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.applesugar.db.AppDatabase;
import com.example.applesugar.db.dao.OnScreenMovieDao;
import com.example.applesugar.db.dao.TopMovieDao;
import com.example.applesugar.db.entity.OnScreenMovie;
import com.example.applesugar.db.entity.TopMovie;

import java.util.List;

public class MovieRepository {
    private OnScreenMovieDao onScreenMovieDao;
    private TopMovieDao topMovieDao;

    public MovieRepository(Context context) {
        onScreenMovieDao = AppDatabase.getInstance(context).onScreenMovieDao();
        topMovieDao = AppDatabase.getInstance(context).topMovieDao();
    }

    public LiveData<List<OnScreenMovie>> getOnScreenMovieList(){
        return onScreenMovieDao.getOnScreenMovieList();
    }

    public LiveData<List<TopMovie>> getTopMovieList(){
        return topMovieDao.getTopMovieList();
    }

}
