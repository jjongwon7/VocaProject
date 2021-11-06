package com.example.vocaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;              // 파이어베이스 인증처리
    private DatabaseReference mDatabaseReference;    // 실시간 데이터베이스
    private EditText mEtEmail, mEtPassword;          // 회원가입 입력필드
    private Button mBtnRegister;                     // 회원가입 버튼



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance(); // mFirebaseAuth 초기화
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("VocaProject"); // mDatabaseReference 초기화

        mEtEmail = findViewById(R.id.et_email);
        mEtPassword = findViewById(R.id.et_password);
        mBtnRegister = findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(new View.OnClickListener()
        {       // 버튼을 눌렀을 경우
            @Override
            public void onClick(View view)
            {
                // 회원가입 처리
                String strEmail = mEtEmail.getText().toString(); // mEtEmail에 입력된 데이터를 가져온다.
                String strPassword = mEtPassword.getText().toString(); // mEtPassword에 입력된 데이터를 가져온다.

                // Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override                                                                                   // 현재 activity
                    public void onComplete(@NonNull Task<AuthResult> task) { // task = 회원가입 처리 결과를 참조
                        if(task.isSuccessful()){ // 처리가 정상적으로 진행이 되었다면
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser(); //firebaseUser가 현재 처리된 계정정보 받음
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid()); // Uid = 로그인 시 생성되는 고유값
                            account.setEmailId(firebaseUser.getEmail());
                            account.setPassword(strPassword);


                            mDatabaseReference.child("UserAccount").child(firebaseUser.getUid()).setValue(account); // DB에 데이터 저장
                            Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }); // 입력받은 정보를 통해 계정 생성


            }
        });


    }
}