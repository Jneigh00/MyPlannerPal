package com.example.daily_organized;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoneList extends AppCompatActivity {

    Button moveToDoList;
    Button settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_done);

        moveToDoList = findViewById(R.id.todo_or_done);
        settings = findViewById(R.id.settings_button);

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

    }



}