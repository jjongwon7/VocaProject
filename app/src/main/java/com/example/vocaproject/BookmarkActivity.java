package com.example.vocaproject;

import android.os.Bundle;

public class BookmarkActivity extends BookmarkManager{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // BookMark 에 해당하는 부분 출력
        for(int i=0;i<bookmarkSize;i++){
            if(bookmark[i] < 100000) {// 쓰레기값이 아니면으로 처리
            // studyActivity 출력문
            }
        }

    }
}
