package com.example.daily_organized;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;


public class LoginPage extends AppCompatActivity {

    TextInputEditText usernameInput;
    TextInputEditText passwordInput;
    Button login;
    int counter = 5;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button googleLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usernameInput = (TextInputEditText) findViewById((R.id.usernameInput));
        passwordInput = (TextInputEditText) findViewById((R.id.passwordInput));
        login = (Button) findViewById(R.id.button_login);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        googleLogin = (Button)findViewById(R.id.google_login);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct != null){
            startNewActivity();
        }


        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignIn();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin(view);
            }
        });
    }

    public  void googleSignIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                startNewActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void userLogin(View v){
        String enteredUSer = usernameInput.getText().toString();
        String enterredPassword = passwordInput.getText().toString();

        if(enteredUSer.equals("Admin") && enterredPassword.equals("1234")){
            Intent intent = new Intent(LoginPage.this, ToDoList.class);
            startActivity(intent);
        }
        else{
            counter--;
            if(counter == 0){
                login.setEnabled(false);
            }
        }

    }

    public void startNewActivity(){
        finish();
        Intent intent = new Intent(LoginPage.this, ToDoList.class);
        startActivity(intent);
    }



}