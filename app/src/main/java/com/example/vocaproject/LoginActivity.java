package com.example.vocaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
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

public class LoginActivity extends BookmarkManager {

    private FirebaseAuth mFirebaseAuth;              // 파이어베이스 인증처리
    private DatabaseReference mDatabaseReference;    // 실시간 데이터베이스
    private EditText mEtEmail, mEtPassword;          // 회원가입 입력필드



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance(); // mFirebaseAuth 초기화
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("VocaProject"); // mDatabaseReference 초기화

        mEtEmail = findViewById(R.id.et_email);
        mEtPassword = findViewById(R.id.et_password);

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 처리
                String strEmail = mEtEmail.getText().toString(); // mEtEmail에 입력된 데이터를 가져온다.
                String strPassword = mEtPassword.getText().toString(); // mEtPassword에 입력된 데이터를 가져온다.

                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            currentID = firebaseUser.getUid();
//                            Intent intentID = new Intent();
//                            ComponentName componentName = new ComponentName(
//                                    "package com.example.vocaproject",
//                                    "package com.example.vocaproject.BookmarkManager"
//                            );
//                            intentID.setComponent(componentName);
//                            intentID.putExtra("ID",currentID);
//                            startActivity(intentID);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish(); // 현재 액티비티 파괴
                        }
                        else {
                            // 로그인 실패
                            Toast.makeText(LoginActivity.this, "잘못된 정보입니다. 다시 로그인 하세요.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        Button btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}