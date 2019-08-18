package com.example.manguostore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rellay1, rellay2;
    private EditText email, password;
    private FirebaseAuth auth;
    public String  user_status;
    public String acc_user = "user";
    public String acc_admin = "admin";
    public String acc_owner = "owner";
    public String id;
    DatabaseReference Users;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    DocumentSnapshot snapshot;
    LazyLoader lazyLoader;


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
        setContentView(R.layout.activity_main);
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 2000);

        email = (EditText) findViewById(R.id.email_login);
        password = (EditText) findViewById(R.id.password_login);

        //For the lazy loader
        lazyLoader = (LazyLoader) findViewById(R.id.myLoaderProgress);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            //redirect();
        }

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
    }

    public void toSignup(View view) {
        Intent intent = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    public void login(View view) {

        String inputEmail = email.getText().toString();
        final String inputPassword = password.getText().toString();

        if (TextUtils.isEmpty(inputEmail)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(inputPassword)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        //authenticate user
        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (inputPassword.length() < 6) {
                                password.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(MainActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            redirect();
                        }
                    }
                });

    }
    public void redirect(){
        lazyLoader.setVisibility(lazyLoader.VISIBLE);
        LazyLoader loader = new LazyLoader(this, 30, 20, ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected));
        loader.setAnimDuration(500);
        loader.setFirstDelayDuration(100);
        loader.setSecondDelayDuration(200);
        loader.setInterpolator(new LinearInterpolator());

        lazyLoader.addView(loader);
        id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db.collection("users").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    user_status = documentSnapshot.getString("status");
                    //Log.i( "Testing",user_status);
                    if(user_status.equals("user")){
                        Intent intent = new Intent(MainActivity.this,LandingUserActivity.class);
                        startActivity(intent);
                        finish();
                    }else if(user_status.equals("owner")){
                        Intent intent = new Intent(MainActivity.this,LandingOwnerActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }else {
                    Toast.makeText(MainActivity.this, "TAsk not even successful", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void toForgotpass(View view) {
        Intent intent = new Intent(MainActivity.this,ForgotPassActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
    }
}
