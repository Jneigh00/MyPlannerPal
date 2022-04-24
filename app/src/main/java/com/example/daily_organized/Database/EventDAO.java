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

    @Query("SELECT * FROM Events where done = :bool")
    List<Event> getAllToDoEvents(boolean bool);

    @Query("SELECT * FROM Events where done = :bool")
    List<Event> getAllDoneEvents(boolean bool);

    @Query("Select * From Events where eventId = :id")
    Event getById(int id);

    @Insert
    void addEvent(Event... events);

    @Update
    void updateEvent(Event...events);

    @Delete
    void removeEvent(Event... event);

    @Query("Delete From Events where eventId = :e_id")
    void delete(int e_id);

}
