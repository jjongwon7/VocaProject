package com.example.vocaproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WordDBOpen extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ArrayList<Word> arrayList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = new ArrayList<>(); // word 객체를 담을 리스트
        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("Word"); //DB테이블 연결


    }
}
