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

public class ValidateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_validate);
        final String originalOTP=getIntent().getStringExtra("otp");
        Toast.makeText(ValidateActivity.this,originalOTP,Toast.LENGTH_SHORT).show();
        EditText codeTxt = findViewById(R.id.OtpTxt);
        Button validateBtn= findViewById(R.id.Verifybtn);


        validateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp=codeTxt.getText().toString();
                if (otp.equals(originalOTP)){
                    Toast.makeText(ValidateActivity.this,"Valid OTP",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(ValidateActivity.this,SuccessActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ValidateActivity.this,"Invalid OTP",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}