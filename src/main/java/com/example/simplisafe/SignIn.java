package com.example.simplisafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SignIn extends AppCompatActivity {

    Button signIn;
    EditText username, password;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.username_field_signIn);
        password = findViewById(R.id.password_field_signIn);
        signIn = findViewById(R.id.btn_signIn_signIn);
        DB = new DBHelper(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(SignIn.this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    if(DB.checkUsernamePassword(user, pass)){
                        Toast.makeText(SignIn.this, "Signed in successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        intent.putExtra("username", user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignIn.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}