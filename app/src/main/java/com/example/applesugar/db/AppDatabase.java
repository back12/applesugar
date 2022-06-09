package com.example.applesugar.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.applesugar.db.dao.CommentDao;
import com.example.applesugar.db.dao.MarkedMovieDao;
import com.example.applesugar.db.dao.OnScreenMovieDao;
import com.example.applesugar.db.dao.PostDao;
import com.example.applesugar.db.dao.TopMovieDao;
import com.example.applesugar.db.dao.UserDao;
import com.example.applesugar.db.entity.Comment;
import com.example.applesugar.db.entity.MarkedMovie;
import com.example.applesugar.db.entity.OnScreenMovie;
import com.example.applesugar.db.entity.Post;
import com.example.applesugar.db.entity.TopMovie;
import com.example.applesugar.db.entity.User;

@Database(entities = {User.class, OnScreenMovie.class, TopMovie.class, MarkedMovie.class, Post.class, Comment.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "AppleSugar.db";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context
                    , AppDatabase.class, DATABASE_NAME)
                    .createFromAsset("applesugar.db")
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
    public abstract OnScreenMovieDao onScreenMovieDao();
    public abstract TopMovieDao topMovieDao();
    public abstract MarkedMovieDao markedMovieDao();
    public abstract PostDao postDao();
    public abstract CommentDao commentDao();

}

