package com.example.applesugar.db.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "marked_movie"
        , foreignKeys = @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "uid", onDelete = CASCADE),
        indices = {@Index(value = "id", unique = true), @Index(value = "uid")}
)
public class MarkedMovie {
    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.TEXT)
    private String id;
    @ColumnInfo(name = "url", typeAffinity = ColumnInfo.TEXT)
    private String url = null;
    @ColumnInfo(name = "type", typeAffinity = ColumnInfo.TEXT)
    private String type = null;
    @ColumnInfo(name = "uid", typeAffinity = ColumnInfo.INTEGER)
    private int uid;

    public MarkedMovie(String id, String url, String type, int uid) {
        this.id = id;
        this.url = url;
        this.type = type;
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
