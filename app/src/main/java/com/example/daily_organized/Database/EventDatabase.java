package com.example.daily_organized.Database;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Event.class}, version = 1, exportSchema = false)
public abstract class EventDatabase extends RoomDatabase{

    public interface eventListener{
        void onEventReturn(Event event);
    }

    private static EventDatabase INSTANCE;

    public abstract EventDAO eventDAO();

    public static EventDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EventDatabase.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EventDatabase.class, "joke_database")
                            .allowMainThreadQueries()
                            .addCallback(createEventDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback createEventDatabaseCallback =
            new RoomDatabase.Callback() {
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    /*
                    for (int i = 0; i < DefaultContent.ID.length; i++) {
                        INSTANCE.addEvent(new Event(DefaultContent.ID[i], DefaultContent.DESC[i], DefaultContent.MONTH[i],
                                                DefaultContent.DAY[i], DefaultContent.YEAR[i], DefaultContent.TIME[i],
                                                DefaultContent.IMPORTANT[i], DefaultContent.DONE[i]));
                    }
                     */
                }
            };

    private void addEvent(Event event) {
        new Thread(() -> INSTANCE.eventDAO().addEvent(event)).start();
    }

    private void removeEvent(Event event){
        new Thread(()-> INSTANCE.eventDAO().removeEvent(event)).start();
    }

    private void delete(int e_id){
        new Thread(() -> INSTANCE.eventDAO().delete(e_id)).start();
    }


    private void update(int rowid){
        new Thread(() -> INSTANCE.eventDAO().updateDone(rowid)).start();
    }
/*
    private static void getEvent(int id, eventListener listener){
        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message m){
                super.handleMessage(m);
                listener.onEventReturn((Event)m.obj );
            }
        };
        (new Thread(() -> {
            Message m = handler.obtainMessage();
            m.obj = INSTANCE.eventDAO().getById(id);
            handler.sendMessage(m);
        } )).start();

    }

 */
}
