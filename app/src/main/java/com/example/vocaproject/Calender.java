package com.example.vocaproject;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;

public class Calender extends UserAccount {

    private static final String tag = "MainActivity";
    private DatePicker datePicker;
    private TextView textView;
    static String history = "none";
    private int translateHistory;
    public Calender(){}

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        if(history!="none"){
            translateHistory = Integer.parseInt(history);
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
