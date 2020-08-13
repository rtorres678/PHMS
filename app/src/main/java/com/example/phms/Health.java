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



public class Health extends AppCompatActivity {
    Button btnVital, btnWeight, btnMedications, btnFood;
    TextView headerText;
    DatabaseReference db_ref;
    private static final String TAG = "Health";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        getSupportActionBar().hide();

        //============================================
        // VARIABLES
        //============================================
        btnVital = (Button)findViewById(R.id.btnVital);
        btnWeight = (Button)findViewById(R.id.btnWeight);
        btnMedications = (Button)findViewById(R.id.btnMedications);
        btnFood = (Button)findViewById(R.id.btnFood);
        headerText = (TextView)findViewById(R.id.headerText);
    }
}