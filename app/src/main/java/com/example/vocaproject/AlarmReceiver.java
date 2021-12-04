package com.example.vocaproject;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private Context context;
    private String channelId="alarm_channel"; // Channel id 생성
    private final int id = 0; // Notification id 생성

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        // 알람 클릭시 이동할 인텐트 생성
        Intent intentMain = new Intent(context, MainActivity.class);
        intentMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Activity를 시작하는 인텐트 생성
        PendingIntent pendingIntent = PendingIntent.getActivity(context,1,intentMain, 0);

        // 알람 콘텐츠 설정
        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_banner).setDefaults(Notification.DEFAULT_ALL) // 아이콘
                .setAutoCancel(true) // 알림을 탭하면 자동으로 알림 삭제
                .setContentTitle("여행 가VOCA") // 제목
                .setContentText("단어 공부할 시간입니다.") // 본문 텍스트
                .setContentIntent(pendingIntent);


        // notification manager 생성
        final NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // 기기 SDK 버전 확인
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            // Channel 정의 생성자
            NotificationChannel channel = new NotificationChannel(channelId, "Alarm", NotificationManager.IMPORTANCE_DEFAULT);
            // Channel 생성
            notificationManager.createNotificationChannel(channel);
        }

        // notification 보냄
        notificationManager.notify(id,notificationBuilder.build());
    }
}
