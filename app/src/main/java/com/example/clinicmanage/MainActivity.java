package com.example.clinicmanage;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Fname , Lname, Mail, PhNo, otp,licNo;
    TextView fnameWarn, lnameWarn, mailWarn, phonNoWarn, otpWarn;
    Button otpBtn, verBtn; public static int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    if(i == 1) {
        Intent intent = new Intent(this, loginPage.class);
        i = 2;
        startActivity(intent);
    }

        otpBtn = findViewById(R.id.btnOtp);
        verBtn = findViewById(R.id.btnVerfiy);
        otp= findViewById(R.id.inOtp);
        otpWarn  = findViewById(R.id.inOtpWarn);
        Fname= findViewById(R.id.fname);
         Lname= findViewById(R.id.lname);
         Mail= findViewById(R.id.mail);
         PhNo= findViewById(R.id.phonNo);
         licNo = findViewById(R.id.LicNo);

        DoctorDatabase db = new DoctorDatabase(MainActivity.this);

        otpBtn.setOnClickListener(view -> { if( docRegValidate()) otp.setVisibility(View.VISIBLE); });

        verBtn.setOnClickListener(view -> {
            if(docRegValidate() && otp.getText().toString().equals("")) Toast.makeText(MainActivity.this, "Enter OTP", Toast.LENGTH_SHORT).show();
            if(!docRegValidate()) Toast.makeText(MainActivity.this, "Fill the details...", Toast.LENGTH_SHORT).show();


    if(docRegValidate() && !otp.getText().toString().equals("")) {
        String nme = Fname.getText().toString();
        String lame = Lname.getText().toString();
        String ml = Mail.getText().toString();
        String ph = PhNo.getText().toString();
        String lic = licNo.getText().toString();

        boolean flag =  db.checkNumber(view,ph);

        if(flag){
            db.addData(nme, lame, ml, ph,lic);
            Toast.makeText(this, "Data has been added", Toast.LENGTH_SHORT).show();
            PasswordBottomSheetFragment box = new PasswordBottomSheetFragment();
            box.show(getSupportFragmentManager(),box.getTag());
        }else{
            Toast.makeText(this, "Phone Number Already exist", Toast.LENGTH_SHORT).show();
            PhNo.setText(""); }
    }

        });

    } // end of onCreate...

    public boolean docRegValidate() {

      boolean flag = true;
        Fname= findViewById(R.id.fname);
        Lname = findViewById(R.id.lname);
        Mail = findViewById(R.id.mail);
        PhNo = findViewById(R.id.phonNo);
        fnameWarn = findViewById(R.id.fnameWarn);
        lnameWarn = findViewById(R.id.lnameWarn);
        mailWarn = findViewById(R.id.mailWarn);
        phonNoWarn = findViewById(R.id.phonNoWarn);

        if(Fname.getText().toString().equals("")){
            fnameWarn.setVisibility(View.VISIBLE);
            flag = false;
        }else  fnameWarn.setVisibility(View.INVISIBLE);

        if(Lname.getText().toString().equals("")){
            lnameWarn.setVisibility(View.VISIBLE);
            flag = false;
        } else  fnameWarn.setVisibility(View.INVISIBLE);

        if(Mail.getText().toString().equals("")) {
            mailWarn.setVisibility(View.VISIBLE);
            flag = false;
        } else  fnameWarn.setVisibility(View.INVISIBLE);

        if(PhNo.getText().toString().equals("")) {
            phonNoWarn.setVisibility(View.VISIBLE);
            flag = false;
        } else  fnameWarn.setVisibility(View.INVISIBLE);

        return flag;
    }

} // End of class...