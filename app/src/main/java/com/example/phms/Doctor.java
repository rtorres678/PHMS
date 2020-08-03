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



public class Doctor extends AppCompatActivity {
    Button btnSampleButton, btnSampleButton2, btnSampleButton3, btnSampleButton4;
    TextView headerText;
    DatabaseReference db_ref;
    private static final String TAG = "Doctor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getSupportActionBar().hide();

        //============================================
        // VARIABLES
        //============================================
        btnSampleButton = (Button)findViewById(R.id.btnSampleButton);
        headerText = (TextView)findViewById(R.id.headerText);
    }
}