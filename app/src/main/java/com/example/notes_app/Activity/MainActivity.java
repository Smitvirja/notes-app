package com.example.notes_app.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.notes_app.Database.notesDatabase;
import com.example.notes_app.R;
import com.example.notes_app.adapter.recayclerviewadapter;

public class MainActivity extends AppCompatActivity {

    private recayclerviewadapter notesAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView addNoteButton = findViewById(R.id.buttonAddNote);

        //this is to go to another activity(creating note avtivity)
        addNoteButton.setOnClickListener(view -> {
            // Start CreatingNoteActivity for adding a new note
            Intent intent = new Intent(MainActivity.this, CreatingNoteActivity.class);
            startActivity(intent);
        });

        RecyclerView noteRecyclerView = findViewById(R.id.noteRecyclerView);
        noteRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

        notesAdapter = new recayclerviewadapter();
        noteRecyclerView.setAdapter(notesAdapter);

        // Initialize the database
        notesDatabase database = notesDatabase.getInstance(this);

        // Observe changes in the database
        database.getDao().getallnotes().observe(this, notesList -> {
            // Update the adapter's data when the database changes
            notesAdapter.setNotes(notesList);
            notesAdapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Handle the result from CreatingNoteActivity here if needed
        // You can access the new note using data.getSerializableExtra("newNote")
    }
}
