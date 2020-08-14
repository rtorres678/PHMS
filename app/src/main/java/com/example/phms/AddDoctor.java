package com.example.phms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class AddDoctor extends AppCompatActivity {
    Button docbtnSave, docbtnCancel;
    TextInputLayout docName, docDate, docCheckupInfo;
    DatabaseReference db_ref;
    private static final String TAG = "AddDoctor";
    Doctor doctor;
    long maxid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        getSupportActionBar().hide();
        //============================================
        // Grab fields from XML
        //============================================
        docName = findViewById(R.id.doc_name);
        docDate = findViewById(R.id.doc_date);
        docCheckupInfo = findViewById(R.id.doc_CheckupInfo);
        docbtnSave = (Button)findViewById(R.id.btnSave);
        docbtnCancel = (Button)findViewById(R.id.btnCancel);
        //============================================
        // NOTE CLASS INSTANTIATION, DB INITIALIZATION
        //============================================
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db_ref = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Doctor");
        db_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    maxid=snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        doctor = new Doctor();

        //============================================
        // WHEN USER CLICKS "Save"
        //============================================
        docbtnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                doctor.setName(docName.getEditText().getText().toString());
                doctor.setDate(docDate.getEditText().getText().toString());
                doctor.setCheckInfo(docCheckupInfo.getEditText().getText().toString());
                db_ref.setValue(doctor);

                Intent intent = new Intent(view.getContext(), Home.class);
                startActivity(intent);
            }
        });

        docbtnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Home.class);
                startActivity(intent);
            }
        });

    }
    }