package com.example.noteapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.noteapp.db.NoteDao;
import com.example.noteapp.db.NoteRoomDataBase;
import com.example.noteapp.model.Note;

import java.util.List;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    public NoteRepository(Application application) {
        NoteRoomDataBase db = NoteRoomDataBase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void insert(Note note) {
        new insertAsyncTask(mNoteDao).execute(note);
    }

    public void update(Note note)  {
        new updateNoteAsyncTask(mNoteDao).execute(note);
    }

    public void deleteAll()  {
        new deleteAllNotesAsyncTask(mNoteDao).execute();
    }

    // Must run off main thread
    public void deleteNote(Note note) {
        new deleteNoteAsyncTask(mNoteDao).execute(note);
    }

    // Static inner classes below here to run database interactions in the background.

    /**
     * Inserts a note into the database.
     */
    private static class insertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncTaskDao;

        insertAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * Deletes all notes from the database (does not delete the table).
     */
    private static class deleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao mAsyncTaskDao;

        deleteAllNotesAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     *  Deletes a single note from the database.
     */
    private static class deleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao mAsyncTaskDao;

        deleteNoteAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.deleteNote(params[0]);
            return null;
        }
    }

    /**
     *  Updates a note in the database.
     */
    private static class updateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao mAsyncTaskDao;

        updateNoteAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}
