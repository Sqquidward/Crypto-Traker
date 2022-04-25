package com.example.test_2.Register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test_2.MainActivity;
import com.example.test_2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_Login extends AppCompatActivity {

    Button Btn_Register_l;
    Button Btn_Login_l;
    EditText edit_Password_l;
    EditText edit_Email_l;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_Email_l = findViewById(R.id.Edit_Email_l);
        edit_Password_l = findViewById(R.id.Edit_Password_l);
        Btn_Login_l = findViewById(R.id.Btn_Login_l);
        Btn_Register_l = findViewById(R.id.Btn_register_l);
        mAuth = FirebaseAuth.getInstance();

        Btn_Register_l.setOnClickListener(view -> {
            Intent intent = new Intent(this, Activity_Register.class);
            startActivity(intent);
        });

        Btn_Login_l.setOnClickListener(view -> {
            loginUser();
        });
    }

    private void loginUser(){
        String email = edit_Email_l.getText().toString();
        String password = edit_Password_l.getText().toString();


        if (email.isEmpty()) {
            edit_Email_l.setError("Email cannot be empty");
            edit_Email_l.requestFocus();
        }
        else if (password.isEmpty()) {
            edit_Password_l.setError("Password cannot be empty");
            edit_Password_l.requestFocus();
        }

        else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Activity_Login.this, "User logged succesfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Activity_Login.this, MainActivity.class));
                    }else{
                        Toast.makeText(Activity_Login.this, "Error Log in!!" + task.getException().getMessage() , Toast.LENGTH_LONG).show();

                    }

                }
            });
    }




    }}
