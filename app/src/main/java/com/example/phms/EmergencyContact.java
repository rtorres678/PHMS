package com.example.phms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmergencyContact extends AppCompatActivity {
    Button btnSave, btnCancel;
    TextInputLayout name, email, phone;
    DatabaseReference db_ref;
    private static final String TAG = "AddEmergencyContact";
    Contact contact;
    long maxid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emergencycontact);
        getSupportActionBar().hide();
        //============================================
        // Grab fields from XML
        //============================================
        name = findViewById(R.id.em_name);
        email = findViewById(R.id.em_email);
        phone = findViewById(R.id.em_phoneNumber);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        //============================================
        // NOTE CLASS INSTANTIATION, DB INITIALIZATION
        //============================================
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db_ref = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Emergency");
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
         contact = new Contact();

        //============================================
        // WHEN USER CLICKS "Save"
        //============================================
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                contact.setName(name.getEditText().getText().toString());
                contact.setEmail(email.getEditText().getText().toString());
                contact.setPhoneNumber(phone.getEditText().getText().toString());
                db_ref.setValue(contact);

                Intent intent = new Intent(view.getContext(), Home.class);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Home.class);
                startActivity(intent);
            }
        });


    }
}
