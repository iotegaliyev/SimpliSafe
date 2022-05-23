package com.example.simplisafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    String username, sum, balance;
    DBHelper DB;
    TextView address_hm;
    VideoView videoView;
    String videoPath;
    Uri uri;
    MediaController mediaController;
    Button btnRcvCall, btnOpen, btnBlock;
    Dialog dialog;
    Dialog2 dialog2;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch drc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent i = getIntent();
        username = i.getStringExtra("username");
        sum = i.getStringExtra("sum");
        balance = i.getStringExtra("balance");
        DB = new DBHelper(this);
        address_hm = findViewById(R.id.address_home);
        videoView = findViewById(R.id.videoView);
        videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        mediaController = new MediaController(this);
        btnRcvCall = findViewById(R.id.btnRcvCall);
        btnOpen = findViewById(R.id.btnOpen);
        btnBlock = findViewById(R.id.btnBlock);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        dialog = new Dialog();
        dialog2 = new Dialog2();
        drc = findViewById(R.id.drc);

        btnRcvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drc.isChecked()){
                    Toast.makeText(Home.this, "Calling system is blocked!", Toast.LENGTH_SHORT).show();
                } else {
                    btnOpen.setVisibility(View.VISIBLE);
                    btnBlock.setVisibility(View.VISIBLE);
                    btnRcvCall.setVisibility(View.GONE);
                    drc.setVisibility(View.GONE);
                }
            }
        });

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show(getSupportFragmentManager(), "Dialog");
                btnOpen.setVisibility(View.GONE);
                btnBlock.setVisibility(View.GONE);
                btnRcvCall.setVisibility(View.VISIBLE);
                drc.setVisibility(View.VISIBLE);
            }
        });

        btnBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show(getSupportFragmentManager(), "Dialog");
                btnOpen.setVisibility(View.GONE);
                btnBlock.setVisibility(View.GONE);
                btnRcvCall.setVisibility(View.VISIBLE);
                drc.setVisibility(View.VISIBLE);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

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
                        intent = new Intent(getApplicationContext(), Profile.class);
                        intent.putExtra("username", username);
                        intent.putExtra("sum", sum);
                        intent.putExtra("balance", balance);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                }
                return false;
            }
        });
        Cursor res = DB.getData(username);
        while (res.moveToNext()){
            address_hm.setText(res.getString(3));
        }
    }
}