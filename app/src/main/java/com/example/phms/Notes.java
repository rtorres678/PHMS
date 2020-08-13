package com.example.phms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Notes extends AppCompatActivity {
    Button btnNewNote, btnCancel;
    private static final String TAG = "Notes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        getSupportActionBar().hide();
        //============================================
        // VARIABLES
        //============================================
        btnNewNote= (Button)findViewById(R.id.btnNewNote);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        //============================================
        // NEW NOTE BUTTON ONCLICK
        //============================================
        btnNewNote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), AddNote.class);
                startActivity(intent);
            }
        });

        //============================================
        // CANCEL BUTTON ONCLICK
        //============================================
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), Home.class);
                startActivity(intent);
            }
        });

    }
}