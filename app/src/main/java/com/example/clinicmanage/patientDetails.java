package com.example.clinicmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class patientDetails extends AppCompatActivity {

    ListView listview;
    SearchView searchview;
    ArrayList<String> list;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        listview = findViewById(R.id.listView);
        searchview = findViewById(R.id.searchView);


        String[] ListElements = new String[] {
                "Jonny",
                "Monty",
                "Vikki",
        };

        final List<String> ListElementsArrayList = new ArrayList<>(Arrays.asList(ListElements));
        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                (patientDetails.this, android.R.layout.simple_list_item_1, ListElementsArrayList);
        listview.setAdapter(adapter);

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
//



    } //onCreate end...
} // class end...