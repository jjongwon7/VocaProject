package com.example.vocaproject;

// 사용자 계정 정보

import androidx.appcompat.app.AppCompatActivity;

public class UserAccount extends AppCompatActivity {
    private String emailId;     // 아이디
    private String password;    // 비밀번호
    private String idToken;     // 고유 토큰정보
    private int[] history;
    private boolean[] bookMark;
    public UserAccount() { } // firebase에선 빈 생성자가 없다면 오류가 발생한다고 한다.


    //유저 정보를 입력하거나 받아올 수 있게

    public int[] getHistory() {
        return history;
    }

    public void setHistory(int[] history) {
        this.history = history;
    }

    public boolean[] getBookMark() {
        return bookMark;
    }

    public void setBookMark(boolean[] bookMark) {
        this.bookMark = bookMark;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
