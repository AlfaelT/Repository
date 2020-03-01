package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class form extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference dRefrence;
    Button btn;
    EditText mob;
    EditText password;
    EditText fName;
    EditText lName;
    EditText gender;
    EditText email;
    EditText userName;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form);
        mAuth=FirebaseAuth.getInstance();
        dRefrence= FirebaseDatabase.getInstance().getReference().child("data");
        btn=findViewById(R.id.signUpButton);
        mob=findViewById(R.id.mobileEditText);
        mob.setInputType(InputType.TYPE_CLASS_PHONE);
        password=findViewById(R.id.passwordEditText);
        fName=findViewById(R.id.frsNameEditText);
        lName=findViewById(R.id.lstNameEditText);
        gender=findViewById(R.id.genderEditText);
        email=findViewById(R.id.emailEditText);
      userName=findViewById(R.id.userNameEditText);
      progressBar=findViewById(R.id.progressBar);

      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                  LogIn();
          }
      });

    }

    private void LogIn() {
        String userNam=userName.getText().toString();
        String pass=password.getText().toString();
        final String fn=fName.getText().toString();
        final String ln=lName.getText().toString();
        final String g=gender.getText().toString();
        final String e=email.getText().toString();
        final String m=mob.getText().toString();
        final String id=e.replace(".com","com");
        Toast.makeText(form.this, "please wait a minute ......",Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(userNam,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    progressBar.setVisibility(View.INVISIBLE);


                    construct c=new construct(fn,ln,g,e,m);
                    dRefrence.child(id).setValue(c);
//                    newPost.child("last name").setValue(ln);
//                    newPost.child("gender").setValue(g);
//                    newPost.child("email").setValue(e);
//                    newPost.child("mobile").setValue(m);
                    startActivity(new Intent(form.this, Display.class));
                }
                else {
                    // If sign in fails, display a message to the user.
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(form.this, "Authentication failed please try again,Make sure you are connected to wifi .",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
