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

    EditText patientName,patientMail,patientNumber,patientAge,patientHeight,patientWeight,patientHeartRate,patientWbc,patientSugarLevel,patientBloodType;
    TextView EpatientName,EpatientMail,EpatientNumber,EpatientAge,EpatientHeight,EpatientWeight,EpatientHeartRate,EpatientWbc,EpatientSugarLevel,EpatientGender,EpatientBloodType;
    RadioGroup genderCheck;
    RadioButton gender;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient_details);

        patientName = findViewById(R.id.patientName);
        patientMail = findViewById(R.id.patientMail);
        patientNumber = findViewById(R.id.patientNumber);
        patientAge = findViewById(R.id.patientAge);
        patientHeight = findViewById(R.id.patientHeight);
        patientWeight = findViewById(R.id.patientWeight);
        patientHeartRate = findViewById(R.id.patientHeartRate);
        patientWbc = findViewById(R.id.patientWbc);
        patientSugarLevel = findViewById(R.id.patientSugarLevel);
        patientBloodType = findViewById(R.id.patientBloodType);

        DoctorDatabase db = new DoctorDatabase(AddPatientDetails.this);
        submit = findViewById(R.id.submitPatient);





    submit.setOnClickListener(view -> {
        String name,mail,number,age,height,weight,heartRate,wbc,sugarLevel,bloodType,genderValue;
        int getId;

        name = String.valueOf(patientName.getText());
        mail = String.valueOf(patientMail.getText());
        number = String.valueOf(patientNumber.getText());
        age = String.valueOf(patientAge.getText());
        height = String.valueOf(patientHeight.getText());
        weight = String.valueOf(patientWeight.getText());
        heartRate = String.valueOf(patientHeartRate.getText());
        wbc = String.valueOf(patientWbc.getText());
        sugarLevel = String.valueOf(patientSugarLevel.getText());
        bloodType = String.valueOf(patientBloodType.getText());
        genderCheck = findViewById(R.id.patientGender);
        EpatientGender = findViewById(R.id.EpatientGender);

        getId = genderCheck.getCheckedRadioButtonId();
        if(getId == -1) {
            EpatientGender.setVisibility(View.VISIBLE);
        } else {
            EpatientGender.setVisibility(View.INVISIBLE);
        }

      if(db.isPresent(number)) {
          if (checkInput() && getId != -1) {
              gender = findViewById(getId);
              genderValue = String.valueOf(gender);

              if (db.savePatientData(name, mail, number, age, genderValue, height, weight, bloodType, heartRate, wbc, sugarLevel)) {
                  Toast.makeText((Context) this, "successfully data entered", Toast.LENGTH_SHORT).show();
              } else {
                  Toast.makeText(this, "Data Not Entered In Database", Toast.LENGTH_SHORT).show();
              }
          } else {
              Toast.makeText(this, "Enter details", Toast.LENGTH_SHORT).show();
          }
      }else{
          Toast.makeText(this, "Patient data already exist.", Toast.LENGTH_SHORT).show();
      }

    });


    } // oncreate method ends...


    public boolean checkInput(){

        boolean flag = true;

        EpatientName = findViewById(R.id.EpatientName);
        EpatientMail = findViewById(R.id.EpatientMail);
        EpatientNumber = findViewById(R.id.EpatientNumber);
        EpatientHeight = findViewById(R.id.EpatientHeight);
        EpatientHeartRate = findViewById(R.id.EpatientHeartRate);
        EpatientWeight = findViewById(R.id.EpatientWeight);
        EpatientWbc = findViewById(R.id.EpatientWbc);
        EpatientSugarLevel = findViewById(R.id.EpatientSugarLevel);
        EpatientAge = findViewById(R.id.EpatientAge);
        EpatientBloodType = findViewById(R.id.EpatientBloodType);

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

        if(String.valueOf(patientBloodType.getText()).equals("")) {
            EpatientBloodType.setVisibility(View.VISIBLE);
            flag = false;
        } else {
            EpatientBloodType.setVisibility(View.INVISIBLE);
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
            EpatientWeight.setVisibility(View.INVISIBLE);
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
       return flag;
    }

} // class ends