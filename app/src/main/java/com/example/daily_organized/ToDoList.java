package com.example.daily_organized;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ToDoList extends AppCompatActivity {

    Button settings;
    Button addActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_todo);

        Spinner spinner = findViewById(R.id.todo_or_done);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tododone, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


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
    }

    public void navigateToSettings(){
        Intent intent = new Intent(ToDoList.this,SettingsPage.class);
        startActivity(intent);
    }

    public void navigateToAddActivity(){
        Intent intent = new Intent(ToDoList.this, AddActivity.class);
        startActivity(intent);
    }
}