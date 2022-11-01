package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.widget.RadioButton;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class SignUp extends AppCompatActivity {
    EditText idt, pwt, name, phone, address;
    TextView personal, terms;
    RadioButton rbtn1, rbtn2;
    Button signok, back, idcb, pwcb;
    Boolean isIdCheck = false;
    Boolean isPwCheck = false;
    String showStr = "";
    int control = 0;

    // EditText 문자열 내용 리턴 함수
    private String chnStr(EditText et){
        return (et.getText().toString());
    }

    // 특정 문자열이 포함되어 있는지 확인하는 함수
    private boolean hasTxt(EditText et){
        return (et.getText().toString().trim().length() > 0);
    }

    // 아이디 중복확인 함수
    private boolean chkID(EditText et){
        String nId = chnStr(et) + ".txt";
        try {
            openFileInput(nId);
        } catch(Exception e){
            e.printStackTrace();
            return true;
        }
        return false;
    }

    // 비밀번호 양식확인 함수(숫자와 영문자 포함 6글자 이상)
    private boolean chkPW(EditText et){
        String str = et.getText().toString();
        char[] chr = str.toCharArray();
        int len = str.trim().length();
        int num = 0;
        int eng = 0;

        if(len >= 6){
            for(int i=0; i<len; i++){
                if (chr[i] >= 48 && chr[i] <= 57) num++;
                else if((chr[i]>=97 && chr[i]<=122)||(chr[i]>=65&&chr[i]<=90)) eng++;
            }
            if(num>0 && eng>0) return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // 아이디 텍스트
        idt = findViewById(R.id.signup_ID);
        // 아이디 중복 확인 버튼
        idcb = findViewById(R.id.ID_check_btn);
        // 비밀번호 텍스트
        pwt = findViewById(R.id.signup_PW);
        // 비밀번호 확인 버튼
        pwcb = findViewById(R.id.PW_check_btn);
        // 이름 텍스트
        name = findViewById(R.id.signup_name);
        // 전화번호 텍스트
        phone = findViewById(R.id.signup_phone);
        // 주소 텍스트
        address = findViewById(R.id.signup_address);
        // 개인정보이용동의 텍스트뷰
        personal = findViewById(R.id.signup_terms);
        // 개인정보이용약관
        terms = findViewById(R.id.terms_text);
        // 개인정보이용약관 동의 라디오버튼
        rbtn1 = findViewById(R.id.accept);
        rbtn2 = findViewById(R.id.decline);

        // 회원가입 버튼
        signok = findViewById(R.id.signup_check_btn);
        // 뒤로가기 버튼
        back = findViewById(R.id.signup_back_btn);

        // 개인정보이용동의 텍스트뷰 클릭시, 개인정보이용약관 활성화
        // control이 홀수일 경우 개인정보이용약관을 보여주고, control이 짝수일 경우 숨기는 함수
        personal.setOnClickListener(view -> {
            control += 1;
            if (view==personal && control%2==1) {
                terms.setVisibility(View.VISIBLE);
            } else {
                terms.setVisibility(View.INVISIBLE);
            }
        });

        // 개인정보이용약관 스크롤바 작동
        terms.setMovementMethod(new ScrollingMovementMethod());

        // 뒤로가기 버튼 클릭시, 회원가입 페이지로 이동
        back.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), Login.class);
            startActivity(intent);
            finish();
        });

        // 아이디 중복 확인 버튼 클릭시, 기존에 있던 아이디인지 확인
        idcb.setOnClickListener(view -> {
            if (!chkID(idt)) {
                showStr = "이미 사용중인 아이디입니다.";
                isIdCheck = false;
            } else {
                showStr = "사용 가능한 아이디입니다.";
                isIdCheck = true;
            }
            Toast.makeText(getApplicationContext(), showStr, Toast.LENGTH_SHORT).show();
        });


        // 비밀번호 확인 버튼 클릭시, 비밀번호 조건에 맞는지 확인
        pwcb.setOnClickListener(view -> {
            if (!chkPW(pwt)) {
                showStr = "비밀번호가 양식에 맞지 않습니다.";
                isPwCheck = false;
            } else {
                showStr = "사용 가능한 비밀번호입니다.";
                isPwCheck = true;
            }
            Toast.makeText(getApplicationContext(), showStr, Toast.LENGTH_SHORT).show();
        });


        // 회원가입 버튼 클릭시, 회원정보 저장하고 로그인 페이지로 이동
        signok.setOnClickListener(view -> {

            finish();
        });

        signok.setOnClickListener(view -> {
            // 아이디 중복 확인 및 비밀번호 확인 후, 회원가입 처리 시작
            if(isIdCheck && isPwCheck) {
                // 이름, 전화번호, 주소값 중에 하나라도 빈 문자열이면
                if (!(hasTxt(name) && hasTxt(phone) && hasTxt(address))) {
                    showStr = "모든 항목을 채워주세요.";
                    Toast.makeText(getApplicationContext(), showStr, Toast.LENGTH_SHORT).show();
                }
                // 아니면
                else {
                    // accept 라디오 버튼이 체크되어있지 않다면
                    if (!rbtn1.isChecked()) {
                        showStr = "개인정보 이용약관 동의가 필요합니다";
                        Toast.makeText(getApplicationContext(), showStr, Toast.LENGTH_SHORT).show();
                    }
                    //회원가입 성공
                    else {
                        showStr = "회원가입이 완료되었습니다";
                        try {
                            // file에 회원정보 저장
                            FileOutputStream fos = null;
                            String fn = chnStr(idt) + ".txt";
                            fos = openFileOutput(fn, Context.MODE_PRIVATE);
                            PrintWriter writer = new PrintWriter(fos);
                            writer.println(chnStr(idt));
                            writer.println(chnStr(pwt));
                            writer.println(chnStr(name));
                            writer.println(chnStr(phone));
                            writer.println(chnStr(address));
                            writer.close();
                            Toast.makeText(getApplicationContext(), showStr, Toast.LENGTH_SHORT).show();
                            // 회원정보 저장 후, 로그인 페이지로 이동
                            Intent intent = new Intent(getApplication(), Login.class);
                            startActivity(intent);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                }
            }
            // 아이디 중복확인, 비밀번호 양식확인을 진행하지 않았을 경우
            else {
                showStr = "아이디, 비밀번호 확인을 진행해주세요";
                Toast.makeText(getApplicationContext(), showStr, Toast.LENGTH_SHORT).show();
            }
        });
    }
}