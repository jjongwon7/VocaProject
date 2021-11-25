package com.example.vocaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookmarkActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        DatabaseReference userDB = FirebaseDatabase.getInstance().getReference("VocaProject").child("UserAccount"); // Firebase DB의 UserAccount와 연동

    }
}