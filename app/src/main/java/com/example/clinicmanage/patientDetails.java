package com.example.clinicmanage;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class patientDetails extends AppCompatActivity {

    ListView listview;
    SearchView searchview;
    ArrayList<String> list;
    FloatingActionButton floatAdd;
   BottomAppBar bottomAppBar;
   String loginId,special;
    String[] specializationArray = new String[] {
            "General Practitioner",
            "Cardiologist",
            "Dentist",
            "ENT Specialist",
            "Gynaecologist",
            "Orthopaedic Surgeon",
            "Paediatrician",
            "Psychiatrists",
            "Radiologist",
            "Pulmonologist",
            "Neurologist",
            "Oncologist",
            "Endocrinologist"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        listview = findViewById(R.id.listView);
        searchview = findViewById(R.id.searchView);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        DoctorDatabase db = new DoctorDatabase(this);


        Intent i = getIntent();
        loginId = i.getStringExtra("login_id");

        floatAdd = findViewById(R.id.floatClick);
        floatAdd.setOnClickListener(view -> {
            Intent intent = new Intent(patientDetails.this,AddPatientDetails.class);
            startActivity(intent);
        });



        String[] ListElements = new String[] {
                "Jonny",
                "Monty",
                "Vikki"
        };

        final List<String> ListElementsArrayList = new ArrayList<>(Arrays.asList(ListElements));
        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                (patientDetails.this, android.R.layout.simple_list_item_1, ListElementsArrayList);
        listview.setAdapter(adapter);



        if(!db.checkSpecial(loginId)) {
            int checkedItem = 1;
            new MaterialAlertDialogBuilder(patientDetails.this)
                    .setTitle("Specialization")
                    .setSingleChoiceItems(specializationArray, checkedItem, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            special = specializationArray[id];
                        }
                    })
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            try{
                            db.addSpecialties(loginId, special);
                            }catch(Exception e){
                                Toast.makeText(patientDetails.this, "error has occured while entering data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .show();
        }




    bottomAppBar.setNavigationOnClickListener(view -> {
      // set intent...
    });

    bottomAppBar.setOnMenuItemClickListener(menuItem -> {
        switch (menuItem.getItemId()) {
            case R.id.userProfile:
                Intent intent = new Intent(patientDetails.this,doctorProfile.class);
                intent.putExtra("login_id",loginId);
                startActivity(intent);
               return  true;
        }
        return true;
        });


    } //onCreate end...




//
//        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                if(list.contains(query)){
//                    adapter.getFilter().filter(query);
//                }else{
//                    Toast.makeText(patientDetails.this, "Match not found", Toast.LENGTH_SHORT).show();
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //    adapter.getFilter().filter(newText);
//                return false;
//            }
//        });





} // class end...