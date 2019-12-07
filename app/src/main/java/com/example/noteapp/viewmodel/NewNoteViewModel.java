package com.example.noteapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.noteapp.NoteRepository;
import com.example.noteapp.model.Note;

import java.util.List;

public class NewNoteViewModel extends AndroidViewModel {

    private NoteRepository mRepository;

    private LiveData<List<Note>> mAllNotes;

    public NewNoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NoteRepository(application);
    }

    public LiveData<Note> getNoteById(int id) {
        return mRepository.getNoteById(id);
    }

    public void insert(Note note) {
        mRepository.insert(note);
    }

    public void update(Note note) {
        mRepository.update(note);
    }
}
