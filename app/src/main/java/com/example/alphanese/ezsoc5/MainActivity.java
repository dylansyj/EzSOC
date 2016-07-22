package com.example.alphanese.ezsoc5;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button  AdvButton;
    Button  AvailButton;
    ImageButton DRButton;
    ImageButton SRButton;
    ImageButton TRButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdvButton = (Button) findViewById(R.id.AdvButton);
        AdvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Advanced Search is clicked", Toast.LENGTH_LONG).show();
                Intent AdvInten = new Intent(MainActivity.this, AdvSearch.class);
                MainActivity.this.startActivity(AdvInten);
            }
        });
        AvailButton = (Button) findViewById(R.id.AvailButton);
        AvailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Availabiltiy Search is clicked", Toast.LENGTH_LONG).show();
                Intent AvailInten = new Intent(MainActivity.this, AvailSearch.class);
                MainActivity.this.startActivity(AvailInten);
            }
        });
        DRButton = (ImageButton) findViewById(R.id.DRimage);
        DRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "DR is clicked", Toast.LENGTH_LONG).show();
                Intent DRintent  = new Intent(MainActivity.this, dr.class);
                MainActivity.this.startActivity(DRintent);

            }
        });
        SRButton = (ImageButton) findViewById(R.id.SRimage);
        SRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "SR is clicked", Toast.LENGTH_LONG).show();
                Intent SRintent = new Intent(MainActivity.this, sr.class);
                MainActivity.this.startActivity(SRintent);
            }
        });
        TRButton = (ImageButton) findViewById(R.id.TRimage);
        TRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "TR is clicked", Toast.LENGTH_LONG).show();
                Intent TRintent = new Intent(MainActivity.this, tr.class);
                MainActivity.this.startActivity(TRintent);
            }
        });
    }
}
