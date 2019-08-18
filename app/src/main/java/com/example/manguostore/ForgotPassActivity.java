package com.example.manguostore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {

    RelativeLayout rellay1, rellay2;
    private FirebaseAuth auth;
    private EditText email;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1_fogortpass);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2_fogortpass);

        email = (EditText) findViewById(R.id.email_forgotpass);

        auth = FirebaseAuth.getInstance();

        handler.postDelayed(runnable, 2000);
    }

    public void changePassword(View view) {
        String inputEmail = email.getText().toString().trim();
        if (TextUtils.isEmpty(inputEmail)) {
            Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.sendPasswordResetEmail(inputEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPassActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPassActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ForgotPassActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void backToLogin(View view) {
        Intent intent = new Intent(ForgotPassActivity.this, MainActivity.class);
        startActivity(intent);
    }
}