package com.example.myregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileInfoActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        btnDone = findViewById(R.id.buttonDone);
        btnDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonDone){
            Intent intent= new Intent(ProfileInfoActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}