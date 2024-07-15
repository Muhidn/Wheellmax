package com.example.wheelmax77;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wheelmax77.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        databaseHelper = new DatabaseHelper(this);
    }

    public void onClick(View v){
        String email = binding.signupEmail.getText().toString();
        String password = binding.signupPassword.getText().toString();
        String confirm = binding.signupConfirm.getText().toString();
        String fullname = binding.signupFullname.getText().toString();
        String address = binding.signupAddress.getText().toString();
        String phoneNumber = binding.signupPhoneNumber.getText().toString();

        if (email.isEmpty() || password.isEmpty() || confirm.isEmpty() || fullname.isEmpty() || address.isEmpty() || phoneNumber.isEmpty())
            Toast.makeText(SignupActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();

        else {
            if (password.equals(confirm)){
                Boolean checkUserEmail = databaseHelper.checkEmail(email);

                if (!checkUserEmail){
                    Boolean insert = databaseHelper.insertUser(email,password, fullname, address, phoneNumber);

                    if (insert){
                        Toast.makeText(SignupActivity.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignupActivity.this, "Signup Fails", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignupActivity.this, "User already exists, Please login", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(SignupActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onPress(View v){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}