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
        int day = getIntent().getIntExtra("day", 0);
        String month = getIntent().getStringExtra("month");
        String year = getIntent().getStringExtra("year");
        // ArrayList<String> dates = getIntent().get
        ((TextView) findViewById(R.id.textView3)).setText(day + " " + month + " " + year);
        int count = 0;
        ArrayList<RoomData> arrayList = new ArrayList<>();
        //insert extraction of data here
        //if extracting of data uses a while loop, just keep adding the data into the
        /*while( there's data){
        String str = entire string
        int len = str.length(); (determines the ending of the string, to be used to extract info)
        String time = str.substring(0,8);
        String info = str.substring(8,len);
        RoomData data1 = new RoomData(time, info);
        arrayList.add(data1);
        count++;
        }*/

        TableLayout stk = (TableLayout) findViewById(R.id.display);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Time ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Occupied ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        stk.addView(tbrow0);
        //count determines the number of rows
        for (int i = 0; i < count; i++) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(arrayList.get(i).getTime());
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(arrayList.get(i).getInfo());
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            stk.addView(tbrow);
        }
    }
}
