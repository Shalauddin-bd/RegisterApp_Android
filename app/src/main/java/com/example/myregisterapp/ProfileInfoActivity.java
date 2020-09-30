package com.example.myregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;

public class ProfileInfoActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvName;
    TextView tvPhoneNo;
    TextView tvEmail;
    TextView tvAddress;
    ImageView ivProfileImage;
    Button btnDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        btnDone = findViewById(R.id.buttonDone);
        btnDone.setOnClickListener(this);

        ivProfileImage = findViewById(R.id.imageViewUserImage);
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
            if(phoneNo != null){
                tvPhoneNo.setText(phoneNo);
            }
            if(email != null){
                tvEmail.setText(email);
            }
            if(address != null){
                tvAddress.setText(address);
            }

            Bitmap image = null;
            String filename = getIntent().getStringExtra("image");
            try {
                FileInputStream is = this.openFileInput(filename);
                image = BitmapFactory.decodeStream(is);
                ivProfileImage.setImageBitmap(image);
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
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