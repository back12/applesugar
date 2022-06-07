package com.example.applesugar.db.entity;

import static androidx.room.ColumnInfo.INTEGER;
import static androidx.room.ColumnInfo.TEXT;
import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "post"
        , foreignKeys = @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "uid", onDelete = CASCADE),
        indices = {@Index(value = "pid", unique = true), @Index(value = "uid")}
)
public class Post {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pid", typeAffinity = INTEGER)
    private int pid;
    @ColumnInfo(name = "username", typeAffinity = TEXT)
    private String username;
    @ColumnInfo(name = "content", typeAffinity = TEXT)
    private String content;
    @ColumnInfo(name = "liked", typeAffinity = INTEGER)
    private int liked;
    @ColumnInfo(name = "uid", typeAffinity = INTEGER)
    private int uid;

    public Post(String username, String content, int uid) {
        this.username = username;
        this.content = content;
        liked = 0;
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

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
