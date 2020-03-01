package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Display extends AppCompatActivity {
 Toolbar toolbar;
 RecyclerView recyclerView;
 FirebaseAuth mAuth;
 DatabaseReference mDatabase;
 ArrayList<construct>list;
 RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useers);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         list=new ArrayList<>();
         mAuth=FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance().getReference().child("data");
        mDatabase.keepSynced(true);
        adapter=new RecyclerAdapter(list);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
     mDatabase.addChildEventListener(new ChildEventListener() {
         @Override
         public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                 construct c = dataSnapshot.getValue(construct.class);
                 list.add(c);



         }

         @Override
         public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

         }

         @Override
         public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

         }

         @Override
         public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.getOut:
                AlertDialog.Builder builder = new AlertDialog.Builder(Display.this);
                builder.setTitle(R.string.app_name);
                builder.setMessage("Do you want to log out?")
                        .setCancelable(true)

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.setPositiveButton("log out", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                   mAuth.signOut();
                        finish();
                        startActivity(new Intent(Display.this,logInActivity.class));
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();



                break;
            case R.id.about:
                Toast.makeText(this, "you know Alpha made this why bother", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
