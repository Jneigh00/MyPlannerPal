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

import java.util.EventListener;

@Database(entities = {Event.class}, version = 1, exportSchema = false)
public abstract class EventDatabase extends RoomDatabase {


    private static EventDatabase INSTANCE;

    /*
    public static EventDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EventDatabase.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EventDatabase.class, "joke_database")

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
                    for (int i = 0; i < DefaultContent.TITLE.length; i++) {
                        insert(new Joke(0, DefaultContent.TITLE[i], DefaultContent.SETUP[i],
                                DefaultContent.PUNCHLINE[i], false));
                    }
                }
            };

    public static void getEvent(int id, EventListener listener){
        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message m){
                super.handleMessage(m);
                listener.onEventReturn((Event) m.obj);
            }
        };
        (new Thread(() -> {
            Message m = handler.obtainMessage();
            m.obj = INSTANCE.EventDAO().getById(id);
            handler.sendMessage(m);
        })).start();
    } */


}
