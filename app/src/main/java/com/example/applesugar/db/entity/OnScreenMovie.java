package com.example.applesugar.db.entity;

import static androidx.room.ColumnInfo.INTEGER;
import static androidx.room.ColumnInfo.TEXT;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_on_screen")
public class OnScreenMovie {
    @PrimaryKey
    @ColumnInfo(name = "id", typeAffinity = INTEGER)
    private int id;
    @ColumnInfo(name = "name", typeAffinity = TEXT)
    private String name;
    @ColumnInfo(name = "img", typeAffinity = TEXT)
    private String img;
    @ColumnInfo(name = "rating", typeAffinity = TEXT)
    private String rating;
    @ColumnInfo(name = "star", typeAffinity = TEXT)
    private String star;
    @ColumnInfo(name = "screen_time", typeAffinity = TEXT)
    private String screenTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getScreenTime() {
        return screenTime;
    }

    public void setScreenTime(String screenTime) {
        this.screenTime = screenTime;
    }
}
