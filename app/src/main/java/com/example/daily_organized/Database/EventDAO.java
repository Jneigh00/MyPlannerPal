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

    @Query("SELECT * FROM Events where done = 0")
    List<Event> getAllToDoEvents();

    @Query("SELECT * FROM Events where done = 1")
    List<Event> getAllDoneEvents();

    @Query("Select * From Events where eventId = :id")
    Event getById(int id);

    @Insert
    void addEvent(Event... events);

    @Query("Update Events set done = 1 where eventId = :rowid")
    void updateDone(int rowid);

    @Delete
    void removeEvent(Event... event);

    @Query("Delete From Events where eventId = :rowid")
    void delete(int rowid);

}
