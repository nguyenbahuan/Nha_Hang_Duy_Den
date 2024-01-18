package com.example.nha_hang_duy_den;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up extends AppCompatActivity {


    private Button btnlogin,btnsign_up;
    private EditText email, passwordLogin,re_password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mAuth = FirebaseAuth.getInstance();
        btnsign_up = findViewById(R.id.btnsign_up);
        btnlogin = findViewById(R.id.login);
        email = findViewById(R.id.email);
        passwordLogin = findViewById(R.id.password);
        re_password = findViewById(R.id.re_password);

        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View View){
                Intent intent = new Intent(sign_up.this,login.class);
                startActivity(intent);
            }
        });
        btnsign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_up();
            }
        });
    }
    private void sign_up(){
        String username = email.getText().toString();
        String password = passwordLogin.getText().toString();
        String Re_password = re_password.getText().toString();
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"Vui lòng nhập email!!!", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Vui lòng nhập password!!!", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(Re_password)){
            Toast.makeText(this,"Vui lòng nhập password!!!", Toast.LENGTH_SHORT).show();
        }
        if(!password.equals(Re_password)){
            Toast.makeText(this,"Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
        }
        mAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Đăng kí  thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(sign_up.this,login.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Đăng kí không thành công ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
