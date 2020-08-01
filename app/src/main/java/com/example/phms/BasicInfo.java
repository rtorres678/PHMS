package com.example.phms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorResolver;

public class BasicInfo extends AppCompatActivity {
    Button regNextBtn;
    TextInputLayout regName, regPhoneNumber, regGender, regWeight, regHeight, regCalorieGoal;
    DatabaseReference db_ref;
    private static final String TAG = "BasicInfo";
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button reg_nextBtn;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);
        regName = findViewById(R.id.reg_name);
        regPhoneNumber = findViewById(R.id.reg_phoneNumber);
        regGender = findViewById(R.id.reg_gender);
        regWeight = findViewById(R.id.reg_weight);
        regHeight = findViewById(R.id.reg_height);
        regCalorieGoal = findViewById(R.id.reg_calorieGoal);
        regNextBtn = findViewById(R.id.reg_nextBtn);

        user = new User();
        db_ref = FirebaseDatabase.getInstance().getReference("User");

        //onclicklistener
        regNextBtn.setOnClickListener(new View.OnClickListener(){
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
                db_ref.push().setValue(user);
                //upload to db
                Log.d(TAG, "DB Upload executed");
                Intent intent = new Intent(view.getContext(), Home.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}