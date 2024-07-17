package com.example.notes_app.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "notes")
public class Notes  implements Serializable{
    @PrimaryKey(autoGenerate = true)
    int id =0;

    @ColumnInfo(name = "title")
    String title ="";

    @ColumnInfo(name = "subtitle")
    String subtitle ="";

    @ColumnInfo(name = "note")
    String note ="";

    @ColumnInfo(name = "date")
    String date ="";

    @ColumnInfo(name = "color")
    String color = "";



    public Notes(String title, String subtitle, String note, String date, String color) {
        this.title = title;
        this.subtitle = subtitle;
        this.note = note;
        this.date = date;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getColor() {
        return color; // Add this method
    }

    public void setColor(String color) {
        this.color = color; // Add this method
    }
}
