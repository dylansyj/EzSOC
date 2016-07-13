package com.example.alphanese.ezsoc5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class popout extends Activity {
    Button backButton;
    Button forwardButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .8));
        int day = getIntent().getIntExtra("day",0);
        String month = getIntent().getStringExtra("month");
        String year = getIntent().getStringExtra("year");

        ((TextView) findViewById(R.id.textView3)).setText(day + " " + month + " " + year);
        View v = new View(this);
        v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
        TableRow row;
        row = (TableRow) findViewById(R.id.tableRow1);
        row.addView(v);

        //below requires availability string to be determined by data extracted
        // remove comments indication below after creation of strings as stated on the abv comment
        /*((EditText) findViewById(R.id.availText01)).setText(availability0); //availability1 indicated by data from server, available or unavailable
        ((EditText) findViewById(R.id.availText)).setText(availability1);
        ((EditText) findViewById(R.id.availText2)).setText(availability2);
        ((EditText) findViewById(R.id.availText3)).setText(availability3);
        ((EditText) findViewById(R.id.availText4)).setText(availability4);
        ((EditText) findViewById(R.id.availText5)).setText(availability5);
        ((EditText) findViewById(R.id.availText6)).setText(availability6);
        ((EditText) findViewById(R.id.availText7)).setText(availability7);
        ((EditText) findViewById(R.id.availText8)).setText(availability8);
        ((EditText) findViewById(R.id.availText9)).setText(availability9);
        ((EditText) findViewById(R.id.availText10)).setText(availability10);
        ((EditText) findViewById(R.id.availText11)).setText(availability11);
        ((EditText) findViewById(R.id.availText12)).setText(availability12);
        ((EditText) findViewById(R.id.availText13)).setText(availability13);
        */

        backButton = (Button) findViewById(R.id.button5);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent nextDate = new Intent(view.getContext(), popout.class);
                nextDate.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(nextDate,1);
            }
        });
        forwardButton = (Button) findViewById(R.id.button6);
        forwardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent nextDate = new Intent(view.getContext(), popout.class);
                nextDate.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(nextDate,1);
            }
        });
    }
}
