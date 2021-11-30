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
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class PushSettingActivity extends AppCompatActivity {

    private Button btn_save,btn_cancel;
    private TimePicker timePicker;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_setting);

        timePicker=(TimePicker) findViewById(R.id.time_picker);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        btn_save.setOnClickListener(v -> {
            Calendar calender = Calendar.getInstance();
            calender.setTimeInMillis(System.currentTimeMillis());
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();
            calender.set(Calendar.HOUR_OF_DAY,hour);
            calender.set(Calendar.MINUTE,minute);

            if(calender.before(Calendar.getInstance())){
                calender.add(Calendar.DATE,1);
            }

            AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

            if(alarmManager != null){
                Intent intent = new Intent(this, AlarmReceiver.class);
                PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), AlarmManager.INTERVAL_DAY,alarmIntent);

                Toast.makeText(PushSettingActivity.this, "알람이 저장되었습니다.", Toast.LENGTH_LONG).show();
            }
        });

        btn_cancel.setOnClickListener();
    }
}