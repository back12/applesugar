package com.example.applesugar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.applesugar.db.entity.OnScreenMovie;
import com.example.applesugar.db.entity.TopMovie;
import com.example.applesugar.repositories.MovieRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private MovieRepository repository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
    }

    public LiveData<List<OnScreenMovie>>  getOnScreenMovieList(){
        return repository.getOnScreenMovieList();
    }

    public LiveData<List<TopMovie>>  getTopMovieList(){
        return repository.getTopMovieList();
    }

}
