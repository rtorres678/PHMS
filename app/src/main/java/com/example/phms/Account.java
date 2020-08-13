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



public class Account extends AppCompatActivity {
<<<<<<< Updated upstream
    Button btnSampleButton, btnSampleButton2, btnSampleButton3, btnSampleButton4;
    TextView headerText;
=======
    Button regUpdateBtn, btnCancel;
    TextInputLayout regName, regPhoneNumber, regGender, regWeight, regHeight, regCalorieGoal;
>>>>>>> Stashed changes
    DatabaseReference db_ref;
    private static final String TAG = "Account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getSupportActionBar().hide();

        //============================================
        // VARIABLES
        //============================================
<<<<<<< Updated upstream
        btnSampleButton = (Button)findViewById(R.id.btnSampleButton);
        headerText = (TextView)findViewById(R.id.headerText);
=======
        //============================================
        // Grab fields from XML
        //============================================
        regName = findViewById(R.id.reg_name);
        regPhoneNumber = findViewById(R.id.reg_phoneNumber);
        regGender = findViewById(R.id.reg_gender);
        regWeight = findViewById(R.id.reg_weight);
        regHeight = findViewById(R.id.reg_height);
        regCalorieGoal = findViewById(R.id.reg_calorieGoal);
        regUpdateBtn = findViewById(R.id.reg_updateBtn);
        btnCancel = findViewById(R.id.btnCancel);
        //============================================
        // USER CLASS INSTANTIATION, DB INITIALIZATION
        //============================================
        user = new User();
        db_ref = FirebaseDatabase.getInstance().getReference().child("Users");
        //============================================
        // WHEN USER CLICKS "NEXT"
        //============================================
        regUpdateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //============================================
                // Could use some form validation!
                // Fine for now -> Proceed to register input in database
                //============================================
                //Get all the values
                String name = regName.getEditText().getText().toString();
                String phoneNumber = regPhoneNumber.getEditText().getText().toString();
                String gender = regGender.getEditText().getText().toString();
                int weight = Integer.parseInt(regWeight.getEditText().getText().toString());
                int height = Integer.parseInt(regHeight.getEditText().getText().toString());
                int calorieGoal = Integer.parseInt(regCalorieGoal.getEditText().getText().toString());
                user.setName(name);
                user.setPhoneNumber(phoneNumber);
                user.setGender(gender);
                user.setWeight(weight);
                user.setHeight(height);
                user.setCalorieGoal(calorieGoal);

                //============================================
                // SPECIFY NAME OF CHILD NODE OF USER CLASS THAT WE ARE GOING TO ADD
                //============================================
                db_ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
                //upload to db
                Log.d(TAG, "DB Update executed");

                Intent intent = new Intent(view.getContext(), Home.class);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Home.class);
                startActivity(intent);
            }
        });
>>>>>>> Stashed changes
    }
}