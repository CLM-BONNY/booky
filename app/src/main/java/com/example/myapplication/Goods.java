package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Goods extends AppCompatActivity {
    Button u_info_btn;
    String id, pw, name, phone, address;
    Boolean loginstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        // 유저정보확인 버튼
        u_info_btn = findViewById(R.id.user_info_btn);

        // 로그인 상태 인텐트 받기
        Intent intent = getIntent();
        loginstate = intent.getExtras().getBoolean("LOGINSTATE");

        // 유저정보확인 버튼 클릭시, 유저정보 팝업창 활성화
        u_info_btn.setOnClickListener(view -> {
            // 로그인 했을 경우
            if (loginstate) {
                // 유저정보 인텐트 받기
                id = intent.getExtras().getString("ID");
                pw = intent.getExtras().getString("PW");
                name = intent.getExtras().getString("NAME");
                phone = intent.getExtras().getString("PHONE");
                address = intent.getExtras().getString("ADDRESS");

                // 유저정보를 인텐트로 전달하면서 유저정보 페이지로 이동
                Intent intentPop = new Intent(getApplicationContext(), PopUp.class);
                intentPop.putExtra("ID", id);
                intentPop.putExtra("PW", pw);
                intentPop.putExtra("NAME", name);
                intentPop.putExtra("PHONE", phone);
                intentPop.putExtra("ADDRESS", address);
                startActivity(intentPop);
            }
            // 로그인 하지 않았을 경우
            else {
                // 회원가입 경고창 띄우고, 확인 버튼 누르면 회원가입 페이지로 이동
                // 아니요 버튼 누르면 경고창 닫기
                AlertDialog.Builder signUPCheck = new AlertDialog.Builder(Goods.this);
                signUPCheck .setTitle("회원가입 확인")
                            .setMessage("회원 가입 하시겠습니까?")
                            .setPositiveButton("확인", (dialogInterface, i) -> {
                                Intent intentSecond = new Intent(getApplication(), SignUp.class);
                                startActivity(intentSecond);
                                finish();
                            })
                        .setNegativeButton("아니요", (dialogInterface, i) -> {
                        });

                AlertDialog msgDlg = signUPCheck.create();
                msgDlg.show();
            }
        });
    }
}
