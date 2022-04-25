package com.example.test_2.Register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test_2.MainActivity;
import com.example.test_2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_Register extends AppCompatActivity{

    Button btn_Login_r;
    Button btn_Register_r;
    EditText edit_Email_r;
    EditText edit_Password_r;
    EditText edit_Passwordag_r;
    EditText edit_Name_r, edit_LastName_r;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_Login_r = findViewById(R.id.Btn_login_r);
        btn_Register_r = findViewById(R.id.Btn_register_r);
        edit_Email_r = findViewById(R.id.Edit_Email_r);
        edit_Password_r = findViewById(R.id.Edit_Password_r);
        edit_Passwordag_r = findViewById(R.id.Edit_Passwordag_r);
        edit_Name_r = findViewById(R.id.Edit_name_r);
        edit_LastName_r = findViewById(R.id.Edit_lastname_r);
//
        mAuth = FirebaseAuth.getInstance();
        btn_Register_r.setOnClickListener(view -> {
            createUser();
        });

        btn_Login_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLoginActivity();
            }
        });

    }

    private void toLoginActivity(){
        Intent intent = new Intent(this, Activity_Login.class);
        startActivity(intent);
    }






    private void createUser() {
        String name = edit_Name_r.getText().toString();
        String email = edit_Email_r.getText().toString();
        String password = edit_Password_r.getText().toString();
        String passwordag = edit_Passwordag_r.getText().toString();
        String lastname = edit_LastName_r.getText().toString();


        if (email.isEmpty()) {
            edit_Email_r.setError("Email cannot be empty");
            edit_Email_r.requestFocus();
        }
        else if (password.isEmpty()) {
            edit_Password_r.setError("Password cannot be empty");
            edit_Password_r.requestFocus();
        }
        else if (name.isEmpty()) {
            edit_Name_r.setError("Name cannot be empty");
            edit_Name_r.requestFocus();
        }
        else if (lastname.isEmpty()){
            edit_LastName_r.setError("Last name cannot be empty");
            edit_LastName_r.requestFocus();
        }


//        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            edit_Email_r.setError("Provide valid email");
//            edit_Email_r.requestFocus();
//            return;
//        }

//        else if (password != passwordag) {
//            edit_Password_r.setError("passwords don't match");
//            edit_Password_r.requestFocus();}
         else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        User user = new User(name, lastname, email, password);

                        FirebaseDatabase database = FirebaseDatabase.getInstance();

                        FirebaseDatabase.getInstance().getReference("users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {


                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(Activity_Register.this, MainActivity.class));
                            }
                        });
                        Toast.makeText(Activity_Register.this, "User registered succesfully", Toast.LENGTH_LONG).show();
                       // startActivity(new Intent(Activity_Register.this, Activity_Login.class));

                    } else {
                        Toast.makeText(Activity_Register.this, "Error registration!!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }

                }
            });

        }
    }}