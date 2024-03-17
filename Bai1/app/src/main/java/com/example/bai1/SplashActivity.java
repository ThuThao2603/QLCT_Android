package com.example.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Form đầu khi vào app muốn hiện cho người dùng khoảng 1.5s đến 2s trước khi đăng nhập

        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        },500);// thời gian muốn delay (đơn vị mili giây để số giây nhân với 1000)
    }
    private void nextActivity() {
        //check user login chưa
       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       // check user để bt ng dùng login hay chưa
        if(user==null){
            //chưa login
            //chuyển vào màn hình đăng nhập
            Intent intent= new Intent(this,DangNhapActivity.class);
            startActivity(intent);
        }else{
            //đã login
            //chuyển vào màn hình TrangChuActivity
            Intent intent= new Intent(this, TrangChuActivity.class);
            startActivity(intent);
        }
        finish();
    }
}