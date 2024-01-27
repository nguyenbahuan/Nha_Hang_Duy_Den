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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sign_up extends AppCompatActivity {


    private Button btnlogin,btnsign_up;
    private EditText email, passwordLogin,phone,full_name;
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
        phone = findViewById(R.id.phone);
        full_name = findViewById(R.id.fullname);


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
                addData();
            }
        });
    }
    private void sign_up(){

        String username = email.getText().toString();
        String password = passwordLogin.getText().toString();


        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"Vui lòng nhập email!!!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Vui lòng nhập password!!!", Toast.LENGTH_SHORT).show();
        }
        else{
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

    private void addData(){
        String fullname = full_name.getText().toString();
        String username = email.getText().toString();
        String password = passwordLogin.getText().toString();
        String phone_number = phone.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users");

        HelperClass helperClass = new HelperClass(fullname,username,password,phone_number);
        reference.child(username.replace(".", "_")).setValue(helperClass);
    }

}
