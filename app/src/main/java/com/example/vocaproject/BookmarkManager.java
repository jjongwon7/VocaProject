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

public class BookmarkManager extends UserAccount {

    private static final String tag="MainActivity";
    int bookmarkSize;
    int[] bookmark = new int[100]; // update할 bookmark
    int[] bookmarkdb = new int[100]; // BookmarkActivity에서 활용할 DB에 올라가있는 bookmark
    static String currentID = ""; // 현재 로그인한 계정의 ID

    DatabaseReference bookmarkDB=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(currentID.equals("")){
            ;
        }
        else {
            bookmarkDB = FirebaseDatabase.getInstance().getReference("VocaProject").child("UserAccount").child(currentID).child("bookmarking");
            // 해당 계정의 계정 정보가 담긴 Firebase DB를 연동

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        bookmarkSize = 0;
        // DB에 업로드 되어 있는 북마크 단어 번호를 bookmark 배열에 저장
        if(bookmarkDB !=null)
        bookmarkDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 북마크 갯수 확인
                    bookmarkSize++;
                }


                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 list 추출
                    if(snapshot.hasChildren()) {
                        bookmarkdb[i] = Integer.parseInt(String.valueOf(snapshot.getValue())); // bookmark 배열에 DB에 저장되어 있는 북마크된 단어 번호 저장
                        i++;
                    } else{
                        break;
                    }

                }
                Log.d(tag,String.valueOf(bookmarkSize));
                for(int j=0;j<bookmarkSize;j++)
                    Log.d(tag,String.valueOf(bookmarkdb[j]));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MainActivity", String.valueOf(error.toException()));
            }
        });
        Log.d(tag,"ONRESUME BOOKMARKMANAGER");
        Log.d(tag, String.valueOf(bookmarkSize));
    }

    public void BookmarkUpdate(){

        // 중복되지 않게 DB clear
        DatabaseReference bookmarkDB = FirebaseDatabase.getInstance().getReference("VocaProject").child("UserAccount").child(currentID).child("bookmarking");
        bookmarkDB.removeValue();
        // StudyActivity에서 수정한 bookmark정보를 DB에 업로드
        int num= 20;
        for(int i=0;i<num;i++){
            if(bookmark[i]>=0) {
                newBookmarkUpload(bookmark[i]);
            }
        }

    }
    // 새로운 북마크 업로드 메소드, 북마크된 단어의 arrayList 인덱스를 인자로 넣으면 됨
    public void newBookmarkUpload(int wordNum){
        DatabaseReference bookmarkDB = FirebaseDatabase.getInstance().getReference("VocaProject").child("UserAccount").child(currentID).child("bookmarking");
        String newBookmark =String.valueOf(wordNum);
        bookmarkDB.child(newBookmark).setValue(wordNum);
    }

    public void bookmarkDownload(){

    }

    }
