package com.example.applesugar.db.entity;

import static androidx.room.ColumnInfo.INTEGER;
import static androidx.room.ColumnInfo.TEXT;
import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "comment"
        , foreignKeys = @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "uid", onDelete = CASCADE),
        indices = {@Index(value = "pid", unique = true), @Index(value = "uid")}
)
public class Comment {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pid", typeAffinity = INTEGER)
    private int pid;
    @ColumnInfo(name = "username", typeAffinity = TEXT)
    private String username;
    @ColumnInfo(name = "content", typeAffinity = TEXT)
    private String content;
    @ColumnInfo(name = "rating", typeAffinity = INTEGER)
    private int rating;
    @ColumnInfo(name = "uid", typeAffinity = INTEGER)
    private int uid;

    public Comment(String username, String content, int rating, int uid) {
        this.username = username;
        this.content = content;
        this.rating = rating;
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
