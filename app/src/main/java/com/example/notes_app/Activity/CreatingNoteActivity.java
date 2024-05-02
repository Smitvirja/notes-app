package com.example.notes_app.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.notes_app.Database.MainDOA;
import com.example.notes_app.Database.notesDatabase;
import com.example.notes_app.Models.Notes;
import com.example.notes_app.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreatingNoteActivity extends AppCompatActivity {

    ImageView imageBack;
    ImageView save;
    TextView date;
    EditText inNoteTitle, inNoteSubtitle, inNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_note);

        // Initialize views
        imageBack = findViewById(R.id.imageBack);
        save = findViewById(R.id.save);
        date = findViewById(R.id.textDateTime);
        inNoteTitle = findViewById(R.id.inputNoteTitle);
        inNoteSubtitle = findViewById(R.id.inputNoteSubtitle);
        inNote = findViewById(R.id.inputNote);

        // Initialize the database
        notesDatabase notesDB = Room.databaseBuilder(getApplicationContext(),
                notesDatabase.class, "NotesApp").build();
        MainDOA mainDAO = notesDB.getDao();

        imageBack.setOnClickListener(view -> onBackPressed());

        // Set the current date and time
        @SuppressLint("SimpleDateFormat") String currentDateTime = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a")
                .format(new Date());
        date.setText(currentDateTime);

        save.setOnClickListener(view -> {
            String title = inNoteTitle.getText().toString();
            String subtitle = inNoteSubtitle.getText().toString();
            String noteText = inNote.getText().toString();

            // Get current date and time
            @SuppressLint("SimpleDateFormat") String currentDateTime1 = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a")
                    .format(new Date());

            // Create a new note
            Notes newNote = new Notes(title, subtitle, noteText, currentDateTime1);

            // Use a background thread or coroutine to insert the note into the database
            new Thread(() -> {
                long id = mainDAO.insert(newNote);
                newNote.setId((int) id);


                // Create an intent to send the new note data back to the main activity
                Intent intent = new Intent();
                intent.putExtra("newNote", newNote);

                // Set the result as OK and include the intent
                setResult(RESULT_OK, intent);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();

                    // Create an intent to restart the main activity
                    Intent restartIntent = new Intent(this, MainActivity.class);
                    restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(restartIntent);

                    // Finish the current activity
                    finish();
                });
            }).start();
        });
    }
}
