package com.example.vocaproject;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.util.Arrays;

public class Calender extends LoginActivity {

    private static final String tag = "MainActivity";
    private DatePicker datePicker;
    private TextView textView;
    static String history = "No Study this Date";
    private boolean[] dates = new boolean[32];

    public Calender(){}


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        DatabaseReference userDB = FirebaseDatabase.getInstance().getReference("VocaProject").child("UserAccount"); // Firebase DB의 UserAccount와 연동

        Arrays.fill(dates,false);  // dates 배열 false로 초기화

        if(history!="No Study this Date"){ //오늘 학습을 했을시
            int translateHistory = Integer.parseInt(history);
            for(int i=0;i<32;i++){
                dates[translateHistory%32] = true; // dates 배열의 오늘 날짜 인덱스에 true를 저장
            }

            userDB.child(currentID).child("history").child(history).setValue(translateHistory);// Firebase 계정 DB에 푸쉬

        }

        datePicker = findViewById(R.id.datePicker);
        textView = findViewById(R.id.textview);


        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view,int year, int month,int date) {

                // if() -> 선택된 날짜와 history가 맞다면 학습했음 출력, 아닐시 안 했음 출력
                textView.setText(history);
                // 학습 날짜 정보에 따라 학습했음으로 setText 해주면 될 듯 -> 색칠은 구현 가능할지 아직 모르겠다..
            }
        });
    }
}
