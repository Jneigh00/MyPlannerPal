package com.example.daily_organized.Database;
import java.math.BigInteger;
import java.util.*;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Events")
public class Event {
    private int  eventId;
    private String eventDescription;
    private int month;
    private int day;
    private int year;
    private String timeOfEvent;
    private boolean important;
    private boolean showAlert;

    public Event(int id, String desc, int month, int day, int year, String time, boolean important, boolean showAlert){
        this.eventId = id;
        this.eventDescription = desc;
        this.month = month;
        this.day = day;
        this.year = year;
        this.timeOfEvent = time;
        this.important = important;
        this.showAlert = showAlert;
    }


}
