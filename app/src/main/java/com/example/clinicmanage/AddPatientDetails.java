package com.example.clinicmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AddPatientDetails extends AppCompatActivity {

    EditText patientName,patientMail,patientNumber,patientAge,patientHeight,patientWeight,patientHeartRate,patientWbc,patientSugarLevel;
    TextView EpatientName,EpatientMail,EpatientNumber,EpatientAge,EpatientHeight,EpatientWeight,EpatientHeartRate,EpatientWbc,EpatientSugarLevel,EpatientGender;
    String name,mail,number,age,height,weight,heartRate,wbc,sugarLevel;
    RadioGroup genderCheck;
    int getId;
    RadioButton gender;
    Button submit;
    String genderValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient_details);

        patientName = findViewById(R.id.patientName);
        patientMail = findViewById(R.id.patientMail);
        patientNumber = findViewById(R.id.patientNumber);
        patientAge = findViewById(R.id.patientAge);
        patientHeight = findViewById(R.id.patientHeight);
        patientHeartRate = findViewById(R.id.patientHeartRate);
        patientWbc = findViewById(R.id.patientWbc);
        patientSugarLevel = findViewById(R.id.patientSugarLevel);

        DoctorDatabase db = new DoctorDatabase(AddPatientDetails.this);
        submit = findViewById(R.id.submitPatient);

        name = patientName.getText().toString();
        mail = patientMail.getText().toString();
        number = patientNumber.getText().toString();
        age = patientAge.getText().toString();
        height = patientHeight.getText().toString();
        weight = patientWeight.getText().toString();
        heartRate = patientHeartRate.getText().toString();
        wbc = patientWbc.getText().toString();
        sugarLevel = patientSugarLevel.getText().toString();



    submit.setOnClickListener(view -> {
        if( checkInput()){
//            db.savePatientData(name,mail,number,age,height,weight,heartRate,wbc,sugarLevel);
            Toast.makeText((Context) this, "successfully data entered", Toast.LENGTH_SHORT).show();
            Toast.makeText((Context) this, name+" "+age+" "+height, Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Enter details", Toast.LENGTH_SHORT).show();
        }

    });

    } // oncreate method ends...


    public boolean checkInput(){
        boolean flag = true;

        EpatientName = findViewById(R.id.EpatientName);
        EpatientMail = findViewById(R.id.EpatientMail);
        EpatientNumber = findViewById(R.id.EpatientNumber);
        EpatientAge = findViewById(R.id.EpatientAge);
        EpatientHeight = findViewById(R.id.EpatientHeight);
        EpatientHeartRate = findViewById(R.id.EpatientHeartRate);
        EpatientWbc = findViewById(R.id.EpatientWbc);
        EpatientSugarLevel = findViewById(R.id.EpatientSugarLevel);

        genderCheck = findViewById(R.id.patientGender);


        if(String.valueOf(patientName.getText()).equals("")) {
            EpatientName.setVisibility(View.VISIBLE);
            flag = false;
        } else {
            EpatientName.setVisibility(View.INVISIBLE);
            flag = true;
        }


        if(String.valueOf(patientMail.getText()).equals("")) {
            EpatientMail.setVisibility(View.VISIBLE);
            flag = false;
        } else  {
            EpatientMail.setVisibility(View.INVISIBLE);
            flag = true;
        }

        if(String.valueOf(patientNumber.getText()).equals("")) {
            EpatientNumber.setVisibility(View.VISIBLE);
            flag = false;
        } else {
            EpatientNumber.setVisibility(View.INVISIBLE);
            flag = true;
        }

        if(String.valueOf(patientSugarLevel.getText()).equals("")) {
            EpatientSugarLevel.setVisibility(View.VISIBLE);
            flag = false;
        } else {
            EpatientSugarLevel.setVisibility(View.INVISIBLE);
            flag = true;
        }

        if(String.valueOf(patientHeight.getText()).equals("")) {
            EpatientHeight.setVisibility(View.VISIBLE);
            flag = false;
        } else {
            EpatientHeight.setVisibility(View.INVISIBLE);
            flag = true;
        }

        if(String.valueOf(patientWeight.getText()).equals("")) {
            EpatientWeight.setVisibility(View.VISIBLE);
            flag = false;
        } else {
            EpatientWeight.setVisibility(View.VISIBLE);
            flag = true;
        }

        if(String.valueOf(patientHeartRate.getText()).equals("")) {
            EpatientHeartRate.setVisibility(View.VISIBLE);
        } else   {
            EpatientHeartRate.setVisibility(View.INVISIBLE);
            flag = true;
        }

        if(String.valueOf(patientAge.getText()).equals("")) {
            EpatientAge.setVisibility(View.VISIBLE);
            flag = false;
        } else {
            EpatientAge.setVisibility(View.INVISIBLE);
            flag = true;
        }

        if(String.valueOf(patientWbc.getText()).equals("")) {
            EpatientWbc.setVisibility(View.VISIBLE);
            flag = false;
        } else  {
            EpatientWbc.setVisibility(View.INVISIBLE);
            flag = true;
        }

        getId = genderCheck.getCheckedRadioButtonId();
        if(getId == -1) {
            EpatientGender.setVisibility(View.VISIBLE);
            flag = false;
        } else {
            gender = findViewById(getId);
          genderValue = gender.getText().toString();
            EpatientGender.setVisibility(View.INVISIBLE);
            flag = true;
        }

       return flag;
    }

} // class ends