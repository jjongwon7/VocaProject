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

public class BookmarkManager extends LoginActivity {

    private static final String tag="MainActivity";
    int bookmarkSize = 0;
    int[] bookmark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        DatabaseReference bookmarkDB = FirebaseDatabase.getInstance().getReference("VocaProject").child("UserAccount").child(currentID).child("bookmarking");
        // 해당 계정의 계정 정보가 담긴 Firebase DB를 연동



        // DB에 업로드 되어 있는 북마크 단어 번호를 bookmark 배열에 저장
        bookmarkDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                String t =String.valueOf(bookmarkSize);
                Log.d(tag, t);

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 북마크 갯수 확인
                    bookmarkSize++;
                }

                String s =String.valueOf(bookmarkSize);
                Log.d(tag, s);


                newBookmarkUpload(38);

                String l =String.valueOf(bookmarkSize);
                Log.d(tag, l);
                bookmark = new int[bookmarkSize]; // 북마크의 개수를 인덱스로 갖는 bookmark 배열 생성

                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 list 추출
                    bookmark[i] = Integer.parseInt(String.valueOf(snapshot.getValue())); // bookmark 배열에 DB에 저장되어 있는 북마크된 단어 번호 저장
                    Log.d(tag, String.valueOf(bookmark[i]));
                    i++;

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MainActivity", String.valueOf(error.toException()));
            }
        });
    }

    // 새로운 북마크 업로드 메소드, 북마크된 단어의 arrayList 인덱스를 인자로 넣으면 됨
    public void newBookmarkUpload(int wordNum){
        DatabaseReference bookmarkDB = FirebaseDatabase.getInstance().getReference("VocaProject").child("UserAccount").child(currentID).child("bookmarking");
        String newBookmark =String.valueOf(wordNum);
        bookmarkDB.child(newBookmark).setValue(wordNum);
    }

    }
