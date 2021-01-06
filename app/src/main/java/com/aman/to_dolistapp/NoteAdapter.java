package com.aman.to_dolistapp;

import android.content.Context;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class NoteAdapter extends RecyclerView.Adapter <NoteAdapter.NoteHolder>{

    ArrayList<Notes> notes;
    Context context;
    ItemClicked itemClicked;
    ViewGroup parent;


    public NoteAdapter(ArrayList<Notes> arrayList,Context context,ItemClicked itemClicked){
        this.notes=arrayList;
        this.context=context;
        this.itemClicked=itemClicked;
    }


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.note_holder, parent,false);
        this.parent=parent;
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {

        holder.title.setText(notes.get(position).getTitle());
        holder.discription.setText(notes.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView discription;
        ImageView imgedit;
        public NoteHolder(@NonNull View itemView) {
            super(itemView);


            title=itemView.findViewById(R.id.txt_node_name);
            discription=itemView.findViewById(R.id.txt_node_description);
            imgedit=itemView.findViewById(R.id.imgEdit);

            discription.setOnClickListener(v -> {
            if(discription.getMaxLines()==1){
                discription.setMaxLines(Integer.MAX_VALUE);
            }else {
                discription.setMaxLines(1);
            }TransitionManager.beginDelayedTransition(parent);
            });
            imgedit.setOnClickListener(view -> itemClicked.onclick(getAdapterPosition(), itemView));

        }
    }
        interface ItemClicked{
        void onclick(int position,View view );
        }

}
