package com.example.applesugar.db.entity;

import static androidx.room.ColumnInfo.TEXT;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "movie_top_250")
public class TopMovie implements Serializable {
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "mid", typeAffinity = TEXT)
    private String mid;
    @ColumnInfo(name = "name", typeAffinity = TEXT)
    private String name;
    @ColumnInfo(name = "img", typeAffinity = TEXT)
    private String img;
    @ColumnInfo(name = "genre", typeAffinity = TEXT)
    private String genre;
    @ColumnInfo(name = "description", typeAffinity = TEXT)
    private String description;
    @ColumnInfo(name = "language", typeAffinity = TEXT)
    private String language;
    @ColumnInfo(name = "country", typeAffinity = TEXT)
    private String country;
    @ColumnInfo(name = "rating", typeAffinity = TEXT)
    private String rating;
    @ColumnInfo(name = "release_date", typeAffinity = TEXT)
    private String releaseDate;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
