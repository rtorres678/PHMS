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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorResolver;


public class Login extends AppCompatActivity {

    //============================================
    // DECLARE JAVA VARIABLES FOR THIS FORM
    //============================================
    TextInputLayout regEmailAddress, regPassword;
    Button regLoginBtn, regGoToSignupBtn;
    private FirebaseAuth mAuth;

    //============================================
    // HAVE TO MAKE AN OBJECT TO ACCESS A LOGIN_SUCCESSFUL BOOLEAN GLOBALLY
    //============================================
    private boolean loginSuccessful = false;
    public boolean getLoginSuccessful(){ return loginSuccessful; }
    public void setLoginSuccessful(boolean bool){ loginSuccessful = bool; }

    //============================================
    // ONCREATE() -> ON ACTIVITY LOAD
    //============================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        //============================================
        // CREATE AND RELATE JAVA VARIABLES WITH EACH XML ELEMENT
        //============================================
        regEmailAddress = findViewById(R.id.reg_emailAddress);
        regPassword = findViewById(R.id.reg_password);
        regLoginBtn = findViewById(R.id.reg_loginBtn);
        regGoToSignupBtn = findViewById(R.id.reg_goToSignupBtn);

        mAuth = FirebaseAuth.getInstance();


        //============================================
        // LOGIN BUTTON LISTENER
        //============================================
        regLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String emailAddress = regEmailAddress.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                signIn(emailAddress, password);
                if(getLoginSuccessful()) {
                    Intent intent = new Intent(view.getContext(), Home.class); //Load home activity
                    view.getContext().startActivity(intent);
                }
            }
        });
        regGoToSignupBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Signup.class); //Load home activity
                view.getContext().startActivity(intent);}
        });
    }

    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            setLoginSuccessful(true);
                        } else {
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}