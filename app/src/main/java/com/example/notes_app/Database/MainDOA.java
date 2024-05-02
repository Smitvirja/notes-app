package com.example.notes_app.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notes_app.Models.Notes;

import java.util.List;

@Dao
public interface MainDOA {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Notes notes);

    @Query("SELECT * FROM notes ORDER BY id DESC")
    LiveData<List<Notes>> getallnotes();

    @Update
    void update(Notes notes);

    @Query("delete from notes where id=:id")
    void delete(int id);
}
