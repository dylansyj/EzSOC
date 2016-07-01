package com.example.alphanese.ezsoc5;

import android.app.Fragment;
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


public class dr extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr);
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
        getMenuInflater().inflate(R.menu.dr, menu);
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

        if (id == R.id.nav_dr_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DrFirstFragment()).commit();
        } else if (id == R.id.nav_dr_layout2) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DrSecondFragment()).commit();
        } else if (id == R.id.nav_dr_layout3) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DrThirdFragment()).commit();
        } else if (id == R.id.nav_dr_layout4) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DrForthFragment()).commit();
        } else if (id == R.id.nav_dr_layout5) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DrFifthFragment()).commit();
        } else if (id == R.id.nav_dr_layout6) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DrSixthFragment()).commit();
        }else if (id == R.id.nav_dr_layout7) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DrSeventhFragment()).commit();
        }
        else if (id == R.id.nav_dr_layout8) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DrEigthFragment()).commit();
        }
        else if (id == R.id.nav_dr_layout9) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DrNinthFragment()).commit();
        }
        else if (id == R.id.nav_dr_layout10) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DrTenthFragment()).commit();
        }
        else if (id == R.id.nav_dr_layout11) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DrEleventhFragment()).commit();
        }
        else if (id == R.id.nav_dr_layout12) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DrTwelvethFragment()).commit();
        }
        else if (id == R.id.nav_dr_layout13) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DrThirteenthFragment()).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
