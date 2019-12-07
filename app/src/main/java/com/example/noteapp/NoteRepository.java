package com.example.noteapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.noteapp.db.NoteDao;
import com.example.noteapp.db.NoteRoomDataBase;
import com.example.noteapp.model.Note;

import java.util.List;

public class NoteRepository {

    private static NoteRepository sInstance;
    private final AppExecutors mExecutors;

    private final NoteRoomDataBase db;
    private LiveData<List<Note>> mAllNotes;

    public NoteRepository(NoteRoomDataBase database, AppExecutors executors) {
        db = database;
        mExecutors = executors;
        mAllNotes = db.noteDao().getAllNotes();
    }

    public static NoteRepository getInstance(final NoteRoomDataBase database,
                                             final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (NoteRepository.class) {
                if (sInstance == null) {
                    sInstance = new NoteRepository(database, executors);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void insert(final Note note) {
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                db.noteDao().insert(note);
            }
        });
    }

    public void update(final Note note) {
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                db.noteDao().update(note);
            }
        });
    }

    public void deleteAll() {
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                db.noteDao().deleteAll();
            }
        });
    }

    // Must run off main thread
    public void deleteNote(final Note note) {
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                db.noteDao().deleteNote(note);
            }
        });
    }

    public LiveData<Note> getNoteById(int id) {
        return db.noteDao().getNote(id);
    }

}