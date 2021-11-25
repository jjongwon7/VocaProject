package com.example.vocaproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RemindActivity extends UserAccount implements View.OnClickListener {

    ArrayList<Word> arrayDB;
    Button backBtn;
    Button menuBtn;
    private String key;
    // 여러개의 텍스트뷰를 배열로 처리하기 위해 텍스트뷰에 대해 배열 선언을 함
    TextView tv[] = new TextView[6];

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        Intent intent = getIntent();
        key = intent.getStringExtra("KeyValue");



        


        tv[0] = (TextView) findViewById(R.id.rem_1_1_1);
        tv[1] = (TextView) findViewById(R.id.rem_1_1_2);
        tv[2] = (TextView) findViewById(R.id.rem_1_2_1);
        tv[3] = (TextView) findViewById(R.id.rem_1_2_2);
        tv[4] = (TextView) findViewById(R.id.rem_1_3_1);
        tv[5] = (TextView) findViewById(R.id.rem_1_3_2);

        // 버튼들에 대한 클릭리스너 등록
        for (int i = 0; i < 6; i++) {
            // 버튼의 포지션(배열에서의 index)를 태그로 저장
            // tv[i].setTag(i);

            // 클릭 리스너 등록
            tv[i].setOnClickListener(this);

        }
        backBtn = (Button) findViewById(R.id.btn_back);
        menuBtn = (Button) findViewById(R.id.btn_menu);
        // Button 이벤트 등록
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                switch (v.getId()) {
//                    //각각의 텍스트 뷰 클릭 시, 다음 뷰로 화면 전환
//                    case R.id.rem_1_1_1:
//                        //첫번째 뷰에서 클릭 시 토스트 메세지 출력
//                        Toast.makeText(getApplicationContext(), "첫번째 단어입니다.", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.rem_1_1_2:
//                        tv[1].setVisibility(View.GONE);
//                        tv[0].setVisibility(View.VISIBLE);
//                        break;
//                    case R.id.rem_1_2_1:
//                        tv[2].setVisibility(View.GONE);
//                        tv[1].setVisibility(View.VISIBLE);
//                        break;
//                    case R.id.rem_1_2_2:
//                        tv[3].setVisibility(View.GONE);
//                        tv[2].setVisibility(View.VISIBLE);
//                        break;
//                    case R.id.rem_1_3_1:
//                        tv[4].setVisibility(View.GONE);
//                        tv[3].setVisibility(View.VISIBLE);
//                        break;
//                    case R.id.rem_1_3_2:
//                        tv[5].setVisibility(View.GONE);
//                        tv[4].setVisibility(View.VISIBLE);
//                        break;
//  Back 누르면 이전 view 보이게 하고싶은데 구현중 입니다,,
//                }
            }
        });
//메뉴버튼 말고 그냥 뒤로가기 눌러도 동작 되는데 굳이 넣어야하나 고민중 입니다..
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    // 클릭 이벤트 콜백
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //각각의 텍스트 뷰 클릭 시, 다음 뷰로 화면 전환
            case R.id.rem_1_1_1:

                tv[0].setVisibility(View.GONE);
                tv[1].setVisibility(View.VISIBLE);

                for(int i=0;i<30;i++){
                    arrayList.get(i).getEnglish();
                    arrayList.get(i).getKoreanMean();
                }



                break;
            case R.id.rem_1_1_2:
                tv[1].setVisibility(View.GONE);
                tv[2].setVisibility(View.VISIBLE);
                break;
            case R.id.rem_1_2_1:
                tv[2].setVisibility(View.GONE);
                tv[3].setVisibility(View.VISIBLE);
                break;
            case R.id.rem_1_2_2:
                tv[3].setVisibility(View.GONE);
                tv[4].setVisibility(View.VISIBLE);
                break;
            case R.id.rem_1_3_1:
                tv[4].setVisibility(View.GONE);
                tv[5].setVisibility(View.VISIBLE);
                break;
            case R.id.rem_1_3_2:
                //마지막 페이지 클릭 시 토스트 메세지 출력
                Toast.makeText(getApplicationContext(), "마지막 단어입니다.", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}

