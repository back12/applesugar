package com.example.applesugar.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user", indices = {@Index(value = {"username"}, unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid", typeAffinity = ColumnInfo.INTEGER)
    private int uid;
    @ColumnInfo(name = "username", typeAffinity = ColumnInfo.TEXT)
    private String username;
    @ColumnInfo(name = "password", typeAffinity = ColumnInfo.TEXT)
    private String password;
    @ColumnInfo(name = "avatarUrl", typeAffinity = ColumnInfo.TEXT)
    private String avatarUrl = null;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
