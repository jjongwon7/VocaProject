package com.example.vocaproject;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WordDBOpen extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;  //database를 참조하는 객체
    public ArrayList<Word> arrayList;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = new ArrayList<>(); // word 객체를 담을 리스트


        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("VocaProject").child("Word"); // firebass의 database의 참조를 포함하는 객체에 realtime DB연동
        databaseReference.addValueEventListener(new ValueEventListener() { // arrayList<Word>에 realtimeDB에 있는 데이터를 옮김
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳

                arrayList.clear(); // 기존 배열리스트가 존재하지 않게 add하기 전에 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){ // 반복문으로 데이터 list 추출

                    Word word = snapshot.getValue(Word.class); // 만들어뒀던 Word 객체에 데이터를 담는다
                    arrayList.add(word); //담은 데이터들을 베열리스트에 넣음


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MainActivity", String.valueOf(error.toException()));
            }
        });

    }
}
