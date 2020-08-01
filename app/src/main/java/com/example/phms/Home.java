package com.example.phms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.MultiFactorResolver;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
Button btnBtn, btnLogout;
TextView welcomeText;
DatabaseReference db_ref;
private static final String TAG = "Home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "phms_uid in Home: "+FirebaseAuth.getInstance().getCurrentUser().getUid());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        //============================================
        // VARIABLES
        //============================================
        btnBtn = (Button)findViewById(R.id.btnBtn);
        btnLogout = (Button)findViewById(R.id.btnLogout);
        welcomeText = (TextView)findViewById(R.id.welcomeText);

        //============================================
        // SIGNOUT BUTTON ONCLICK
        //============================================
        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(view.getContext(), Login.class);
                startActivity(intent);
            }
        });

        //============================================
        // NAME UPDATE BUTTON ONCLICK
        //============================================
        btnBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                db_ref = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()); // MUCH FUNCTION ACCESSING VERY WOW
                db_ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = snapshot.child("name").getValue().toString();
                        welcomeText.setText("Welcome, "+name);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}