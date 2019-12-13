package com.example.noteapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.noteapp.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    @Query("DELETE FROM note_table")
    void deleteAll();

    @Delete
    void deleteNote(Note note);

    @Query("SELECT * from note_table ORDER BY published_on DESC")
    LiveData<List<Note>> getAllNotes();

    @Update
    void update(Note... note);
}
