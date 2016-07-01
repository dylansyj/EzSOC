package com.example.alphanese.ezsoc5;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by alphanese on 23/6/2016.
 */
public class TrFifteenthFragment extends Fragment {
    View myView3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView3 = inflater.inflate(R.layout.tr_layout15, container, false);
        return myView3;
    }
}
