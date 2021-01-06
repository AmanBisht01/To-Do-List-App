package com.aman.to_dolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditNote extends AppCompatActivity {
    EditText edttitle,edtdescription;
    Button btncancel,btnsave;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        Intent intent=getIntent();
        linearLayout=findViewById(R.id.btnholder);
        edttitle=findViewById(R.id.edi_edit_text);
        edtdescription=findViewById(R.id.edi_edit_description);

        edtdescription.setText(intent.getStringExtra("description"));
        edttitle.setText(intent.getStringExtra("title"));
        btncancel=findViewById(R.id.btncancel);
        btnsave=findViewById(R.id.btnsave);

        btncancel.setOnClickListener(v -> onBackPressed());

        btnsave.setOnClickListener(v -> {
            Notes note=new Notes(edttitle.getText().toString(), edtdescription.getText().toString());
            note.setId(intent.getIntExtra("id",1));
            if(new NotesHandler(this).update(note))
                Toast.makeText(this, "Note Updates", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Failed Updates", Toast.LENGTH_SHORT).show();


            onBackPressed();

        });


    }

    @Override
    public void onBackPressed() {
        btnsave.setVisibility(View.GONE);
        btncancel.setVisibility(View.GONE);
        TransitionManager.beginDelayedTransition(linearLayout);
        super.onBackPressed();

    }
}