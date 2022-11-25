package com.example.clinicmanage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Objects;

public class DoctorDatabase extends SQLiteOpenHelper {

    public static final String PATIENT_DETAIL = "PatientDetail";
    public static final String PATIENT_PH_NO = "Patient_PhNo";
    public static final String PATIENT_NAME = "Patient_Name";
    public static final String PATIENT_MAIL = "Patient_Mail";
    public static final String PATIENT_GENDER = "Patient_Gender";
    public static final String PATIENT_HEIGHT = "Patient_Height";
    public static final String PATIENT_WEIGHT = "Patient_Weight";
    public static final String PATIENT_AGE = "Patient_Age";
    public static final String PATIENT_BLOOD_TYPE = "Patient_BloodType";
    public static final String PATIENT_HEART_RATE = "Patient_HeartRate";
    public static final String PATIENT_WBC_COUNT = "Patient_WbcCount";
    public static final String PATIENT_SUGAR_LEVEL = "Patient_SugarLevel";
    public static final String PATIENT_EXTRA_DATA = "Patient_ExtraData";
    Context context;


    public static final String DOCTOR_TABLE = "DoctorTable";
    public static final String F_NAME = "Fname";
    public static final String L_NAME = " Lname";
    public static final String E_MAIL = " EMAIL";
    public static String PH_NUMBER = " PHNUMBER";
    public static final String LIC_NO = "LICNO";
    public static final String PASSWORD = "Password";
    public static final String DOCTOR_PROFILE = "DOCTOR_PROFILE";
    public static final String DOCTOR_PROFILE_IMAGE = "DOCTOR_PROFILE_IMAGE";
    public static final String PHONE_NUMBER = "PhoneNumber";
    public static final String PHONE = "Phone";
    public static final String SPECIALTIES = "Specialties";
    public static final String PROFILE_PHOTO = "ProfilePhoto";
    public static final String PROFILE = "Profile";


    public ByteArrayOutputStream byteArrayOutputStream;
    public byte[] imageInByte;

    public DoctorDatabase(@Nullable Context context) {
        super(context, "DoctorDatabase.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DOCTOR_TABLE + " (" + F_NAME + " TEXT," + L_NAME + " TEXT," + E_MAIL + " TEXT," + PH_NUMBER + " TEXT PRIMARY KEY, " + LIC_NO + " TEXT); ";
        String query02 = "Create table " + DOCTOR_PROFILE + "(" + PHONE_NUMBER + " TEXT PRIMARY KEY, " + SPECIALTIES + " TEXT," + PROFILE_PHOTO + " BLOB );";
        String query03 = "Create table " + DOCTOR_PROFILE_IMAGE + "(" + PHONE + " TEXT PRIMARY KEY, " + PROFILE + " BLOB );";
        String query04 = "Create table " + PATIENT_DETAIL +  " ( " + PATIENT_PH_NO + " TEXT PRIMARY KEY, " + PATIENT_NAME + "  TEXT, "  + PATIENT_MAIL + "  TEXT, " + PATIENT_GENDER + " TEXT, " + PATIENT_HEIGHT + "  TEXT, " + PATIENT_WEIGHT + "  TEXT, " + PATIENT_AGE + "  TEXT, " + PATIENT_BLOOD_TYPE + "  TEXT, " + PATIENT_HEART_RATE + "  TEXT, " + PATIENT_WBC_COUNT + "  TEXT, " + PATIENT_SUGAR_LEVEL + "  TEXT, " + PATIENT_EXTRA_DATA + "  TEXT); ";
        db.execSQL(query);
        db.execSQL(query02);
        db.execSQL(query03);
        db.execSQL(query04);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if(newVersion>oldVersion) db.execSQL("ALTER TABLE DoctorTable ADD COLUMN " + PASSWORD + " TEXT");
    }

    public void addData(String name, String Lastname, String mail, String phNo, String lic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(F_NAME, name);
        val.put(L_NAME, Lastname);
        val.put(E_MAIL, mail);
        val.put(PH_NUMBER, phNo);
        val.put(LIC_NO, lic);
        db.insert(DOCTOR_TABLE, null, val);
    }


    public boolean checkNumber(View view, String ph) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from DoctorTable", null);
        if (cursor.getCount() == 0)
            Toast.makeText(view.getContext(), "NO DATA RECORD", Toast.LENGTH_SHORT).show();
        else {
            while (cursor.moveToNext()) {
                String temp = cursor.getString(3);
                if (Objects.equals(temp, ph)) return false;
            }
        }
        cursor.close();
        return true;
    }


    public void passPassword(String confirmPassword) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from DoctorTable", null);
        cursor.moveToLast();
        String sql = "UPDATE DoctorTable SET Password = " + confirmPassword + " WHERE  PHNUMBER = " + cursor.getString(3);
        db.execSQL(sql);

    }

    public boolean verifyUser(String mail, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from DoctorTable", null);
        while (cursor.moveToNext()) {
            String temp = cursor.getString(2);
            if (Objects.equals(temp, mail)) {
                String pass = cursor.getString(5);
                if (password.equals(pass)) return true;
            }
        }
        return false;
    }

    public String getPhoneNumber(String mail, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from DoctorTable", null);
        while (cursor.moveToNext()) {
            String temp = cursor.getString(2);
            if (Objects.equals(temp, mail)) {
                String pass = cursor.getString(5);
                if (password.equals(pass)) {
                    return cursor.getString(3);
                }
            }
        }
        return "Not Registered";
    }


    public void addSpecialties(String phNumber,String special){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(PHONE_NUMBER, phNumber);
        val.put(SPECIALTIES,special);
        try{
        db.insert(DOCTOR_PROFILE, null, val);
        }catch(Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkSpecial(String phNumber){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from DOCTOR_PROFILE", null);
        while (cursor.moveToNext()) {
            String temp = cursor.getString(0);
            if (Objects.equals(temp,phNumber)) return true;
        }
        return false;
    }

    public boolean addProfileImage(String PhNo,byte[] profileImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(PHONE, PhNo);
        val.put(PROFILE, profileImage);
        db.insert(DOCTOR_PROFILE_IMAGE, null, val);
    return true;
    }

    public boolean checkImageStored(String loginId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from DOCTOR_PROFILE_IMAGE", null);
        while (cursor.moveToNext()) {
            String temp = cursor.getString(0);
            if (Objects.equals(temp,loginId)) return true;
        }
        return false;
    }

    public byte[]  getImageStored(String loginId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from DOCTOR_PROFILE_IMAGE", null);
        while (cursor.moveToNext()) {
            String temp = cursor.getString(0);
              if(Objects.equals(temp,loginId))
                  try {
                      return cursor.getBlob(1);
                  }catch(Exception e){
                      Log.e("error is : ",e.getMessage());
                  }
        }
        return null;
    }


    public String getSpecial(String loginId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from DOCTOR_PROFILE", null);
        while (cursor.moveToNext()) {
            String temp = cursor.getString(0);
            if (Objects.equals(temp,loginId)) return cursor.getString(1);
        }
        return null;
    }

    public String getRegNo(String loginId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from DoctorTable", null);
        while (cursor.moveToNext()) {
            String temp = cursor.getString(3);
            if (Objects.equals(temp,loginId)) return cursor.getString(4);
        }
        return null;
    }

    public String getMail(String loginId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from DoctorTable", null);
        while (cursor.moveToNext()) {
            String temp = cursor.getString(3);
            if (Objects.equals(temp,loginId)) return cursor.getString(2);
        }
        return null;
    }

    public void savePatientData(String name, String mail, String number, String age,
                                String height, String weight, String heartRate, String wbc,
                                String sugarLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(PATIENT_PH_NO,number);
        val.put(PATIENT_NAME,name);
        val.put(PATIENT_MAIL,mail);
        // gender


    }
} // class ends...
