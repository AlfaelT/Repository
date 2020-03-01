package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class logInActivity extends AppCompatActivity {
EditText userName;
EditText password;
Button btnLog;
FirebaseAuth mAuth;
ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth=FirebaseAuth.getInstance();
        userName=findViewById(R.id.userNameLog);
        password=findViewById(R.id.pssLog);
        progress=findViewById(R.id.progressBar2);
        btnLog=findViewById(R.id.logg);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uu=userName.getText().toString();
                String pp=password.getText().toString();
                if(uu.isEmpty() || pp.isEmpty()){
                    userName.setError("there is an empty field or ..");
                    password.setError("password can't be empty");

                }

                else {
                    registered();
                }

            }
        });
    }

    private void registered() {
        String userNam = userName.getText().toString();
        String word=password.getText().toString();
        progress.setVisibility(View.VISIBLE);
        Toast.makeText(logInActivity.this, "please wait a minute ......",Toast.LENGTH_LONG).show();
        mAuth.signInWithEmailAndPassword(userNam,word).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progress.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(logInActivity.this, Display.class));

                } else {
                    // If sign in fails, display a message to the user.
                    progress.setVisibility(View.INVISIBLE);
                    Toast.makeText(logInActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
