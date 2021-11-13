package com.example.vocaproject;

import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Calender extends AppCompatActivity {

    private DatePicker datePicker;
    private TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        datePicker = findViewById(R.id.datePicker);
        textView = findViewById(R.id.textview);

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                textView.setText(year +"년"+monthOfYear + "월"+dayOfMonth+"일");
                // 학습 날짜 정보에 따라 학습했음으로 setText 해주면 될 듯 -> 색칠은 구현 가능할지 아직 모르겠다..
            }
        });
    }
}
