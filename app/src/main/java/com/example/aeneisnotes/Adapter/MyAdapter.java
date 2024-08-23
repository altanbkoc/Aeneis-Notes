package com.example.aeneisnotes.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aeneisnotes.Model.Notlar;
import com.example.aeneisnotes.R;
import com.example.aeneisnotes.View.DuzenleActivity;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<Notlar> notList;

    public MyAdapter(Context context,List<Notlar> notList){
        this.context=context;
        this.notList=notList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.rec_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.myText.setText(notList.get(position).baslik);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), DuzenleActivity.class);
                intent.putExtra("myNote",notList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText=itemView.findViewById(R.id.myTxt);
        }
    }
}
