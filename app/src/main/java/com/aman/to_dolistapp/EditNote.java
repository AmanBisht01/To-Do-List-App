package com.aman.to_dolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class EditNote extends AppCompatActivity {
    EditText edttitle,edtdescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        Intent intent=getIntent();

        edttitle=findViewById(R.id.edi_edit_text);
        edtdescription=findViewById(R.id.edi_edit_description);

        edtdescription.setText(intent.getStringExtra("description"));
        edttitle.setText(intent.getStringExtra("title"));


    }
}