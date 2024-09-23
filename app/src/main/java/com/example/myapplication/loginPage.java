package com.example.myapplication;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginPage extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mloginBtn;
    TextView mRegisterBtn, forgotPass;
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
        forgotPass = findViewById(R.id.forgot_the_password);

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
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetEmail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("reset password?");
                passwordResetDialog.setMessage("enter your email to receive reset link");
                passwordResetDialog.setView(resetEmail);
                passwordResetDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        extract the email nad send reset link
                        String mail = resetEmail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(loginPage.this, "resent link sent to your email!", Toast.LENGTH_LONG).show();

                            }
                        }) .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(loginPage.this, "error!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        close the dialog
                    }
                });
                passwordResetDialog.create().show();
            }
        });
    }
}