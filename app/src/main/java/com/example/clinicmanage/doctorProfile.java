package com.example.clinicmanage;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class doctorProfile extends AppCompatActivity {

    ImageView imageview;
    FloatingActionButton floatbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        imageview = findViewById(R.id.imageView);
        floatbtn = findViewById(R.id.floatingActionButton);


       ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
               new ActivityResultContracts.StartActivityForResult(),
               new ActivityResultCallback<ActivityResult>() {
                   @Override
                   public void onActivityResult(ActivityResult result) {
                       Intent intent = result.getData();
                       if(intent != null){
                           try {
                               Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                       getContentResolver(),intent.getData()
                               );
                               imageview.setImageBitmap(bitmap);
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       }
                   }
               }
       );


        floatbtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            resultLauncher.launch(intent);
        });




    } //onCreate ends...



}  // class ends...