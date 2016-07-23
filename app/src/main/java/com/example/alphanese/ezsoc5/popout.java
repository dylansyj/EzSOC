package com.example.alphanese.ezsoc5;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class popout extends Activity {
    TableLayout tl;
    TableRow tr;
    TextView timeOfDay, available;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .8));
        tl = (TableLayout) findViewById(R.id.maintable);
        tl.setColumnStretchable(0, true);
        tl.setColumnStretchable(1, true);
        ArrayList<String> timings = getIntent().getStringArrayListExtra("timings");
        if(timings.get(0).equals("No bookings made.")){
            tr = new TableRow(this);
            timeOfDay = new TextView(this);
            available = new TextView(this);
            timeOfDay.setText("-");
            available.setText("No bookings made for the day");
            timeOfDay.setGravity(Gravity.CENTER);
            available.setGravity(Gravity.CENTER);
            tr.addView(timeOfDay);
            tr.addView(available);
            tl.addView(tr);
        }
        else {
            for (int i = 0; i < timings.size(); i++) {
                tr = new TableRow(this);
                timeOfDay = new TextView(this);
                available = new TextView(this);
                timeOfDay.setText(timings.get(i));
                timeOfDay.setTextSize(15);
                timeOfDay.setGravity(Gravity.CENTER);
                available.setText("Unavailable");
                available.setTextSize(15);
                available.setGravity(Gravity.CENTER);
                tr.addView(timeOfDay);
                tr.addView(available);
                tl.addView(tr);
            }
        }
    }
}
