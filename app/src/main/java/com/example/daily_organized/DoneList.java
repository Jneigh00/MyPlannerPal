package com.example.daily_organized;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class DoneList extends AppCompatActivity {

    Button moveToDoList;
    Button remove;
    Button settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_done);

        moveToDoList = findViewById(R.id.todo_or_done);

    }



}