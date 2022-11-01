package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Login extends AppCompatActivity {
    Button login, sign, goods;
    EditText idt, pwt;
    String loginStr = "";
    Boolean canLogin;
    public String[] userinfo;
    String rPW;
    Boolean chkID;

    // 특정 문자열이 포함되어 있는지 확인하는 함수
    private boolean hasTxt(EditText et) {
        return (et.getText().toString().trim().length() > 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //로그인 버튼
        login = findViewById(R.id.Login_btn);
        //회원가입 버튼
        sign = findViewById(R.id.Signup_btn);
        //상품 보러가기 버튼
        goods = findViewById(R.id.Goods_btn);

        // 아이디 텍스트
        idt = findViewById(R.id.ID);
        // 비밀번호 텍스트
        pwt = findViewById(R.id.PW);

        canLogin = true;
        userinfo = new String[5];
        rPW = "";

        login.setOnClickListener(view -> {
            // 아이디, 비밀번호값 중에 하나라도 빈 문자열이라면
            if (!(hasTxt(idt) && hasTxt(pwt))) {
                loginStr = "로그인 정보를 입력해주세요";
                canLogin = false;
            }
            else {
                // 입력한 아이디와 번호의 문자열 추출
                String nId = idt.getText().toString() + ".txt";
                String nPW = pwt.getText().toString();

                try {
                    // 입력한 아이디와 일치하는 파일을 열고 파일 내용 한 줄씩 읽어오기
                    FileInputStream fis = openFileInput(nId);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                    for (int i=0; i < 5; i++) {
                        userinfo[i] = reader.readLine();
                    }
                    rPW = userinfo[1];
                    chkID = true;
                } catch(Exception e) {
                    // 입력한 아이디와 일치하는 파일이 없을 경우
                    e.printStackTrace();
                    chkID = false;
                }

                // 입력한 아이디와 일치하는 파일이 존재할 경우, 파일 내용에서 읽어온 비밀번호와 입력한 비밀번호가 일치하는지 확인
                if(chkID) {
                    if(!nPW.equals(rPW)) {
                        loginStr = "비밀번호가 맞지 않습니다";
                        canLogin = false;
                    }
                }
                // 입력한 아이디와 일치하는 파일이 없을 경우
                else {
                    loginStr = "등록되지 않은 아이디입니다";
                    canLogin = false;
                }
            }

            // 로그인 할 수 없는 경우, 메시지 출력
            if(!canLogin) {
                Toast.makeText(getApplicationContext(), loginStr, Toast.LENGTH_SHORT).show();
            }
            else {
                // 로그인 성공한 경우, 유저 정보를 인텐트로 전달하면서 상품 페이지로 이동
                Intent intentThird = new Intent(getApplicationContext(), Goods.class);
                intentThird.putExtra("ID",userinfo[0]);
                intentThird.putExtra("PW",userinfo[1]);
                intentThird.putExtra("NAME",userinfo[2]);
                intentThird.putExtra("PHONE",userinfo[3]);
                intentThird.putExtra("ADDRESS",userinfo[4]);
                intentThird.putExtra("LOGINSTATE", true);
                startActivity(intentThird);
                finish();
            }
        });

        //회원가입 버튼 클릭시, 회원가입 페이지로 이동
        sign.setOnClickListener(view -> {
            Intent intentSign = new Intent(getApplication(), SignUp.class);
            startActivity(intentSign);
            finish();
        });

        //상품 보러가기 버튼 클릭시, 상품 페이지로 이동
        goods.setOnClickListener(view -> {
            Intent intentGoods = new Intent(getApplication(), Goods.class);
            intentGoods.putExtra("LOGINSTATE", false);
            startActivity(intentGoods);
            finish();
        });
    }
}