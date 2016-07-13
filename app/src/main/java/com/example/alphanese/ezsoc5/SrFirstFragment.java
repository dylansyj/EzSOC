package com.example.alphanese.ezsoc5;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by alphanese on 23/6/2016.
 */
public class SrFirstFragment extends Fragment {
    View myView2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView2 = inflater.inflate(R.layout.sr_layout, container, false);
        return myView2;
    }
}
