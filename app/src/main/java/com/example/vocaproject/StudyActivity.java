package com.example.vocaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.ArrayList;

//음성파일 처리여부?
//인텐트 담긴 카테고리 전환 코드 작성 필요

public class StudyActivity extends BookmarkManager implements View.OnClickListener {

    private static final String TAG="MainActivity";


    Button back;
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
    String[] word1=new String[8];
    String[] korean=new String[8];


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

        back = (Button)findViewById(R.id.study_btn_back);

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
        back.setOnClickListener(this);

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

        Tv.setText(num+"\n학습하기\n"+"\n \n(터치 하세요)");




    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onRestart");
        for (int i = 0; i < 10; i++) {

            //checked[bookmarkdb[i]] = 1;
        }
        Log.d(TAG, String.valueOf(bookmarkSize));

    }

    @Override
    public void onClick(View v) {

//        //FireBase의 DB에서 사용할 단어와 뜻을 arrayList에 저장


      //  Log.d(TAG, "onclick");
      //  Log.d(TAG, String.valueOf(bookmarkSize));
        k = whosecall(key, Maxlength);
        for(int i=0;i < Maxlength;i++){
            //1
            word1[i] = arrayList.get(k+i).getEnglish();
            korean[i] = arrayList.get(k+i).getKoreanMean();
            //1
        }

        switch (v.getId()) {
            case R.id.study_btn_back:
                Intent intent = new Intent(StudyActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.Tv1:
                //뷰에 처음으로 터치 이벤트 발생 시 학습하기 페이지로 넘어감
                Tv.setVisibility(View.GONE);
                Ly.setVisibility(View.VISIBLE);

                //첫번째 단어 출력
                //word.setText(""+arrayList.get(0).getEnglish());
                //mean.setText(""+arrayList.get(0).getKoreanMean());

                word.setText(""+word1[index]);
                mean.setText(""+korean[index]);
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
//                     word.setText(""+arrayList.get(index-1).getEnglish());
//                     mean.setText(""+arrayList.get(index-1).getKoreanMean());
                    word.setText(""+word1[index-1]);
                    mean.setText(""+korean[index-1]);

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
//                    word.setText(""+arrayList.get(index+1).getEnglish());
//                    mean.setText(""+arrayList.get(index+1).getKoreanMean());
                    word.setText(""+word1[index+1]);
                    mean.setText(""+korean[index+1]);
                    index++;
                }
                text_progress.setText((index+1)+"/20"); //참조 인덱스 확인하여 몇번째인지 출력
                break;

            case R.id.bookmark:
                //북마크 됐으면 배열에 값 넣어주기
                //id 안되어있으면 불가능하다고 Toast 메세지 띄우기

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

    @Override
    protected void onPause() { // 학습하기 화면이 꺼지면 checked[] 에 저장된 bookmark 정보를 BookmarkManager 클래스의 bookmark[] 에 저장
        super.onPause();


        if(currentID.equals("")){
            Log.d(TAG,"onPause but null");
        }
        else {
            Log.d(TAG,currentID);
            bookMarkTranslate(checked);
            BookmarkUpdate();
        }
    }

    private void bookMarkTranslate(int[] checked){
        int k=0;
        for(int i=0;i<Maxlength;i++){
            bookmark[i]=-1;
        }
        for(int i=0;i<Maxlength;i++) {
            if(checked[i]==1) {
                bookmark[k] = i;
                k++;
            }else ;
        }
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
