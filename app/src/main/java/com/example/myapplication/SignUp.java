package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SignUp extends AppCompatActivity implements View.OnClickListener {
    TextView personal, terms, idt, pwt;
    Button signok, back, idcb, pwcb;
    int control = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // 개인정보이용동의 텍스트뷰
        personal = (TextView) findViewById(R.id.signup_terms);
        // 개인정보이용약관
        terms = (TextView) findViewById(R.id.terms_text);
        // 개인정보이용동의 텍스트뷰 클릭시, 개인정보이용약관 활성화
        personal.setOnClickListener((View.OnClickListener) this);
        // 개인정보이용약관 스크롤바 작동
        terms.setMovementMethod(new ScrollingMovementMethod());

        // 아이디 텍스트뷰
        idt = (TextView) findViewById(R.id.signup_ID);
        // 아이디 중복 확인 버튼
        idcb = (Button) findViewById(R.id.ID_check_btn);

        // 비밀번호 텍스트뷰
        pwt = (TextView) findViewById(R.id.signup_PW);
        // 비밀번호 확인 버튼
        pwcb = (Button) findViewById(R.id.PW_check_btn);
        // 글자수(8글자), 특수문자(!, @, #)포함여부 확인

        // 회원가입 버튼
        signok = (Button) findViewById(R.id.signup_check_btn);
        // 회원가입 버튼 클릭시, 회원정보 저장하고 로그인 페이지로 이동
        signok.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), Login.class);
            startActivity(intent);
            finish();
        });

        // 뒤로가기 버튼
        back = (Button) findViewById(R.id.signup_back_btn);
        // 뒤로가기 버튼 클릭시, 회원가입 페이지로 이동
        back.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), Login.class);
            startActivity(intent);
            finish();
        });
    }

    public void onClick(View v) {
        control += 1;
        if (v==personal && control%2==1) {
            terms.setVisibility(View.VISIBLE);
        } else {
            terms.setVisibility(View.INVISIBLE);
        }
    }
}