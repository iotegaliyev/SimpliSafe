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
import java.util.Locale;

public class SignUp extends AppCompatActivity {

    Button signUp;
    EditText username, fullName, age, address, card, password;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username_field_signUp);
        fullName = findViewById(R.id.fullName_field_signUp);
        age = findViewById(R.id.age_field_signUp);
        address = findViewById(R.id.address_field_signUp);
        card = findViewById(R.id.card_field_signUp);
        password = findViewById(R.id.password_field_signUp);
        signUp = findViewById(R.id.btn_signUp_signUp);
        DB = new DBHelper(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String us = username.getText().toString().trim();
                String fn = fullName.getText().toString().trim();
                String ag = age.getText().toString().trim();
                String ad = address.getText().toString().trim();
                String cn = card.getText().toString().trim();
                String ps = password.getText().toString().trim();

                if(us.isEmpty() || fn.isEmpty() || ag.isEmpty() || ad.isEmpty() || cn.isEmpty() || ps.isEmpty()){
                    Toast.makeText(SignUp.this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
                } else if(Integer.parseInt(age.getText().toString().trim()) < 18){
                    Toast.makeText(SignUp.this, "You are too young!", Toast.LENGTH_SHORT).show();
                } else if(cn.length() != 16){
                    Toast.makeText(SignUp.this, "Card number must contain 16 digits!", Toast.LENGTH_SHORT).show();
                } else if(ps.length() < 8){
                    Toast.makeText(SignUp.this, "Password must contain at least 8 characters!", Toast.LENGTH_SHORT).show();
                } else {
                    int agInt = Integer.parseInt(age.getText().toString().trim());
                    Boolean checkUser = DB.checkUsername(us);
                    if(!checkUser){
                        Boolean insert = DB.insertData(us, fn, agInt, ad, cn, ps);
                        if(insert){
                            Toast.makeText(SignUp.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUp.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUp.this, "User already exists!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}