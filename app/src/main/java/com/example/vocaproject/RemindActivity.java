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

public class RemindActivity extends UserAccount {

    ArrayList<Word> arrayDB;
    Button backBtn;
    Button menuBtn;
    int index =0;
    int num_of_word = 4; // 각 챕터 단어 개수
    int checkclk,clicked=0;
    int btn = 0;
    private String key;
    // 여러개의 텍스트뷰를 배열로 처리하기 위해 텍스트뷰에 대해 배열 선언을 함
    TextView remind;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        Intent intent = getIntent();
        key = intent.getStringExtra("KeyValue");

        remind = (TextView) findViewById(R.id.remind);
        backBtn = (Button) findViewById(R.id.btn_back);
        menuBtn = (Button) findViewById(R.id.btn_menu);


        //  클릭리스너 등록
        remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0;i < num_of_word;i++){
                    arrayList.get(i).getEnglish();
                    arrayList.get(i).getKoreanMean();
                }

                backBtn.setVisibility(View.VISIBLE);

                switch((btn%2)){
                    case 0:
                        if(btn >= (num_of_word)*2){
                            //마지막 단어일 때(=index 번째 단어) 토스트 메시지 출력
                            Toast.makeText(getApplicationContext(), "마지막 단어입니다.", Toast.LENGTH_SHORT).show();
                        }else if(btn == 0){
                            btn++;
                            remind.setText(""+arrayList.get(index).getEnglish());
                        }else{
                            btn++;
                            index++;
                            remind.setText(""+arrayList.get(index).getEnglish());
                        }
                        break;
                    case 1:
                        btn++;
                        remind.setText(""+arrayList.get(index).getEnglish()+arrayList.get(index).getKoreanMean());
                        break;
                }
            }
        });

        // Button 이벤트 등록
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch((btn % 2)){
                    case 0:
                        btn--;
                        remind.setText(""+arrayList.get(index).getEnglish());
                        break;
                    case 1:
                        if(btn==1){
                            Toast.makeText(getApplicationContext(), "첫번째 단어입니다.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            btn--;
                            index--;
                            remind.setText(""+arrayList.get(index).getEnglish()+arrayList.get(index).getKoreanMean());
                        }
                        break;
                }
            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RemindActivity.this,MainActivity.class);
            }
        });

    }

}
