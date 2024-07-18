package com.example.notes_app.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.notes_app.Database.MainDOA;
import com.example.notes_app.Database.notesDatabase;
import com.example.notes_app.Models.Notes;
import com.example.notes_app.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreatingNoteActivity extends AppCompatActivity {

    ImageView imageBack;
    ImageView save;
    TextView date;
    EditText inNoteTitle, inNoteSubtitle, inNote;
    private LinearLayout noteContainer;




    String selectNoteColor;
     View viewSubtitleIndicator;

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
        viewSubtitleIndicator = findViewById(R.id.viewSubtitleIndicator);

        // Initialize the database
        notesDatabase notesDB = Room.databaseBuilder(getApplicationContext(),
                notesDatabase.class, "NotesApp").build();
        MainDOA mainDAO = notesDB.getDao();

        imageBack.setOnClickListener(view -> onBackPressed());

        // Set the current date and time
        @SuppressLint("SimpleDateFormat") String currentDateTime = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a")
                .format(new Date());
        date.setText(currentDateTime);

        selectNoteColor = "#333333";


        initMiscellaneous();
        setSubtitleIndicatorColor();

        save.setOnClickListener(view -> {
            String title = inNoteTitle.getText().toString();
            String subtitle = inNoteSubtitle.getText().toString();
            String noteText = inNote.getText().toString();
            String color;

            // Get current date and time
            @SuppressLint("SimpleDateFormat") String currentDateTime1 = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a")
                    .format(new Date());

            // Create a new note
            Notes newNote = new Notes(title, subtitle, noteText, currentDateTime1, selectNoteColor);

            // Use a background thread or coroutine to insert the note into the database
            new Thread(() -> {
                long id = mainDAO.insert(newNote);
                newNote.setId((int) id);


                // Create an intent to send the new note data back to the main activity
                Intent intent = new Intent();
                intent.putExtra("newNote", newNote);
                intent.putExtra("noteColor", selectNoteColor);

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

    private void initMiscellaneous(){
        final LinearLayout layoutMiscellaneous = findViewById(R.id.layoutMiscellaneous);
        final BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(layoutMiscellaneous);
        layoutMiscellaneous.findViewById(R.id.textMiscellaneous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        final ImageView imageColor1 = layoutMiscellaneous.findViewById(R.id.imageColor1);
        final ImageView imageColor2 = layoutMiscellaneous.findViewById(R.id.imageColor2);
        final ImageView imageColor3 = layoutMiscellaneous.findViewById(R.id.imageColor3);
        final ImageView imageColor4 = layoutMiscellaneous.findViewById(R.id.imageColor4);
        final ImageView imageColor5 = layoutMiscellaneous.findViewById(R.id.imageColor5);

        layoutMiscellaneous.findViewById(R.id.viewcolor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectNoteColor ="#333333";
                imageColor1.setImageResource(R.drawable.ic_done);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                setSubtitleIndicatorColor();
            }
        });

        layoutMiscellaneous.findViewById(R.id.viewcolor2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectNoteColor ="#FDBE3B";
                imageColor2.setImageResource(R.drawable.ic_done);
                imageColor1.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                setSubtitleIndicatorColor();
            }
        });

        layoutMiscellaneous.findViewById(R.id.viewcolor3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectNoteColor ="#FF4842";
                imageColor3.setImageResource(R.drawable.ic_done);
                imageColor2.setImageResource(0);
                imageColor1.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                setSubtitleIndicatorColor();
            }
        });

        layoutMiscellaneous.findViewById(R.id.viewcolor4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectNoteColor ="#2196F3";
                imageColor4.setImageResource(R.drawable.ic_done);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor1.setImageResource(0);
                imageColor5.setImageResource(0);
                setSubtitleIndicatorColor();
            }
        });

        layoutMiscellaneous.findViewById(R.id.viewcolor5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectNoteColor ="#ffffff";
                imageColor5.setImageResource(R.drawable.ic_done);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor1.setImageResource(0);
                setSubtitleIndicatorColor();
            }
        });


    }

    private void setSubtitleIndicatorColor(){
        GradientDrawable gradientDrawable =(GradientDrawable) viewSubtitleIndicator.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectNoteColor));
    }
}
