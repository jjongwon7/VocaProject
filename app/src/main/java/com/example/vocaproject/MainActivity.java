package com.example.vocaproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainActivity extends Calender{

    private static final String TAG = "MainActivity";
    private DrawerLayout drawerLayout;
    private View drawerView;
    Dialog dialog;
    Dialog dialogBackground;
    private Date tHistory; // 학습하기,리마인드,테스트 중 선택하였을 때 history 변수에 오늘 날짜를 저장
    String themeColor;
    boolean isClicked=false; // false: 라이트모드 true: 다크모드


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Log.d(TAG,"DB 이상무");
        Log.d(TAG,history);
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

        dialogBackground = new Dialog(MainActivity.this); // dialog 초기화
        dialogBackground.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀제거
        dialogBackground.setContentView(R.layout.background_dialog); // xml 레이아웃 파일과 연결

        themeColor = ThemeUtil.modLoad(getApplicationContext());
        ThemeUtil.applyTheme(themeColor);

        Button btn_background = (Button)findViewById(R.id.btn_background);
        btn_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogBackground();
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
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String str = "btn_airport";
                showDialog(str);
            }
        });

        Button btn_transportation = (Button) findViewById(R.id.btn_transportation);
        btn_transportation.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String str = "btn_transportation";
                showDialog(str);
            }
        });

        Button btn_hotel = (Button) findViewById(R.id.btn_hotel);
        btn_hotel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String str = "btn_hotel";
                showDialog(str);
            }
        });

        Button btn_sports = (Button) findViewById(R.id.btn_sports);
        btn_sports.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String str = "btn_sports";
                showDialog(str);
            }
        });

        Button btn_restaurant = (Button) findViewById(R.id.btn_restaurant);
        btn_restaurant.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String str = "btn_restaurant";
                showDialog(str);
            }
        });

        Button btn_shopping = (Button) findViewById(R.id.btn_shopping);
        btn_shopping.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String str = "btn_shopping";
                showDialog(str);
            }
        });

        Button btn_communication = (Button) findViewById(R.id.btn_communication);
        btn_communication.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String str = "btn_communication";
                showDialog(str);
            }
        });

        Button btn_emergency = (Button) findViewById(R.id.btn_emergency);
        btn_emergency.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String str = "btn_emergency";
                showDialog(str);
            }
        });
    }
        @RequiresApi(api = Build.VERSION_CODES.O)
        public void showDialogBackground() {
            dialogBackground.show(); // 다이얼로그 띄우기
            RadioButton btn_light = (RadioButton) dialogBackground.findViewById(R.id.btn_light);
            RadioButton btn_dark = (RadioButton) dialogBackground.findViewById(R.id.btn_dark);

            btn_light.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    themeColor = ThemeUtil.LIGHT_MODE;
                    ThemeUtil.applyTheme(themeColor);
                    ThemeUtil.modSave(getApplicationContext(), themeColor);
                    dialog.dismiss();
                }
            });
            btn_dark.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    themeColor = ThemeUtil.DARK_MODE;
                    ThemeUtil.applyTheme(themeColor);
                    ThemeUtil.modSave(getApplicationContext(), themeColor);
                    dialog.dismiss();
                }
            });
        }
        @RequiresApi(api = Build.VERSION_CODES.O)
        public void showDialog(String str){
            dialog.show(); // 다이얼로그 띄우기
            Button btn_study = (Button) dialog.findViewById(R.id.btn_study);
            Button btn_remind = (Button) dialog.findViewById(R.id.btn_remind);
            Button btn_test = (Button) dialog.findViewById(R.id.btn_test);


            btn_study.setOnClickListener(new View.OnClickListener()
            {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    tHistory = new Date(); // 다이얼로그의 학습하기 버튼이 눌렸을 경우 현재 시간을 history에 저장
                    SimpleDateFormat fHistory = new SimpleDateFormat("yyyyMMdd");
                    history = fHistory.format(tHistory);
//                    Intent intent = new Intent(MainActivity.this, StudyActivity.class);
//                    intent.putExtra("KeyValue", str);
//                    startActivity(intent);
                    dialog.dismiss();
                }
            });

            btn_remind.setOnClickListener(new View.OnClickListener()
            {
                @RequiresApi(api = Build.VERSION_CODES.O)
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
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, TestActivity.class);
                    intent.putExtra("KeyValue", str);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });
        }

        private static void transferHistory(String formattedHistory){
        Calender.history = formattedHistory;
        }
}