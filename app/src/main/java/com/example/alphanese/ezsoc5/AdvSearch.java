package com.example.alphanese.ezsoc5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AdvSearch extends AppCompatActivity {
    Spinner spinner, spinner2, spinner3, spinner4, spinner5;
    ArrayAdapter<CharSequence> adapter, adapter2, adapter3, adapter4, adapter5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_search);
        spinner= (Spinner) findViewById(R.id.roomSelection);
        adapter= ArrayAdapter.createFromResource(this, R.array.room_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2=(Spinner) findViewById(R.id.TimeSelection1);
        adapter2= ArrayAdapter.createFromResource(this,R.array.timings_selection, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent2, View view2, int position2, long id2) {
//                Toast.makeText(getBaseContext(), parent2.getItemAtPosition(position2) + "selected", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner3=(Spinner) findViewById(R.id.TimeSelection2);
        adapter3= ArrayAdapter.createFromResource(this,R.array.timings_selection2, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent3, View view3, int position3, long id3) {
//                Toast.makeText(getBaseContext(), parent3.getItemAtPosition(position3) + "selected", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner4=(Spinner) findViewById(R.id.DaySelection);
        adapter4= ArrayAdapter.createFromResource(this,R.array.dates_selection_day, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        spinner4.setAdapter(adapter4);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent4, View view4, int position4, long id4) {
//                Toast.makeText(getBaseContext(), parent4.getItemAtPosition(position4) + "selected", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner5=(Spinner) findViewById(R.id.MonthSelection);
        adapter5= ArrayAdapter.createFromResource(this,R.array.dates_selection_month, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        spinner5.setAdapter(adapter5);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent5, View view5, int position5, long id5) {
//                Toast.makeText(getBaseContext(), parent5.getItemAtPosition(position5) + "selected ", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}

