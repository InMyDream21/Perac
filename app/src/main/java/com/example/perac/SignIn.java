package com.example.perac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.perac.activities.HomepageActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.perac.activities.MenuListActivity;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    EditText edtEmail, edtPassword;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    FrameLayout frameLayout;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent goMain = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(goMain);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        edtEmail = findViewById(R.id.signin_email);
        edtPassword = findViewById(R.id.signin_pw);
        progressBar = findViewById(R.id.progressBar);
        frameLayout = findViewById(R.id.fl_sign_up);
        Button toMain = findViewById(R.id.btn_sign_in);

        toMain.setOnClickListener(this);
        frameLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_sign_in){
            progressBar.setVisibility(View.VISIBLE);
            String email, password, name;
            email = String.valueOf(edtEmail.getText());
            password = String.valueOf(edtPassword.getText());

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(SignIn.this, "Email cannot be empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(SignIn.this, "Password cannot be empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent goMain = new Intent(SignIn.this, HomepageActivity.class);
                                startActivity(goMain);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignIn.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else if (v.getId() == R.id.fl_sign_up) {
            Intent intent = new Intent(SignIn.this, SignUp.class);
            startActivity(intent);
        }
    }
}