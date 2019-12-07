package com.example.noteapp;

import android.app.Application;

import com.example.noteapp.db.NoteRoomDataBase;

public class BasicApp extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

    public NoteRoomDataBase getDatabase() {
        return NoteRoomDataBase.getInstance(this);
    }

    public NoteRepository getRepository() {
        return NoteRepository.getInstance(getDatabase(), mAppExecutors);
    }
}
