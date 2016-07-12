package com.example.alphanese.ezsoc5;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by alphanese on 23/6/2016.
 */

public class DrFirstFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dr_layout, container, false);
        Button myButton = (Button) view.findViewById(R.id.button);
        Button b = (Button) view.findViewById(R.id.button5);

        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(),popout.class));
            }
        });
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });
        //((TextView) getView().findViewById(R.id.editText)).setText("hi");

        return view;
    }
    public void defaultDate() {
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        String dateString = sdf.format(date);
        ((TextView) getActivity().findViewById(R.id.editText)).setText(dateString);
    }
    /*private Button test;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
    public void onCreateView(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.dr_layout);
        test = (Button) get.findViewById(R.id.button5);
        relativeLayout = (RelativeLayout) getView().findViewById(R.id.relative);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.activity_popout,null);

                popupWindow = new PopupWindow(container,800,800,true);
                popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY,500,500);
                container.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent){
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });
    }*/

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            month +=1;
            Toast.makeText(getContext(), "Date : " + day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            //TextView myTextView = (TextView) view.findViewById(R.id.editText);
            String monthName = "Sup bitch";
            if (month == 1){
                monthName = "Jan";
            } else if(month == 2) {
                monthName = "Feb";
            } else if(month == 3){
                    monthName = "Mar";
            } else if(month == 4){
                monthName = "Apr";
            } else if(month == 5){
                monthName = "May";
            } else if(month == 6){
                monthName = "Jun";
            } else if(month == 7) {
                monthName = "Jul";
            } else if(month == 8){
                monthName = "Aug";
            } else if(month == 9){
                monthName = "Sep";
            } else if(month == 10){
                monthName = "Oct";
            } else if(month == 11){
                monthName = "Nov";
            } else if(month == 12){
                monthName = "Dec";
            }
            ((TextView) getActivity().findViewById(R.id.editText)).setText(day + " " + monthName + " " + year);
            
        }
    }
}
