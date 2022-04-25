package com.example.test_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_2.Register.Activity_Login;
import com.example.test_2.Register.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ServiceConfigurationError;

public class Setting extends AppCompatActivity {

    Button exit_btn, mainactivity_btn;
    TextView name, lastname, email, password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        exit_btn = findViewById(R.id.exit_setting);
        name = findViewById(R.id.name_setting);
        lastname = findViewById(R.id.lastname_setting);
        email = findViewById(R.id.email_setting);
        mainactivity_btn = findViewById(R.id.mainactivity_setting);
        password = findViewById(R.id.password_setting);

        mainactivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting.this, MainActivity.class);
                startActivity(intent);
            }
        });


        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Setting.this, Activity_Login.class);
                startActivity(intent);
                Toast.makeText(Setting.this, "You have logged out of your account", Toast.LENGTH_LONG).show();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user!=null){
                    name.setText(user.name);
                    lastname.setText(user.lastname);
                    email.setText(user.email);
                    password.setText(user.password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}