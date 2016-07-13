package com.example.alphanese.ezsoc5;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by alphanese on 23/6/2016.
 */
public class DrEigthFragment extends Fragment {
    static String dateSelected;
    ArrayList<String> listOfDates = new ArrayList<String>();
    String url = "https://mysoc.nus.edu.sg/~calendar/getBooking.cgi?room=DR8";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dr_layout8, container, false);
        Button myButton = (Button) view.findViewById(R.id.button);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
                new chooseDate().execute();
            }
        });
        //((TextView) getView().findViewById(R.id.editText)).setText("hi");
        return view;
    }
    //This class obtains the 5 dates from the url itself
    private class chooseDate extends AsyncTask<Void, Void, Void> {
        String date1;
        String date2;
        String date3;
        String date4;
        String date5;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document document = Jsoup.connect(url).get();
                Element firstLink = document.select("a").first();
                Element secondLink = document.select("a").get(1);
                Element thirdLink = document.select("a").get(2);
                Element forthLink = document.select("a").get(3);
                Element fifthLink = document.select("a").get(4);

                String first = firstLink.attr("abs:href").substring(67,77); //If DR 10,11.. (68,78)
                date1= first;
                String second = secondLink.attr("abs:href").substring(67,77);
                date2= second;
                String third = thirdLink.attr("abs:href").substring(67,77);
                date3= third;
                String forth = forthLink.attr("abs:href").substring(67,77);
                date4= forth;
                String fifth = fifthLink.attr("abs:href").substring(67,77);
                date5= fifth;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void aVoid) {
            listOfDates.add(date1);
            listOfDates.add(date2);
            listOfDates.add(date3);
            listOfDates.add(date4);
            listOfDates.add(date5);
        }
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

            ((TextView) getActivity().findViewById(R.id.editText)).setText(year + " " + monthName + " " + day);
            Intent myIntent = new Intent(view.getContext(), popout.class);
            dateSelected = year + "/" + monthName + "/" + day;
            System.out.println(dateSelected + " test");
            myIntent.putExtra("day", day);
            myIntent.putExtra("month", monthName);
            myIntent.putExtra("year", Integer.toString(year));
            startActivityForResult(myIntent, 0);
        }
    }
}
