package com.example.simplisafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {

    DBHelper DB;
    String username, sum, balance;
    TextView username_pr, fullName_pr, balance_pr, age_pr, address_pr, cardNumber_pr;
    Button btnLogOut;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent i = getIntent();
        username = i.getStringExtra("username");
        sum = i.getStringExtra("sum");
        balance = i.getStringExtra("balance");
        username_pr = findViewById(R.id.username_profile);
        fullName_pr = findViewById(R.id.fullName_profile);
        balance_pr = findViewById(R.id.balance_profile);
        age_pr = findViewById(R.id.age_profile);
        address_pr = findViewById(R.id.address_profile);
        cardNumber_pr = findViewById(R.id.cardNumber_profile);
        DB = new DBHelper(this);
        btnLogOut = findViewById(R.id.btnLogOut);

        if(balance == null){
            balance = "100000";
        }
        balance_pr.setText(balance + "₸");

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logOut = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(logOut);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.call:
                        Intent intent = new Intent(getApplicationContext(), Call.class);
                        intent.putExtra("username", username);
                        intent.putExtra("sum", sum);
                        intent.putExtra("balance", balance);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.pay:
                        intent = new Intent(getApplicationContext(), Pay.class);
                        intent.putExtra("username", username);
                        intent.putExtra("sum", sum);
                        intent.putExtra("balance", balance);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
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

        Cursor res = DB.getData(username);
        while (res.moveToNext()){
            username_pr.setText("@" + res.getString(0));
            fullName_pr.setText(res.getString(1));
            age_pr.setText(res.getString(2));
            address_pr.setText(res.getString(3));
            cardNumber_pr.setText(res.getString(4));
        }
    }
}