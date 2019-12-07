package com.example.noteapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "desc")
    private String mContent;

    @NonNull
    @ColumnInfo(name = "published_on")
    private Date mNoteDate;

    public Note( String title, String content, @NonNull Date noteDate){
        this.mTitle = title;
        this.mContent = content;
        this.mNoteDate = noteDate;
    }

    @Ignore
    public Note(int id, String title, String content, @NonNull Date noteDate){
        this.id = id;
        this.mTitle = title;
        this.mContent = content;
        this.mNoteDate = noteDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public Date getNoteDate() {
        return mNoteDate;
    }

    public void setNoteDate(Date mNoteDate) {
        this.mNoteDate = mNoteDate;
    }

    public void setId(int id) {
        this.id = id;
    }
}
