//왜 edit text 입력이 안될까 ,,,,, 수정중 ,,
package com.example.vocaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView.OnEditorActionListener; //OnEditorActionListener import하기

import android.widget.TextView;
import android.widget.Toast;


public class TestActivity extends UserAccount implements OnEditorActionListener{ //OnEditorActionListener 인터페이스 implement하기

    private String key;
    private EditText editText;
    ProgressBar progressBar;
    TextView textview;
    TextView tv[] = new TextView[50];
    LinearLayout result;
    String str1 = "passport";
    String str2 = "airport";
    String str3 = "boarding pass";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent = getIntent();
        key = intent.getStringExtra("KeyValue");
        editText = (EditText)findViewById(R.id.editText);
        textview = (TextView)findViewById(R.id.textview_prog);
        String inputStr = editText.getText().toString().trim();
        editText.setOnEditorActionListener((OnEditorActionListener) this);
        tv[0] = (TextView)findViewById(R.id.textView1);
        tv[1] = (TextView)findViewById(R.id.textView2);
        tv[49] = (TextView)findViewById(R.id.textView3);
        result = (LinearLayout) findViewById(R.id.result);


//        progressBar = (ProgressBar)findViewById(R.id.progressBar);
//        int input = Integer.parseInt(inputStr);
//        progressBar.setProgress(input);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        //오버라이드한 onEditorAction() 메소드
        String strText = editText.getText().toString() ;
        if(v.getId()==R.id.editText && actionId==EditorInfo.IME_ACTION_DONE){ // 뷰의 id를 식별, 키보드의 완료 키 입력 검출
            if(editText.getText().toString().equals("") || editText.getText().toString() == null){//빈값이 넘어올때의 처리
                Toast.makeText(getApplicationContext(), "단어를 입력하세요.", Toast.LENGTH_SHORT).show();
            }
            else {
                switch (v.getId()) {
                    //각각의 텍스트 뷰 클릭 시, 다음 뷰로 화면 전환
                    case R.id.textView1:
                        if(strText.equals(str1)){
                        //바 숫자 갱신 해야함
                        showToastcorr();
                    }else
                        showToastincorr();
                        tv[0].setVisibility(View.GONE);
                        tv[1].setVisibility(View.VISIBLE);
                        break;

                    case R.id.textView2:
                        if(strText.equals(str2)){
                            //바 숫자갱신 해야함
                            showToastcorr();
                        }else
                            showToastincorr();
                        tv[1].setVisibility(View.GONE);
                        tv[2].setVisibility(View.VISIBLE);

                        break;

                    case R.id.textView3:
                        if(strText.equals(str3)){
                            //바 숫자갱신 해야함
                            showToastcorr();
                        }else
                            showToastincorr();
                        tv[2].setVisibility(View.GONE);
                        result.setVisibility(View.VISIBLE);
                        break;

                }
            }
        }

       return false;
    }
    //커스텀 토스트 함수
    public void showToastcorr(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,(ViewGroup)findViewById(R.id.smile) );

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
    }
    public void showToastincorr(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout2,(ViewGroup)findViewById(R.id.unsmile) );

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
    }
}