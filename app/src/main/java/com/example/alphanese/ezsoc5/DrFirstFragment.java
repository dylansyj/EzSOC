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
            month += 1;
            Toast.makeText(getContext(), "Date : " + day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            //TextView myTextView = (TextView) view.findViewById(R.id.editText);
            String monthName = "Sup bitch";
            if (month == 1) {
                monthName = "01";
            } else if (month == 2) {
                monthName = "02";
            } else if (month == 3) {
                monthName = "03";
            } else if (month == 4) {
                monthName = "04";
            } else if (month == 5) {
                monthName = "05";
            } else if (month == 6) {
                monthName = "06";
            } else if (month == 7) {
                monthName = "07";
            } else if (month == 8) {
                monthName = "08";
            } else if (month == 9) {
                monthName = "09";
            } else if (month == 10) {
                monthName = "10";
            } else if (month == 11) {
                monthName = "11";
            } else if (month == 12) {
                monthName = "12";
            }

            ((TextView) getActivity().findViewById(R.id.editText)).setText(day + " " + monthName + " " + year);
            Intent myIntent = new Intent(view.getContext(), popout.class);
            myIntent.putExtra("day", day);
            myIntent.putExtra("month", monthName);
            myIntent.putExtra("year", Integer.toString(year));
            startActivityForResult(myIntent, 0);
        }
    }
}
