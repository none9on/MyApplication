package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registerPage extends AppCompatActivity {
//    эдит текст для записи, кнопка, текст вью это для уже зареганы
    EditText mName, mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBarRegister;
    FirebaseFirestore fstore;
    String userID;
    FirebaseUser fuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
       mName = findViewById(R.id.register_name);
       mEmail = findViewById(R.id.register_email);
       mPassword = findViewById(R.id.register_password);
       mRegisterBtn = findViewById(R.id.register_button);
       mLoginBtn = findViewById(R.id.textViewLogin);
       mPhone = findViewById(R.id.register_phone);
//getting the current instance of data base
       fAuth = FirebaseAuth.getInstance();
       progressBarRegister = findViewById(R.id.progressBarRegister);
       fstore = FirebaseFirestore.getInstance();


//       is user logged in or not need to check this first
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

//       after the user clicked on register button нужно проверить, что все данные введены правильно
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                String name = mName.getText().toString();
                String phone = mPhone.getText().toString();

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
//                register the user
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

//                        verify email
                        fuser = fAuth.getCurrentUser();
                        fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(registerPage.this, "verification letter has been sent", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("TAG", "On failure: email not sent" + e.getMessage());
                            }
                        });



                        if(task.isSuccessful()){
                            Toast.makeText(registerPage.this, "user created!", Toast.LENGTH_SHORT).show();

                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(userID);
//                            понятия не имею что делает эта карта нужно почитать
                            Map<String, Object> user = new HashMap<>();
                            user.put("name", name);
                            user.put("email", email);
                            user.put("phone", phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("TAG", "profile is created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "On Failure" + e.toString());
                                }
                            });
//                            переходим после регистрации успешной в мейн активити
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        }
                        else
                        {
                            Toast.makeText(registerPage.this, "error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBarRegister.setVisibility(View.GONE);

                        }
                    }
                });
            }
        });
    mLoginBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), loginPage.class));
            finish();
        }
    });
    }
}