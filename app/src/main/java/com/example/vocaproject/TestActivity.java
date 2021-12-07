package com.example.vocaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView.OnEditorActionListener; //OnEditorActionListener import하기
import java.util.ArrayList;
import android.widget.TextView;
import android.widget.Toast;

//로그인 정보 처리??
//단어 개수에 따른 프로그래스바 처리

public class TestActivity extends UserAccount implements OnEditorActionListener{ //OnEditorActionListener 인터페이스 implement하기

    private String key;
    private EditText inputText;
    ProgressBar progressBar;
    TextView textview;
    int num_of_word = 4; // 각 챕터 단어 개수
    TextView t_mean;
    TextView rs[] = new TextView[num_of_word];
    TextView Mean;
    TextView TV_1;
    TextView test_score;
    LinearLayout result;
    FrameLayout meanFrame;
    LinearLayout Ly;

    String str1 ;

    ArrayList<Word> arrayDB;
    int index = 0; // 단어 참조 인덱스
    int correct[] = new int [num_of_word]; //맞은놈 틀린놈 - 작업 필요
    int score;  //단어 갯수에 따른 아래 값 수정 필요
    int progress = (100/4); // 테스트 진도율 -수정 필요

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();
        key = intent.getStringExtra("KeyValue");

        inputText = (EditText)findViewById(R.id.editText);
        textview = (TextView)findViewById(R.id.textview_prog);
        inputText.setOnEditorActionListener(this); //EditText 위젯에 setOnEditorActionListener()를 연결시켜준다.
        test_score = (TextView)findViewById(R.id.result_test);
//        String inputStr = inputText.getText().toString().trim();

        t_mean = (TextView)findViewById(R.id.textView1);


        Mean = (TextView)findViewById(R.id.Mean);
        result = (LinearLayout) findViewById(R.id.result);
        Ly = (LinearLayout) findViewById(R.id.LY1);


        rs[0] = (TextView)findViewById(R.id.testresult1);
        rs[1] = (TextView)findViewById(R.id.testresult2);
        rs[2] = (TextView)findViewById(R.id.testresult3);
        rs[3] = (TextView)findViewById(R.id.testresult4);
        TV_1 = (TextView)findViewById(R.id.Tv_1);



        for(int i = 0; i < num_of_word; i++) {
            correct[i] = 1;
        }

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
//        int input = Integer.parseInt(inputStr);
        TV_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //처음 화면 터치 후 테스트 실행
                TV_1.setVisibility(View.INVISIBLE);
                Ly.setVisibility(View.VISIBLE);

                for(int i=0;i < num_of_word;i++){
                    arrayList.get(i).getEnglish();
                    arrayList.get(i).getKoreanMean();
                }
                t_mean.setText(arrayList.get(index).getKoreanMean());
                progressBar.setProgress(progress);

            }
        });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        //오버라이드한 onEditorAction() 메소드



        String strText;
        strText= inputText.getText().toString() ;
        // inputText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS); xml에도 선언했는데 왜 추천 단어 입력 해제가 안될까 ..

        if(v.getId()==R.id.editText && actionId==EditorInfo.IME_ACTION_DONE){
            // 뷰의 id를 식별, 키보드의 완료 키 입력 검출
            if(inputText.getText().toString().equals("") || inputText.getText().toString() == null){
                //빈값이 넘어올때의 처리
                Toast.makeText(getApplicationContext(), "단어를 입력하세요.", Toast.LENGTH_SHORT).show();
            }
            else {

                if(index<num_of_word ){
                    //각각의 테스트 입력결과 (정답, 오답)확인 후 다음 단어로 전환
                        progressBar.setProgress(progress);
                        str1 = arrayList.get(index).getEnglish();
//                        tv[0].setText(""+str1);
                        if (strText.equals(str1)) {
                            score += 10;
                            showToastcorr();
                        } else {
                            correct[index] = 0;
                            showToastincorr();

                        }
                        inputText.setText(null);
                        index++;
                        if(index<num_of_word)
                            t_mean.setText(arrayList.get(index).getKoreanMean());
                        progress += (100 / num_of_word);

                        if(index == num_of_word ){
                            inputText.setVisibility(View.GONE);
                            Mean.setVisibility(View.GONE);
                            t_mean.setVisibility(View.GONE);
                            //마지막 단어 테스트 후 결과 화면 출력
                            result.setVisibility(View.VISIBLE);
                            textview.setText("정답률");
                            progressBar.setProgress(score);
                            test_score.setText("User의 Part1 점수" + score + "점");
                            //오답인 애들 스크롤 뷰
                            for (int i = 0; i < num_of_word; i++) {
                                if (correct[i] == 0) {
                                    rs[i].setVisibility(View.VISIBLE);
                                    rs[i].setText("\n"+arrayList.get(i).getEnglish()+arrayList.get(i).getKoreanMean());
                                }
                            }
                        }
                }
            }
        }
       return false;
    }

    //커스텀 토스트 함수
    public void showToastcorr(){
        //editText로 사용자로부터 입력받은 문자열이 참일때 출력하는 토스트 메시지
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,(ViewGroup)findViewById(R.id.smile) );

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }
    public void showToastincorr(){
        //editText로 사용자로부터 입력받은 문자열이 거짓일때 출력하는 토스트 메시지
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout2,(ViewGroup)findViewById(R.id.unsmile) );

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
