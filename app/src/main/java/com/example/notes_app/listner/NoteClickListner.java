package com.example.notes_app.listner;

import com.example.notes_app.Models.Notes;

public interface NoteClickListner {
    void onNoteClicker(Notes note, int position);
}
