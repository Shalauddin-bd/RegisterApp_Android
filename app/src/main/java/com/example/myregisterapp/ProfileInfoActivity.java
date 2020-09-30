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
    Button btnDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        btnDone = findViewById(R.id.buttonDone);
        btnDone.setOnClickListener(this);

        tvName = findViewById(R.id.textViewName);

        Intent profileIntent = getIntent();
        if(getIntent() != null)
        {
            Log.i("Profile Info Activity","Name received!");
            String name = profileIntent.getStringExtra("name");
            if(name != null){
                tvName.setText(name);
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