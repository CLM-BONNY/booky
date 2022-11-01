package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

public class PopUp extends AppCompatActivity {
    TextView u_id, u_pw, u_name, u_phone, u_address;
    String id, pw, name, phone, address;
    Button u_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        // 유저 아이디 텍스트
        u_id = findViewById(R.id.u_id);
        // 유저 비밀번호 텍스트
        u_pw = findViewById(R.id.u_pw);
        // 유저 이름 텍스트
        u_name = findViewById(R.id.u_name);
        // 유저 전화번호 텍스트
        u_phone = findViewById(R.id.u_phone);
        // 유저 주소 텍스트
        u_address = findViewById(R.id.u_address);
        // 확인 버튼
        u_ok = findViewById(R.id.check_btn);

        // 텍스트뷰에 읽어온 유저정보 인텐트 할당
        Intent intent = getIntent();
        id = intent.getExtras().getString("ID");
        u_id.setText(u_id.getText().toString() + id);
        pw = intent.getExtras().getString("PW");
        u_pw.setText(u_pw.getText().toString() + pw);
        name = intent.getExtras().getString("NAME");
        u_name.setText(u_name.getText().toString() + name);
        phone = intent.getExtras().getString("PHONE");
        u_phone.setText(u_phone.getText().toString() + phone);
        address = intent.getExtras().getString("ADDRESS");
        u_address.setText(u_address.getText().toString() + address);

        // 확인 버튼 누르면 유저 정보 페이지 닫기
        u_ok.setOnClickListener(view -> {
            Intent intentset = new Intent();
            intent.putExtra("result", "유저 정보 확인 창닫기");
            setResult(RESULT_OK, intentset);
            finish();
        });
    }

    // 바깥 레이어 클릭시 안닫히게 하는 함수
    @Override public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    //안드로이드 백버튼 막기
    @Override public void onBackPressed() {
        return;
    }
}
