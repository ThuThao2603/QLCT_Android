package com.example.bai1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DangKyActivity extends AppCompatActivity {

    // khai báo
    private TextView textDangNhap;
    private EditText editEmail,editTenDangNhap,editMatKhau;
    private Button buttonDangKy;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        //gọi hàm
        unitUi();
        unitListener();
    }

    private void unitListener() {
        // chuyển trang đăng nhập
        textDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DangKyActivity.this,DangNhapActivity.class);
                startActivity(intent);
            }
        });
        // đăng ký tài khoản
        buttonDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClickDangKy();
            }
        });
    }

    private void setOnClickDangKy() {
        //thực hiện logic đăng ký tài khoản (tham khảo trên trang firebase)

        String strEmail=editEmail.getText().toString().trim();
        String strTenDangNhap= editTenDangNhap.getText().toString().trim();
        String strMatKhau= editMatKhau.getText().toString().trim();

        FirebaseAuth auth= FirebaseAuth.getInstance();

        if(TextUtils.isEmpty(strEmail) && TextUtils.isEmpty(strMatKhau) && TextUtils.isEmpty(strTenDangNhap)) {

            Toast.makeText(DangKyActivity.this,"Nhập đầy đủ thông tin!",Toast.LENGTH_SHORT).show();

        }else {
            // xử lý tạo user mất thời gian nên sẽ thêm 1 cái proressDialog để ng dùng bt
            // load để ng dùng bt tkhoan đang đc thêm vào đki(thêm vào db)
            progressDialog.show();
            auth.createUserWithEmailAndPassword(strEmail, strMatKhau)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        // check đăng ký thành công hay thất bại
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //tắt load tbao cho ng dùng khi dki thành công
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                // Đăng nhập thành công, cập nhật giao diện người dùng với thông tin người dùng đã đăng nhập
                                Intent intent = new Intent(DangKyActivity.this, TrangChuActivity.class);
                                startActivity(intent);
                                //đóng tất cả các trang trước TrangChuActivity lại
                                finishAffinity();
                            } else {
                                // Nếu đăng nhập không thành công, hiển thị thông báo cho người dùng.
                                Toast.makeText(DangKyActivity.this, "Kiểm tra lại thông tin đăng ký.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    //khai báo ánh xạ
    private void unitUi(){
        // ánh xạ
        textDangNhap= findViewById(R.id.tvDangNhap);
        editEmail=findViewById(R.id.edEmail);
        editTenDangNhap=findViewById(R.id.edTenDangNhap);
        editMatKhau=findViewById(R.id.edMatKhau);
        buttonDangKy=findViewById(R.id.btDangKy);
        progressDialog=new ProgressDialog(this);
    }

}
