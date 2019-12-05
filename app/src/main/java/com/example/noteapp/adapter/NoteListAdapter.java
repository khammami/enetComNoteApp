package com.example.noteapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.model.Note;
import com.example.noteapp.R;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private final LayoutInflater mInflater;
    private List<Note> mNotes; // Cached copy of words
    private static ClickListener clickListener;

    NoteListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if (mNotes != null) {
            Note current = mNotes.get(position);
            holder.titleItemView.setText(current.getTitle());
            holder.contentItemView.setText(current.getDescription());
            holder.dateItemView.setText(current.getNoteDate().toString());
        }
    }

    void setNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }

    public Note getNoteAtPosition(int position) {
        return mNotes.get(position);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleItemView;
        private final TextView contentItemView;
        private final TextView dateItemView;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            titleItemView = itemView.findViewById(R.id.itemTitleTetView);
            contentItemView = itemView.findViewById(R.id.itemContentTetView);
            dateItemView = itemView.findViewById(R.id.itemDateTetView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(view, getAdapterPosition());
                }
            });
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        NoteListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }
}
