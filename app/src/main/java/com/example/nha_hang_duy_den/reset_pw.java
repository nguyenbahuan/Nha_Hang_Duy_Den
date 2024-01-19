package com.example.nha_hang_duy_den;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class reset_pw extends AppCompatActivity {
    private Button confirm;
    private EditText email;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_pw);

        confirm = findViewById(R.id.confirm);
        email = findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_reset = email.getText().toString();
                mAuth.sendPasswordResetEmail(email_reset).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(getApplicationContext(), "Vui lòng kiểm tra email của bạn", Toast.LENGTH_SHORT).show();
                        else{
                            Toast.makeText(getApplicationContext(), "Email bạn nhập không tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
