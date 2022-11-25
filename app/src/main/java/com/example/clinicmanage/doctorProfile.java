package com.example.clinicmanage;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.database.CursorWindow;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class doctorProfile extends AppCompatActivity {

    ImageView imageview;
    FloatingActionButton floatbtn;
    Toolbar toolbar;
    Button logout, save;
    String loginId;
    Bitmap bitmap;
    TextView specialdata,regNo,mail,phNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        DoctorDatabase db = new DoctorDatabase(this);

        Intent in = getIntent();
        loginId = in.getStringExtra("login_id");


        imageview = findViewById(R.id.imageView);
        floatbtn = findViewById(R.id.floatingActionButton);
        toolbar = findViewById(R.id.toolbar);
        logout = findViewById(R.id.logout);
        save = findViewById(R.id.saveProfile);

        specialdata = findViewById(R.id.doctorSpecial);
        regNo = findViewById(R.id.regNo);
        mail = findViewById(R.id.mail);
        phNo = findViewById(R.id.phNo);

        specialdata.setText(db.getSpecial(loginId));
        regNo.setText(db.getRegNo(loginId));
        mail.setText(db.getMail(loginId));
        phNo.setText(loginId);




        if( db.checkImageStored(loginId)){
            imageview.setImageBitmap(ByteToBitmap(db.getImageStored(loginId)));
        }else{
            Toast.makeText(this, "Choose a profile picture.", Toast.LENGTH_SHORT).show();
        }




        ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();
                        if (intent != null) {
                            try {
                                 bitmap = MediaStore.Images.Media.getBitmap(
                                        getContentResolver(), intent.getData()
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



        toolbar.setNavigationOnClickListener(view -> {
            Intent intent = new Intent(doctorProfile.this, patientDetails.class);
            startActivity(intent);
        });

        logout.setOnClickListener(view -> {
            Intent intent = new Intent(doctorProfile.this, loginPage.class);
            startActivity(intent);
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
        });

        save.setOnClickListener(view -> {
            if (db.addProfileImage( loginId,BitmapToByte(bitmap))){
                Toast.makeText(this, "Profile picture added", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Image Not Added", Toast.LENGTH_SHORT).show();
            }
        });


    } //onCreate ends...

    public byte[] BitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        return stream.toByteArray();
    }

    public Bitmap ByteToBitmap(byte[] stream){
        return BitmapFactory.decodeByteArray(stream,0,stream.length);
    }

}  // class ends...