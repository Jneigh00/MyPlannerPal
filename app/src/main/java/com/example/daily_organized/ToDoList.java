package com.example.daily_organized;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.daily_organized.Database.Event;
import com.example.daily_organized.Database.EventDatabase;

import java.util.List;

public class ToDoList extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    Button settings;
    Button addActivity;
    Button checkDoneBtn;
    RecyclerView recyclerView;
    AdapterToDo adapterToDo;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_todo);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tododone, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        recyclerView = findViewById(R.id.recyclerview_for_todos);
        done = findViewById(R.id.todo_or_done);

        settings = (Button)findViewById(R.id.settings_button);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToSettings();
            }
        });

        addActivity = (Button) findViewById(R.id.add_todo_button);
        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToAddActivity();
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ToDoList.this, DoneList.class);
                startActivity(intent);
            }
        });


        initRecyclerView();
        loadEventList();
    }


    public void navigateToSettings(){
        Intent intent = new Intent(ToDoList.this,SettingsPage.class);
        intent.putExtra("ToDoList","Todo");
        startActivity(intent);
    }

    public void navigateToAddActivity(){
        Intent intent = new Intent(ToDoList.this, AddActivity.class);
        intent.putExtra("ToDoList","Todo");
        startActivityForResult(intent, 100);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview_for_todos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapterToDo = new AdapterToDo(this);
        recyclerView.setAdapter(adapterToDo);
    }

    private void loadEventList() {
        EventDatabase db = EventDatabase.getDatabase(this.getApplicationContext());
        List<Event> eventList = db.eventDAO().getAllToDoEvents(false);
        adapterToDo.setEventList(eventList);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100) {
            loadEventList();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        EventDatabase db = EventDatabase.getDatabase(this.getApplicationContext());

        if(item == "To Do"){
            List<Event> userList = db.eventDAO().getAllToDoEvents(false);
            adapterToDo.setEventList(userList);
        } else {
            List<Event> userList = db.eventDAO().getAllDoneEvents(true);
            adapterToDo.setEventList(userList);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}