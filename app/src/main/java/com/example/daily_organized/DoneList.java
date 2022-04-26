package com.example.daily_organized;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.daily_organized.Database.Event;
import com.example.daily_organized.Database.EventDatabase;

import java.util.List;

public class DoneList extends AppCompatActivity {

    Button moveToDoList;
    Button settings;
    RecyclerView recyclerView;
    AdapterToDo adapterToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_done);

        moveToDoList = findViewById(R.id.todo_or_done);
        settings = findViewById(R.id.settings_button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tododone, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        recyclerView = findViewById(R.id.recyclerview_for_done);


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoneList.this, SettingsPage.class);
                intent.putExtra("ToDoList","done");
                startActivity(intent);
                finish();
            }
        });

        moveToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoneList.this, ToDoList.class);
                startActivity(intent);
                finish();
            }
        });

        initRecyclerView();
        loadEventList();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview_for_done);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapterToDo = new AdapterToDo(this);
        recyclerView.setAdapter(adapterToDo);
    }

    private void loadEventList() {
        EventDatabase db = EventDatabase.getDatabase(this.getApplicationContext());
        List<Event> eventList = db.eventDAO().getAllDoneEvents();
        adapterToDo.setEventList(eventList);
    }

}