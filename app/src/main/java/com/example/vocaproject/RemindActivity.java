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

    String key1 = "btn_airport";
    String key2 = "btn_transportation";
    String key3 = "btn_hotel";
    String key4 = "btn_sports";
    String key5 = "btn_restaurant";
    String key6 = "btn_shopping";
    String key7 = "btn_communication";
    String key8 = "btn_emergency";
    String num = "";

    int k;
    String[] word=new String[8];
    String[] korean=new String[8];


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        Intent intent = getIntent();
        key = intent.getStringExtra("KeyValue");

        remind = (TextView) findViewById(R.id.remind);
        backBtn = (Button) findViewById(R.id.btn_back);
        menuBtn = (Button) findViewById(R.id.btn_menu);

        if(key.equals(key1))
            num = "출입국·기내";
        else if(key.equals(key2))
            num = "교통";
        else if(key.equals(key3))
            num = "호텔";
        else if(key.equals(key4))
            num = "관광·스포츠";
        else if(key.equals(key5))
            num = "레스토랑";
        else if(key.equals(key6))
            num = "쇼핑";
        else if(key.equals(key7))
            num = "통신";
        else if(key.equals(key8))
            num = "긴급 상황";

        remind.setText(num+"\n리마인드\n \n(터치 하세요)");



        //  클릭리스너 등록
        remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                for(int i=0;i < num_of_word;i++){
//                    arrayList.get(i).getEnglish();
//                    arrayList.get(i).getKoreanMean();
//                }

                k = whosecall(key, num_of_word);
                for(int i=0;i < num_of_word;i++){
                    //1
                    word[i] = arrayList.get(k+i).getEnglish();
                    korean[i] = arrayList.get(k+i).getKoreanMean();
                    //1
                }

                backBtn.setVisibility(View.VISIBLE);

                switch((btn%2)){
                    case 0:
                        if(btn >= (num_of_word)*2){
                            //마지막 단어일 때(=index 번째 단어) 토스트 메시지 출력
                            Toast.makeText(getApplicationContext(), "마지막 단어입니다.", Toast.LENGTH_SHORT).show();
                        }else if(btn == 0){
                            btn++;
//                            remind.setText(""+arrayList.get(index).getEnglish());
                            remind.setText(""+word[index]);
                        }else{
                            btn++;
                            index++;
//                            remind.setText(""+arrayList.get(index).getEnglish());
                            remind.setText(""+word[index]);
                        }
                        break;
                    case 1:
                        btn++;
//                        remind.setText(""+arrayList.get(index).getEnglish()+"\n\n"+arrayList.get(index).getKoreanMean());
                        remind.setText(""+word[index]+"\n\n"+korean[index]);
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
//                        remind.setText(""+arrayList.get(index).getEnglish());
                        remind.setText(""+word[index]);
                        break;
                    case 1:
                        if(btn==1){
                            Toast.makeText(getApplicationContext(), "첫번째 단어입니다.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            btn--;
                            index--;
//                            remind.setText(""+arrayList.get(index).getEnglish()+"\n\n"+arrayList.get(index).getKoreanMean());
                            remind.setText(""+word[index]+"\n\n"+korean[index]);
                        }
                        break;
                }
            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RemindActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public int whosecall(String key, int num_of_word){
        //어떤 챕터에서 기능 호출했는지 확인하고, 그에 해당하는 단어를 배정해주는 함수
        int num=0;
        String key1 = "btn_airport";
        String key2 = "btn_transportation";
        String key3 = "btn_hotel";
        String key4 = "btn_sports";
        String key5 = "btn_restaurant";
        String key6 = "btn_shopping";
        String key7 = "btn_communication";
        String key8 = "btn_emergency";

        if(key.equals(key1))
            num = 0;
        else if(key.equals(key2))
            num = 4;
        else if(key.equals(key3))
            num = 8;
        else if(key.equals(key4))
            num = 12;
        else if(key.equals(key5))
            num = 16;
        else if(key.equals(key6))
            num = 20;
        else if(key.equals(key7))
            num = 24;
        else if(key.equals(key8))
            num = 28;

        return num;

    }
}
