package com.example.daily_organized;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

public class SettingsPage extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button signOut;
    CheckBox darkMode;
    Button back;

    TextView usernameDisplay;
    TextView userInfoDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);


        usernameDisplay = findViewById(R.id.username);


        back = findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getActivity = getIntent().getStringExtra("ToDoList");
                if(getActivity.equals("Todo")){
                    Intent backIntent = new Intent(SettingsPage.this, ToDoList.class);
                    startActivity(backIntent);
                }
                else if(getActivity.equals("done")){
                    Intent backIntent = new Intent(SettingsPage.this, DoneList.class);
                    startActivity(backIntent);
                }
            }
        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        signOut = (Button)findViewById(R.id.logout_button);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOutOfApp();
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct == null){
            usernameDisplay.setText("Admin");
        }
        else{
            usernameDisplay.setText((acct.getEmail()));
        }

    }

    public void signOutOfApp(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(SettingsPage.this, LoginPage.class));
            }
        });
    }
}