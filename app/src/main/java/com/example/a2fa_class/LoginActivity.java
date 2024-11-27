package com.example.a2fa_class;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import javax.mail.MessagingException;

public class LoginActivity extends AppCompatActivity {
    private EmailSender sender= new EmailSender();
    private String hardcodeEmail="Recipent Email";
    private String hardcodedPass="Password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        EditText userEmail= findViewById(R.id.EmailField);
        EditText userPass=findViewById(R.id.PasswordField);
        Button loginBtn=findViewById(R.id.LogInHandler);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString().trim();
                String pass= userPass.getText().toString().trim();
                if(email.isEmpty()||pass.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Insert Data in fields",Toast.LENGTH_SHORT).show();
                } else if (email.equals(hardcodeEmail)&&pass.equals(hardcodedPass)){
                    String otp=OTPGenerator.generate(6);
                    sendMail(email,otp);
                    Intent intent= new Intent(LoginActivity.this,ValidateActivity.class);
                    intent.putExtra("email",email);
                    intent.putExtra("otp",otp);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Data is not Valid",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void sendMail(String email, String otp) {
        new Thread(() -> {
            try {
                sender.sendOTPEmail(email, otp);
                runOnUiThread(() -> Toast.makeText(LoginActivity.this, "OTP sent to " + email, Toast.LENGTH_SHORT).show());
            } catch (MessagingException e) {
                runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Failed to send OTP", Toast.LENGTH_SHORT).show());
                e.printStackTrace();
            }
        }).start();
    }
}