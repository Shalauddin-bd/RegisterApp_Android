package com.example.myregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileInfoActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvName;
    TextView tvPhoneNo;
    TextView tvEmail;
    TextView tvAddress;
    Button btnDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        btnDone = findViewById(R.id.buttonDone);
        btnDone.setOnClickListener(this);

        tvName = findViewById(R.id.textViewName);
        tvPhoneNo = findViewById(R.id.textViewPhoneNo);
        tvEmail = findViewById(R.id.textViewEmail);
        tvAddress = findViewById(R.id.textViewAddress);

        Intent profileIntent = getIntent();
        if(getIntent() != null)
        {
            String name = profileIntent.getStringExtra("name");
            String phoneNo = profileIntent.getStringExtra("phoneNo");
            String email = profileIntent.getStringExtra("email");
            String address = profileIntent.getStringExtra("address");

            if(name != null){
                tvName.setText(name);
            }
            if(name != null){
                tvPhoneNo.setText(phoneNo);
            }
            if(name != null){
                tvEmail.setText(email);
            }
            if(name != null){
                tvAddress.setText(address);
            }

        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonDone){
            Intent intent= new Intent(ProfileInfoActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}