package com.example.alphanese.ezsoc5;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class sr extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sr, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.nav_sr_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SrFirstFragment()).commit();
        } else if (id == R.id.nav_sr_layout2) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SrSecondFragment()).commit();
        } else if (id == R.id.nav_sr_layout3) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SrThirdFragment()).commit();
        } else if (id == R.id.nav_sr_layout4) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SrForthFragment()).commit();
        } else if (id == R.id.nav_sr_layout5) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SrFifthFragment()).commit();
        } else if (id == R.id.nav_sr_layout6) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SrSixthFragment()).commit();
        } else if (id == R.id.nav_sr_layout7) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SrSeventhFragment()).commit();
        } else if (id == R.id.nav_sr_layout8) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SrEigthFragment()).commit();
        } else if (id == R.id.nav_sr_layout9) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SrNinthFragment()).commit();
        } else if (id == R.id.nav_sr_layout10) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SrTenthFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
