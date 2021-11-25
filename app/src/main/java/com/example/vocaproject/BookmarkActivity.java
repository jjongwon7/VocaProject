package com.example.vocaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookmarkActivity extends LoginActivity {

    private static final String tag="MainActivity";
    int bookmarkSize = 0;
    int[] bookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        DatabaseReference bookmarkDB = FirebaseDatabase.getInstance().getReference("VocaProject").child("UserAccount").child("bookmarking"); // Firebase DB의 UserAccount와 연동


        bookmarkDB.addValueEventListener(new ValueEventListener() { //
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳

                bookmark =new int[3];
               while(dataSnapshot.hasChildren()){ // 반복문
                   bookmarkSize++;
                }
                String t = Integer.toString(bookmarkSize);
                Log.d(tag,"bookmarkactivity");
                Log.d(tag,t);


                bookmark = new int[bookmarkSize];

                // 기존 배열리스트가 존재하지 않게 add 하기 전에 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){ // 반복문으로 데이터 list 추출

//                    Word word = snapshot.getValue(Word.class); // 만들어뒀던 Word 객체에 데이터를 담는다
//                    arrayList.add(word); //담은 데이터들을 베열리스트에 넣음


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MainActivity", String.valueOf(error.toException()));
            }
        });

    }
        // DB 에서 BookMark 를 받아옴

        // BookMark 에 해당하는 부분 출력
    }
