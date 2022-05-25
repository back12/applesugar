package com.example.applesugar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.applesugar.db.entity.OnScreenMovie;
import com.example.applesugar.repositories.MovieRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    public LiveData<List<OnScreenMovie>>  getOnScreenMovieList(){
        return movieRepository.getOnScreenMovieList();
    }
}
