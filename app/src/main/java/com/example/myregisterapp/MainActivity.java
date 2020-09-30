package com.example.myregisterapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivProfileImage;
    static final int PickImage=1;
    Uri imageUri;

    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivProfileImage = findViewById(R.id.imageViewUserImage);
        ivProfileImage.setOnClickListener(this);
        btnSubmit = findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.imageViewUserImage){
            Intent gallery = new Intent();
            gallery.setType("image/*");
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(gallery,"Select image"),PickImage );
        } else if(view.getId()==R.id.buttonSubmit){
            Intent intent= new Intent(MainActivity.this, ProfileInfoActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PickImage && resultCode== RESULT_OK)
            imageUri = data.getData();
       try {
           Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
           ivProfileImage.setImageBitmap(image);
       }
       catch (IOException e){
            e.printStackTrace();
       }
    }


}