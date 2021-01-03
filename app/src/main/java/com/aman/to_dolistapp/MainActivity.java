package com.aman.to_dolistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButton;
    ArrayList<Notes> notes;
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton =findViewById(R.id.imageButton);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=(LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewInput=inflater.inflate(R.layout.note_input, null,false);

                final EditText editTitle = viewInput.findViewById(R.id.edit_title);
                final EditText editdescription = viewInput.findViewById(R.id.edit_description);

                new AlertDialog.Builder(MainActivity.this).setView(viewInput).setTitle("Add Note").setPositiveButton("ADD",
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String title=editTitle.getText().toString();
                        String description=editdescription.getText().toString();

                        Notes notes=new Notes( title, description);
                        boolean isinsertred=new NotesHandler(MainActivity.this).create(notes);

                        if (isinsertred){
                            Toast.makeText(MainActivity.this, "Note Saved", Toast.LENGTH_SHORT).show();
                            loadnotes();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Unale to save note", Toast.LENGTH_SHORT).show();
                        }
                        dialog.cancel();

                    }
                }).show();

            }
        });

        recyclerView=findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(
                0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                new NotesHandler(MainActivity.this).delete(notes.get(viewHolder.getAdapterPosition()).getId());
                notes.remove(viewHolder.getAdapterPosition());
                noteAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        };


        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    loadnotes();
    }



    public ArrayList<Notes> readnotes(){
        return new NotesHandler(this).readNotes();
    }

    public void loadnotes(){
        notes=readnotes();
        noteAdapter=new NoteAdapter(notes, MainActivity.this);
        recyclerView.setAdapter(noteAdapter);

    }

}