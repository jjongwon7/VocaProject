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

import java.util.ArrayList;

//음성파일 처리여부?
//인텐트 담긴 카테고리 전환 코드 작성 필요

public class StudyActivity extends WordDBOpen implements View.OnClickListener {

    ArrayList<Word> arrayDB;

    ImageButton leftBtn;
    ImageButton rightBtn;
    ImageButton Mark;

    private SoundPool soundPool;
    int sounduk, soundus;
    ImageButton sound1;
    ImageButton sound2;

    TextView text_progress,Tv,mean;
    LinearLayout Ly;

    int Maxlength = 4; //단어 갯수

    int checked[] = new int[Maxlength];




    // 여러개의 LinearLayout를 배열로 처리하기 위해 텍스트뷰에 대해 배열 선언을 함
    TextView word;
    // 여러개의 텍스트뷰를 배열로 처리하기 위해 텍스트뷰에 대해 배열 선언을 함
    private String key;

    int index = 0; // 텍스트 뷰 참조 인덱스


    // arrayList -> 한 행 구성 category, english, koreanMean, voice
    // arrayList에서 첫 번째 영단어 빼기 arrayList.get(0).getEnglish();
    // arrayList에서 첫 번째 영단어 뜻 빼기 arrayList.get(0).getKoreanMean();
    // 카테고리 전환시 MainActivity에서 넘어온  intent에 담긴 str을 활용하여 switch 문으로 하면 될 듯 <- 이 말 이해안되면 연락하세용 ~
    // ex) equals(str,"btn_transportation") -> arrayList.get(20*2).getEnglish();

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

        word = (TextView) findViewById(R.id.word);
        Tv = (TextView)findViewById(R.id.Tv1);
        Ly = (LinearLayout)findViewById(R.id.Ly1);

        Mark = (ImageButton) findViewById(R.id.bookmark);
        sound1 = (ImageButton) findViewById(R.id.soundbtn1);
        sound2 = (ImageButton) findViewById(R.id.soundbtn2);

        text_progress = (TextView) findViewById(R.id.text_progress);
        mean = (TextView) findViewById(R.id.mean);


        // Button 이벤트 등록
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
        Mark.setOnClickListener(this);
        sound1.setOnClickListener(this);
        sound2.setOnClickListener(this);
        Tv.setOnClickListener(this);
        //sound
        soundPool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        sounduk = soundPool.load(this,R.raw.uk,1);
        soundus = soundPool.load(this,R.raw.us,1);



    }
    @Override
    public void onClick(View v) {

//        //FireBase의 DB에서 사용할 단어와 뜻을 arrayList에 저장
        for(int i=0;i<4;i++){
            arrayList.get(i).getEnglish();
            arrayList.get(i).getKoreanMean();
        }



        switch (v.getId()) {

            case R.id.Tv1:
                //뷰에 처음으로 터치 이벤트 발생 시 학습하기 페이지로 넘어감
                Tv.setVisibility(View.GONE);
                Ly.setVisibility(View.VISIBLE);

                //첫번째 단어 출력
                word.setText(""+arrayList.get(0).getEnglish());
                mean.setText(""+arrayList.get(0).getKoreanMean());
                break;

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
                    // 이전 인덱스에 해당하는 단어와 뜻으로 화면 구성하기
                     word.setText(""+arrayList.get(index-1).getEnglish());
                     mean.setText(""+arrayList.get(index-1).getKoreanMean());

                     index--;
                 }
                 text_progress.setText((index+1)+"/20");
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
                    // 다음 인덱스에 해당하는 단어와 뜻으로 화면 구성하기
                    word.setText(""+arrayList.get(index+1).getEnglish());
                    mean.setText(""+arrayList.get(index+1).getKoreanMean());
                    index++;
                }
                text_progress.setText((index+1)+"/20"); //참조 인덱스 확인하여 몇번째인지 출력
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
                //단어에 따라 사운드 변환시켜줘야 함
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
