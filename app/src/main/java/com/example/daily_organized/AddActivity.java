package com.example.daily_organized;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daily_organized.Database.Event;
import com.example.daily_organized.Database.EventDatabase;

public class AddActivity extends AppCompatActivity {

    EditText nameInput;
    EditText descInput;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        nameInput = findViewById(R.id.todo_name);
        descInput = findViewById(R.id.description);
        saveBtn = findViewById(R.id.save_todo);

        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveEvent(nameInput.getText().toString(), descInput.getText().toString());
                Toast toast = Toast.makeText(getApplicationContext(),"Event Added!",Toast.LENGTH_LONG);
                        toast.show();
            }
        });
    }

    private void saveEvent(String name, String desc){
        EventDatabase db = EventDatabase.getDatabase(this.getApplicationContext());
        Event event = new Event();
        event.name = name;
        event.desc = desc;
        event.done = false;
        db.eventDAO().addEvent(event);
        finish();
    }

}