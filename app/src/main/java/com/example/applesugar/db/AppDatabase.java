package com.example.applesugar.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.applesugar.db.dao.UserDao;
import com.example.applesugar.db.entity.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "my_db";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context
                    , AppDatabase.class, DATABASE_NAME).build();
        }
        return instance;
    }

    public abstract UserDao userDao();


}

