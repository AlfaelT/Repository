package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class afterCardSelected extends AppCompatActivity {
    TextView f,l,g,m,e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_card_selected);
        f=findViewById(R.id.fnC);
        l=findViewById(R.id.lnC);
        g=findViewById(R.id.gC);
        m=findViewById(R.id.mC);
        e=findViewById(R.id.eC);
        Intent intent=getIntent();
        String fn= intent.getStringExtra("first name");
        String ln= intent.getStringExtra("last name");
        String gn= intent.getStringExtra("gender");
        String mo= intent.getStringExtra("mobile");
        String em= intent.getStringExtra("email");
        f.setText(fn);
        l.setText(ln);
        g.setText(gn);
        m.setText(mo);
        e.setText(em);

    }
}
