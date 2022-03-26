package com.example.daily_organized.Database;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface EventDAO {
    @Insert
    void addEvent(Event... event);

    @Delete
    void removeEvent(Event... event);



}
