package com.example.bai1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TrangChuActivity extends AppCompatActivity {

    private TextView tvTenDangNhap;
    private ImageView ivDangXuat,ivThongKe,ivHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        // gọi hàm
        unitUi();
        unitUilistener();
    }

    private void unitUi(){
        tvTenDangNhap =findViewById(R.id.tvTenDangNhap);
        ivDangXuat= findViewById(R.id.ivDangXuat);
        ivHome=findViewById(R.id.ivHome);
        ivThongKe=findViewById(R.id.ivThongKe);
    }
    private void unitUilistener(){
        ivDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(TrangChuActivity.this, DangNhapActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TrangChuActivity.this,TrangChuActivity.class);
                startActivity(intent);
            }
        });
    }
}