package com.example.notes_app.Database;


import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notes_app.Models.Notes;

@Database(entities = {Notes.class},version =20,exportSchema = false)
public abstract class notesDatabase extends RoomDatabase{

    public static notesDatabase INSTANCE;
    private static final String DATABASE_NAME = "NotesApp";
    public static notesDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE=Room.databaseBuilder(context.getApplicationContext(),notesDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
    public abstract MainDOA getDao();
}


