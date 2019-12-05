package com.example.noteapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.noteapp.model.Note;

@Database(entities = {Note.class}, version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class NoteRoomDataBase extends RoomDatabase {

    public abstract NoteDao noteDao();

    private static NoteRoomDataBase INSTANCE;

    public static NoteRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteRoomDataBase.class) {
                if (INSTANCE == null) {
                    // Create database here.
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteRoomDataBase.class, "note_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
