package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;

public class Login extends AppCompatActivity {
    Button login,sign, goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //로그인 버튼
        login = (Button) findViewById(R.id.Login_btn);
        //로그인 버튼 클릭시, 아이디 비밀번호 일치하는 회원 있는지 확인하고 없으면 에러 메시지 출력

        //회원가입 버튼
        sign = (Button) findViewById(R.id.Signup_btn);
        //회원가입 버튼 클릭시, 회원가입 페이지로 이동
        sign.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), SignUp.class);
            startActivity(intent);
            finish();
        });

        //상품 보러가기 버튼
        goods = (Button) findViewById(R.id.Goods_btn);
        //상품 보러가기 버튼 클릭시, 상품 페이지로 이동
        goods.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), Goods.class);
            startActivity(intent);
            finish();
        });
    }
}