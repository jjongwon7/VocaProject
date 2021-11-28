package com.example.vocaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button info_login = (Button) findViewById(R.id.info_login);
        info_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        Button info_calendar = (Button) findViewById(R.id.info_calendar);
        info_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this,Calender.class);
                startActivity(intent);
            }
        });

        Button info_bookmark = (Button) findViewById(R.id.info_bookmark);
        info_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, BookmarkManager.class);
                startActivity(intent);
            }
        });

    }
}