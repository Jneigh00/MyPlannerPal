package com.example.daily_organized.Database;
import java.math.BigInteger;
import java.util.*;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Events")
public class Event {
    /*private int  eventId;
    private String eventDescription;
    private int month;
    private int day;
    private int year;
    private String timeOfEvent;
    private boolean important;
    private boolean showAlert;
*/
    public Event(int id, String desc, int month, int day, int year, String time, boolean important, boolean showAlert){
        this.eventId = id;
        this.eventDescription = desc;
        this.month = month;
        this.day = day;
        this.year = year;
        this.timeOfEvent = time;
        this.important = important;
        this.done = showAlert;
    }

    public Event(int id){
        this.eventId = id;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "eventId")
    public int eventId;

    @ColumnInfo(name = "eventDescription")
    public String eventDescription;

    @ColumnInfo(name = "month")
    public int month;

    @ColumnInfo(name = "day")
    public int day;

    @ColumnInfo(name = "year")
    public int year;

    @ColumnInfo(name = "timeOfEvent")
    public  String timeOfEvent;

    @ColumnInfo(name = "important")
    public boolean important;

    @ColumnInfo(name = "done")
    public boolean done;

}
