package com.example.alphanese.ezsoc5;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class popout extends Activity {
    TableLayout tl;
    TableRow tr;
    TextView timeOfDay, available;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popout);
        /*DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .8));
        */
        tl = (TableLayout) findViewById(R.id.maintable);
        tl.setColumnStretchable(0, true);
        tl.setColumnStretchable(1, true);
        ArrayList<String> timings = getIntent().getStringArrayListExtra("timings");
        for(int i = 0 ; i < timings.size(); i++){
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //function adds Headers to table
    /**public void addHeaders() {
        //creating a TableRow dynamically
        tr = new TableRow(this);
        tr.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //adding textview to row
        timeOfDay = new TextView(this);
        timeOfDay.setText("Time");
        timeOfDay.setTextColor(Color.BLACK);
        timeOfDay.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        timeOfDay.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        timeOfDay.setPadding(5, 5, 5, 0);
        tr.addView(timeOfDay);

        available = new TextView(this);
        available.setText("");
        available.setTextColor(Color.GRAY);
        available.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        available.setPadding(5, 5, 5, 0);
        available.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(available);
        // Adding textView to tablerow.
        tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        tr = new TableRow(this);
        tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        /** Creating another textview
        TextView divider = new TextView(this);
        divider.setText("-----------------");
        divider.setTextColor(Color.GREEN);
        divider.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        divider.setPadding(5, 0, 0, 0);
        divider.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider); // Adding textView to tablerow.

        TextView divider2 = new TextView(this);
        divider2.setText("-------------------------");
        divider2.setTextColor(Color.GREEN);
        divider2.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        divider2.setPadding(5, 0, 0, 0);
        divider2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider2); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
    }

    public void addData(){
        ArrayList<String> timings = getIntent().getStringArrayListExtra("timings");
        // ArrayList<String> dates = getIntent().get
        //((TextView) findViewById(R.id.textView3)).setText(day + " " + month + " " + year);
        int count = 0;
        System.out.println("at pop out:" + timings.get(0));
        if (timings.get(0).equals("No bookings made.")) {
            System.out.println("Lol");
        }
        else {
            for(int i = 0; i < timings.size();i++){
                tr = new TableRow(this);
                tr.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                timeOfDay = new TextView(this);
                timeOfDay.setText(timings.get(i));
                timeOfDay.setTextColor(Color.RED);
                timeOfDay.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                timeOfDay.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                timeOfDay.setPadding(5,5,5,5);
                tr.addView(timeOfDay);
                /** Creating another textview
                available = new TextView(this);
                available.setText("Unavailable");
                available.setTextColor(Color.GREEN);
                available.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                available.setPadding(5, 5, 5, 5);
                available.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                tr.addView(available); // Adding textView to tablerow.

                // Add the TableRow to the TableLayout
                tl.addView(tr, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }
        }
    }**/

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "popout Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.alphanese.ezsoc5/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "popout Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.alphanese.ezsoc5/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
