package com.example.clinicmanage;

import androidx.appcompat.app.AppCompatActivity;

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

        DoctorDatabase db = new DoctorDatabase(loginPage.this);

        verify.setOnClickListener(view -> {
          String mailData = mail.getText().toString();
          String passwordData = password.getText().toString();

//          if(db.verifyUser(mailData,passwordData))
//              Toast.makeText(this, "Verified User", Toast.LENGTH_SHORT).show();
//          else
//              Toast.makeText(this, "You are not registered", Toast.LENGTH_SHORT).show();


        });


    }
}