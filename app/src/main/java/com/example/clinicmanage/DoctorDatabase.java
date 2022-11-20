package com.example.clinicmanage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Objects;

public class DoctorDatabase extends SQLiteOpenHelper {


    public static final String DOCTOR_TABLE = "DoctorTable";
    public static final String F_NAME = "Fname";
    public static final String L_NAME = " Lname";
    public static final String E_MAIL = " EMAIL";
    public static final String PH_NUMBER = " PHNUMBER";
    public static final String LIC_NO = "LICNO";
    public static final String PASSWORD = "Password";

    public DoctorDatabase(@Nullable Context context) {
        super(context, "DoctorDatabase.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DOCTOR_TABLE + " (" + F_NAME + " TEXT," + L_NAME + " TEXT," + E_MAIL + " TEXT," + PH_NUMBER + " TEXT PRIMARY KEY, " + LIC_NO + " TEXT); ";
        db.execSQL(query);
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

    public String getNumber(String mail, String password) {
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


} // class ends...
