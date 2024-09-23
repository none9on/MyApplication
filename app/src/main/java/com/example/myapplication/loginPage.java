package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginPage extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mloginBtn;
    TextView mRegisterBtn;
    ProgressBar progressBarRegister;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mEmail = findViewById(R.id.login_email);
        mPassword = findViewById(R.id.logon_password);
        progressBarRegister = findViewById(R.id.progressBarLogin);
        fAuth = FirebaseAuth.getInstance();
        mloginBtn = findViewById(R.id.button_login);
        mRegisterBtn = findViewById(R.id.textViewRegister);


        mloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("email is required!");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("password is required!");
                    return;
                }
                if(password.length() < 6){
                    mPassword.setError("password is too short!");
                    return;
                }
                progressBarRegister.setVisibility(View.VISIBLE);

                //authenticate user
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(loginPage.this, "logged in!", Toast.LENGTH_SHORT).show();
//                            переходим после регистрации успешной в мейн активити
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        }
                        else
                        {
                            Toast.makeText(loginPage.this, "error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBarRegister.setVisibility(View.GONE);

                        }
                        }

                });
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), registerPage.class));
                finish();

            }
        });
    }
}