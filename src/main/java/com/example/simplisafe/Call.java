package com.example.simplisafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Call extends AppCompatActivity {

    String username, sum, balance;
    ImageButton btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        Intent i = getIntent();
        username = i.getStringExtra("username");
        sum = i.getStringExtra("sum");
        balance = i.getStringExtra("balance");
        btnCall = findViewById(R.id.btnCall);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Intent.ACTION_CALL);
                it.setData(Uri.parse("tel:87713071744"));
                startActivity(it);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.call);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.call:
                        return true;
                    case R.id.pay:
                        Intent intent = new Intent(getApplicationContext(), Pay.class);
                        intent.putExtra("username", username);
                        intent.putExtra("sum", sum);
                        intent.putExtra("balance", balance);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        intent = new Intent(getApplicationContext(), Profile.class);
                        intent.putExtra("username", username);
                        intent.putExtra("sum", sum);
                        intent.putExtra("balance", balance);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        intent = new Intent(getApplicationContext(), Home.class);
                        intent.putExtra("username", username);
                        intent.putExtra("sum", sum);
                        intent.putExtra("balance", balance);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}