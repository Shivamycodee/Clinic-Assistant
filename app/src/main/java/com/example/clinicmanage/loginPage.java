package com.example.clinicmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginPage extends AppCompatActivity {

    EditText mail,password;
    Button verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mail = findViewById(R.id.verifyMail);
        password = findViewById(R.id.verifyPassword);
        verify = findViewById(R.id.verifyUser);

        DoctorDatabase db = new DoctorDatabase(this);

        verify.setOnClickListener(view ->{
         String Mail = mail.getText().toString();
         String Password = password.getText().toString();

          if( db.verifyUser(Mail,Password)) {

              Intent intent = new Intent(loginPage.this,doctorProfile.class);
              startActivity(intent);
              Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show();
          }
          else Toast.makeText(this, "Not registered", Toast.LENGTH_SHORT).show();
        });


    }
}