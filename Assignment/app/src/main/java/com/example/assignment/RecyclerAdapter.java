package com.example.assignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MemberViewHolder>{
    Task<Void> mAuth;
    ArrayList<construct> list;
    public RecyclerAdapter(ArrayList<construct> list){
        this.list=list;
    }
    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.data, viewGroup, false);
        return new MemberViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final MemberViewHolder holder, int i) {
        holder.fn.setText(list.get(i).getFirstName());
        holder.ln.setText(list.get(i).getLastName());
        holder.g.setText(list.get(i).getGender());
        holder.m.setText(list.get(i).getMobile());
        holder.e.setText(list.get(i).getEmail());
        final int currentPosition=i;
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String f=list.get(currentPosition).getFirstName();
                String l=list.get(currentPosition).getLastName();
                String g=list.get(currentPosition).getGender();
                String m=list.get(currentPosition).getMobile();
                String e=list.get(currentPosition).getEmail();
                Intent intent=new Intent(v.getContext(),afterCardSelected.class);
                intent.putExtra("first name",f);
                intent.putExtra("last name",l);
                intent.putExtra("gender",g);
                intent.putExtra("mobile",m);
                intent.putExtra("email",e);
                v.getContext().startActivity(intent);

            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle(R.string.app_name);
                builder.setMessage("are you sure?")
                        .setCancelable(true)

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth=FirebaseAuth.getInstance().getCurrentUser().delete();
                        list.remove(currentPosition);
                        notifyItemRemoved(currentPosition);
                        notifyItemRangeChanged(currentPosition,list.size());
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MemberViewHolder extends RecyclerView.ViewHolder{
        TextView fn,ln,g,m,e;
        CardView cardView;
        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            fn=itemView.findViewById(R.id.frsNameTv);
            ln=itemView.findViewById(R.id.lstNameTv);
            g=itemView.findViewById(R.id.genderTv);
            m=itemView.findViewById(R.id.mobileTv);
            e=itemView.findViewById(R.id.emailTv);
           cardView=itemView.findViewById(R.id.dataContainer);
        }
    }
}

