package com.example.simplisafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Pay extends AppCompatActivity {

    String username;
    TextView sum, balance, mustPay, yourBalance, youPaid, t1, t2;
    String sum2, balance2;
    EditText enteredSum;
    Button btnPay;
    DBHelper DB;
    View mustPayBg, balanceBg;
    ImageView youPaidImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Intent i = getIntent();
        username = i.getStringExtra("username");
        mustPay = findViewById(R.id.mustPay);
        yourBalance = findViewById(R.id.yourBalance);
        youPaid = findViewById(R.id.youPaid);
        youPaidImg = findViewById(R.id.youPaidImg);
        sum = findViewById(R.id.sum);
        balance = findViewById(R.id.balance);
        enteredSum = findViewById(R.id.enteredSum);
        btnPay = findViewById(R.id.btnPay);
        DB = new DBHelper(this);
        sum2 = i.getStringExtra("sum");
        balance2 = i.getStringExtra("balance");
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        mustPayBg = findViewById(R.id.mustPayBg);
        balanceBg = findViewById(R.id.balanceBg);

        if(sum2 != null && balance2 != null) {
            sum.setText(sum2);
            balance.setText(balance2);
            if (Integer.parseInt(sum.getText().toString()) == 0) {
                mustPay.setVisibility(View.GONE);
                yourBalance.setVisibility(View.GONE);
                sum.setVisibility(View.GONE);
                balance.setVisibility(View.GONE);
                btnPay.setVisibility(View.GONE);
                enteredSum.setVisibility(View.GONE);
                t1.setVisibility(View.GONE);
                t2.setVisibility(View.GONE);
                mustPayBg.setVisibility(View.GONE);
                balanceBg.setVisibility(View.GONE);
                youPaid.setVisibility(View.VISIBLE);
                youPaidImg.setVisibility(View.VISIBLE);
            }
        }


        btnPay.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                int sm = Integer.parseInt(sum.getText().toString());
                int bl = Integer.parseInt(balance.getText().toString());
                String esm = enteredSum.getText().toString().trim();
                if(esm.isEmpty()){
                    Toast.makeText(Pay.this, "Enter sum!", Toast.LENGTH_SHORT).show();
                } else if(Integer.parseInt(esm) > sm){
                    Toast.makeText(Pay.this, "Entered sum is more than required!", Toast.LENGTH_SHORT).show();
                } else {
                    if (Integer.parseInt(esm) == sm) {
                        mustPay.setVisibility(View.GONE);
                        yourBalance.setVisibility(View.GONE);
                        sum.setVisibility(View.GONE);
                        balance.setVisibility(View.GONE);
                        btnPay.setVisibility(View.GONE);
                        enteredSum.setVisibility(View.GONE);
                        t1.setVisibility(View.GONE);
                        t2.setVisibility(View.GONE);
                        mustPayBg.setVisibility(View.GONE);
                        balanceBg.setVisibility(View.GONE);
                        youPaid.setVisibility(View.VISIBLE);
                        youPaidImg.setVisibility(View.VISIBLE);
                    }
                    sum.setText(Integer.toString(sm - Integer.parseInt(esm)));
                    balance.setText(Integer.toString(bl - Integer.parseInt(esm)));
                    enteredSum.getText().clear();
                }
            }
        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.pay);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.call:
                        Intent intent = new Intent(getApplicationContext(), Call.class);
                        intent.putExtra("username", username);
                        intent.putExtra("sum", sum.getText().toString());
                        intent.putExtra("balance", balance.getText().toString());
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.pay:
                        return true;
                    case R.id.profile:
                        intent = new Intent(getApplicationContext(), Profile.class);
                        intent.putExtra("username", username);
                        intent.putExtra("sum", sum.getText().toString());
                        intent.putExtra("balance", balance.getText().toString());
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        intent = new Intent(getApplicationContext(), Home.class);
                        intent.putExtra("username", username);
                        intent.putExtra("sum", sum.getText().toString());
                        intent.putExtra("balance", balance.getText().toString());
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}