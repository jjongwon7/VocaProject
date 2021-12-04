package com.example.vocaproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class PushSettingActivity extends AppCompatActivity {

    private Button btn_save, btn_cancel; // 저장, 설정 취소 버튼
    private TimePicker timePicker; // 시간 설정

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_setting);

        timePicker = (TimePicker) findViewById(R.id.time_picker);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        // 알림 설정
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calendar 객체 생성
                Calendar calender = Calendar.getInstance();

                // 단말기 현재시간으로 설정
                calender.setTimeInMillis(System.currentTimeMillis());

                // timePicker 값 저장
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                // timePicker에 설정된 값을 저장
                calender.set(Calendar.HOUR_OF_DAY, hour);
                calender.set(Calendar.MINUTE, minute);

                // AlarmManager 생성
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                // 알람 설정
                if (alarmManager != null) {
                    Intent intent = new Intent(PushSettingActivity.this, AlarmReceiver.class);

                    // BroadcastReceiver를 시작하는 인텐트 생성
                    PendingIntent alarmIntent = PendingIntent.getBroadcast(PushSettingActivity.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    // 알람을 매일마다 반복
                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);

                    Toast.makeText(PushSettingActivity.this, "알람이 저장되었습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });

        // 알림 취소
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(PushSettingActivity.this, AlarmReceiver.class);
                PendingIntent cancelIntent = PendingIntent.getBroadcast(PushSettingActivity.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.cancel(cancelIntent);
                Toast.makeText(PushSettingActivity.this, "더이상 알람이 울리지 않습니다.", Toast.LENGTH_LONG).show();

            }
        });
    }
}