package com.example.vocaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View drawerView;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting 관련 코드
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.setting);

        ImageButton btn_setting = (ImageButton) findViewById(R.id.btn_setting);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        Button btn_close = (Button)findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        Button btn_background = (Button)findViewById(R.id.btn_background);
        btn_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BackgroundSettingActivity.class);
                startActivity(intent);
            }
        });

        Button btn_push = (Button)findViewById(R.id.btn_push);
        btn_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PushSettingActivity.class);
                startActivity(intent);
            }
        });

        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        //내 정보로 이동하는 코드
        ImageButton btn_info = (ImageButton) findViewById(R.id.btn_info);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InfoActivity.class);
                startActivity(intent);
            }
        });

        //dialog 관련 코드
        dialog = new Dialog(MainActivity.this); // dialog 초기화
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀제거
        dialog.setContentView(R.layout.dialog); // xml 레이아웃 파일과 연결

        Button btn_airport = (Button) findViewById(R.id.btn_airport);
        btn_airport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "btn_airport";
                showDialog(str);
            }
        });

        Button btn_transportation = (Button) findViewById(R.id.btn_transportation);
        btn_transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "btn_transportation";
                showDialog(str);
            }
        });

        Button btn_hotel = (Button) findViewById(R.id.btn_hotel);
        btn_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "btn_hotel";
                showDialog(str);
            }
        });

        Button btn_sports = (Button) findViewById(R.id.btn_sports);
        btn_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "btn_sports";
                showDialog(str);
            }
        });

        Button btn_restaurant = (Button) findViewById(R.id.btn_restaurant);
        btn_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "btn_restaurant";
                showDialog(str);
            }
        });

        Button btn_shopping = (Button) findViewById(R.id.btn_shopping);
        btn_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "btn_shopping";
                showDialog(str);
            }
        });

        Button btn_communication = (Button) findViewById(R.id.btn_communication);
        btn_communication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "btn_communication";
                showDialog(str);
            }
        });

        Button btn_emergency = (Button) findViewById(R.id.btn_emergency);
        btn_emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "btn_emergency";
                showDialog(str);
            }
        });
    }
        public void showDialog(String str){
            dialog.show(); // 다이얼로그 띄우기
            Button btn_study = (Button) dialog.findViewById(R.id.btn_study);
            Button btn_remind = (Button) dialog.findViewById(R.id.btn_remind);
            Button btn_test = (Button) dialog.findViewById(R.id.btn_test);

            btn_study.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, StudyActivity.class);
                    intent.putExtra("KeyValue", str);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });

            btn_remind.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, RemindActivity.class);
                    intent.putExtra("KeyValue", str);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });

            btn_test.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, TestActivity.class);
                    intent.putExtra("KeyValue", str);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });

        }
}