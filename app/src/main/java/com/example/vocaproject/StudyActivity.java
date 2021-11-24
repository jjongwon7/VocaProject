package com.example.vocaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.media.AudioManager;
import android.media.SoundPool;




public class StudyActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton leftBtn;
    ImageButton rightBtn;
    ImageButton Mark;

    private SoundPool soundPool;
    int sounduk, soundus;
    ImageButton sound1;
    ImageButton sound2;

    TextView text_progress;

    int Maxlength = 3; //단어 갯수

    int checked[] = new int[Maxlength];



    LinearLayout ly[] = new LinearLayout[Maxlength];
    // 여러개의 LinearLayout를 배열로 처리하기 위해 텍스트뷰에 대해 배열 선언을 함
    TextView word[] = new TextView[Maxlength];
    // 여러개의 텍스트뷰를 배열로 처리하기 위해 텍스트뷰에 대해 배열 선언을 함
    private String key;

    int index = 0; // 텍스트 뷰 참조 인덱스

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        Intent intent = getIntent();
        key = intent.getStringExtra("KeyValue");

        for(int i = 0; i < Maxlength; i++) {
            checked[i] = 0;
        }

        leftBtn = (ImageButton) findViewById(R.id.leftbtn);
        rightBtn = (ImageButton) findViewById(R.id.rightbtn);

        word[0] = (TextView) findViewById(R.id.word1_1);
        word[1] = (TextView) findViewById(R.id.word1_2);
        word[2] = (TextView) findViewById(R.id.word1_50);

        Mark = (ImageButton) findViewById(R.id.bookmark);
        sound1 = (ImageButton) findViewById(R.id.soundbtn1);
        sound2 = (ImageButton) findViewById(R.id.soundbtn2);

        text_progress = (TextView) findViewById(R.id.text_progress);
        ly[0] = (LinearLayout) findViewById(R.id.study1_1);
        ly[1] = (LinearLayout) findViewById(R.id.study1_2);
        ly[2] = (LinearLayout) findViewById(R.id.study1_50);

        // Button 이벤트 등록
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
        Mark.setOnClickListener(this);
        sound1.setOnClickListener(this);
        sound2.setOnClickListener(this);

        //sound
        soundPool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        sounduk = soundPool.load(this,R.raw.uk,1);
        soundus = soundPool.load(this,R.raw.us,1);

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.leftbtn:
                if(index == 0){
                 Toast.makeText(getApplicationContext(), "첫번째 단어입니다.", Toast.LENGTH_SHORT).show();
                }
                 else {
                     //해당 단어가 북마크 되어있는지 먼저 확인
                    if(checked[index-1] == 1) {
                        Mark.setImageResource(R.drawable.fill_star);
                    }else{
                        Mark.setImageResource(R.drawable.empty_star);
                    }
                     ly[index-1].setVisibility(View.VISIBLE); //이전 레이아웃
                     word[index-1].setVisibility(View.VISIBLE);
                     ly[index].setVisibility(View.GONE);  //현재 레이아웃
                     word[index].setVisibility(View.GONE);
                     index--;
                 }
                 text_progress.setText((index+1)+"/50");
                break;

            case R.id.rightbtn:

                if(index == (Maxlength-1)){
                    Toast.makeText(getApplicationContext(), "마지막 단어입니다. 링크를 연결합니다.", Toast.LENGTH_SHORT).show();
                    Intent urintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.naver.com"));
                    startActivity(urintent);
                }
                else {
                    //해당 단어가 북마크 되어있는지 먼저 확인
                    if(checked[index+1] == 1) {
                        Mark.setImageResource(R.drawable.fill_star);
                    }else{
                        Mark.setImageResource(R.drawable.empty_star);
                    }
                    ly[index+1].setVisibility(View.VISIBLE); //다음 레이아웃
                    word[index+1].setVisibility(View.VISIBLE);
                    ly[index].setVisibility(View.GONE); //현재 레이아웃
                    word[index].setVisibility(View.GONE);
                    index++;
                }
                text_progress.setText((index+1)+"/50"); //참조 인덱스 확인하여 몇번째인지 출력
                break;

            case R.id.bookmark:
                //북마크 됐으면 배열에 값 넣어주기

                if(checked[index] == 0) {
                    Mark.setImageResource(R.drawable.fill_star);
                    checked[index] = 1;
                }else{
                    Mark.setImageResource(R.drawable.empty_star);
                    checked[index] = 0;
                }

                break;

            case R.id.soundbtn1:
                //단어에 따라 사운드 변환시켜줘야
                Toast.makeText(getApplicationContext(), "uk", Toast.LENGTH_SHORT).show();
                soundPool.play(soundus,1f,1f,0,0,1f);
                break;

            case R.id.soundbtn2:
                //단어에 따라 사운드 변환시켜줘야 함
                Toast.makeText(getApplicationContext(), "us", Toast.LENGTH_SHORT).show();
                soundPool.play(sounduk, 1f, 1f, 0, 0, 1f);
                break;

        }

    }
}
