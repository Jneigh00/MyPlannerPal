package com.example.daily_organized;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText usernameInput;
    TextInputEditText passwordInput;
    Button login;
    int counter = 5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usernameInput = (TextInputEditText) findViewById((R.id.usernameInput));
        passwordInput = (TextInputEditText) findViewById((R.id.passwordInput));
        login = (Button) findViewById(R.id.button_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin(view);
            }
        });
    }

    public void userLogin(View v){
        String enteredUSer = usernameInput.getText().toString();
        String enterredPassword = passwordInput.getText().toString();

        if(enteredUSer.equals("Admin") && enterredPassword.equals("1234")){
            Intent intent = new Intent(MainActivity.this, WeekDisplayActivity.class);
            startActivity(intent);
        }
        else{
            counter--;
            if(counter == 0){
                login.setEnabled(false);
            }
        }


    }

}