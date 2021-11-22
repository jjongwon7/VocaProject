package com.example.vocaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class StudyActivity extends WordDBOpen implements View.OnClickListener {
    ImageButton leftBtn;
    ImageButton rightBtn;
    ImageButton Mark1;
    ImageButton Mark2;
    ImageButton Mark50;
    ImageButton sound1_1;
    ImageButton sound1_2;
    ImageButton sound2_1;
    ImageButton sound2_2;
    ImageButton sound50_1;
    ImageButton sound50_2;
    LinearLayout ly[] = new LinearLayout[3];

    private String key;
    // 여러개의 텍스트뷰를 배열로 처리하기 위해 텍스트뷰에 대해 배열 선언을 함

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        Intent intent = getIntent();
        key = intent.getStringExtra("KeyValue");


//        leftBtn = (ImageButton) findViewById(R.id.leftbtn);
//        rightBtn = (ImageButton) findViewById(R.id.rightbtn);
//        Mark1 = (ImageButton) findViewById(R.id.bookmark1_1);
//        Mark2 = (ImageButton) findViewById(R.id.bookmark1_2);
//        Mark50 = (ImageButton) findViewById(R.id.bookmark1_50);
//        sound1_1 = (ImageButton) findViewById(R.id.soundbtn1_1);
//        sound1_2 = (ImageButton) findViewById(R.id.soundbtn1_2);
//        sound2_1 = (ImageButton) findViewById(R.id.soundbtn2_1);
//        sound2_2 = (ImageButton) findViewById(R.id.soundbtn2_2);
//        sound50_1 = (ImageButton) findViewById(R.id.soundbtn1_50_1);
//        sound50_2 = (ImageButton) findViewById(R.id.soundbtn1_50_2);
//        ly[0] = (LinearLayout) findViewById(R.id.study1_1);
//        ly[1] = (LinearLayout) findViewById(R.id.study1_2);
//        ly[2] = (LinearLayout) findViewById(R.id.study1_50);


        // Button 이벤트 등록
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
//        if(v == leftBtn) {
//            switch (v.getId()) {
//                //각각의 텍스트 뷰 클릭 시, 다음 뷰로 화면 전환
//                case R.id.study1_1:
//                    //첫번째 뷰에서 클릭 시 토스트 메세지 출력
//                    Toast.makeText(getApplicationContext(), "첫번째 단어입니다.", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.study1_2:
//                    ly[1].setVisibility(View.GONE);
//                    ly[0].setVisibility(View.VISIBLE);
//                    break;
//                case R.id.study1_50:
//                    ly[2].setVisibility(View.GONE);
//                    ly[1].setVisibility(View.VISIBLE);
//                    break;
//
//            }
//        } else if(v == rightBtn) {
//            switch (v.getId()) {
//                //각각의 텍스트 뷰 클릭 시, 다음 뷰로 화면 전환
//                case R.id.study1_1:
//                    ly[0].setVisibility(View.GONE);
//                    ly[1].setVisibility(View.VISIBLE);
//                    break;
//                case R.id.study1_2:
//                    ly[1].setVisibility(View.GONE);
//                    ly[2].setVisibility(View.VISIBLE);
//                    break;
//                case R.id.study1_50:
//                    //마지막 페이지 클릭 시 토스트 메세지 출력
//                    Toast.makeText(getApplicationContext(), "마지막 단어입니다. 링크를 연결합니다.", Toast.LENGTH_SHORT).show();
//                    intent.setData(Uri.parse("https://youtu.be/JZUDg2DdbbM"));
//                    startActivity(intent);
//                    break;
//
//            }
        }
//        switch (view.getId()) {
//            //각각의 텍스트 뷰 클릭 시, 다음 뷰로 화면 전환
//            case R.id.rem_1_1_1:
//                tv[0].setVisibility(View.GONE);
//                tv[1].setVisibility(View.VISIBLE);
//
//                break;
//            case R.id.rem_1_1_2:
//                tv[1].setVisibility(View.GONE);
//                tv[2].setVisibility(View.VISIBLE);
//                break;
//            case R.id.rem_1_2_1:
//                tv[2].setVisibility(View.GONE);
//                tv[3].setVisibility(View.VISIBLE);
//                break;
//            case R.id.rem_1_2_2:
//                tv[3].setVisibility(View.GONE);
//                tv[4].setVisibility(View.VISIBLE);
//                break;
//            case R.id.rem_1_3_1:
//                tv[4].setVisibility(View.GONE);
//                tv[5].setVisibility(View.VISIBLE);
//                break;
//            case R.id.rem_1_3_2:
//                //마지막 페이지 클릭 시 토스트 메세지 출력
//                Toast.makeText(getApplicationContext(), "마지막 단어입니다.", Toast.LENGTH_SHORT).show();
//                break;
//
//        }
}
