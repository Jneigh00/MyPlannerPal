package com.example.daily_organized.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Events")
public class Event {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "eventId")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "eventDescription")
    public String desc;

    @ColumnInfo(name = "done")
    public boolean done;

}
