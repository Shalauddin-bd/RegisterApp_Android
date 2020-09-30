package com.example.myregisterapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //private Context context;
    EditText etName;
    EditText etPhoneNo;
    EditText etEmail;
    EditText etAddress;
    ImageView ivProfileImage;
    static final int PickImage=1;

    Uri imageUri;
    Bitmap image;

    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivProfileImage = findViewById(R.id.imageViewUserImage);
        ivProfileImage.setOnClickListener(this);
        etName=findViewById(R.id.editTextName);
        etPhoneNo=findViewById(R.id.editTextPhoneNo);
        etEmail=findViewById(R.id.editTextEmail);
        etAddress=findViewById(R.id.editTextAddress);
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
            try {
                Intent intent = new Intent(MainActivity.this, ProfileInfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name",etName.getText().toString());
                intent.putExtra("phoneNo",etPhoneNo.getText().toString());
                intent.putExtra("email",etEmail.getText().toString());
                intent.putExtra("address",etAddress.getText().toString());

            //Convert to byte array
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] byteArray = stream.toByteArray();
//            intent.putExtra("image",byteArray);

                //Write file
                String filename = "bitmap.png";
                FileOutputStream stream = this.openFileOutput(filename, Context.MODE_PRIVATE);
                image.compress(Bitmap.CompressFormat.PNG, 100, stream);

                //Cleanup
                stream.close();
                image.recycle();

                intent.putExtra("image", filename);

                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PickImage && resultCode== RESULT_OK)
            imageUri = data.getData();
       try {
           image = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
           ivProfileImage.setImageBitmap(image);
       }
       catch (IOException e){
            e.printStackTrace();
       }
    }


}