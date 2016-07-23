package com.example.alphanese.ezsoc5;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ProgressDialog;
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
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by alphanese on 23/6/2016.
 */
public class DrThirdFragment extends Fragment {
    static String dateSelected;
    View myView;
    String url = "https://mysoc.nus.edu.sg/~calendar/getBooking.cgi?room=DR3";
    static ProgressDialog PD;
    public static int year;
    public static int month;
    public static int day;
    public static String monthName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dr_layout3, container, false);
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

    public class DatePickerFragment extends DialogFragment
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

        public void onDateSet(DatePicker view, int Year, int Month, int Day) {
            month += 1;
            Toast.makeText(getContext(), "Date : " + Day + "/" + Month + "/" + Year, Toast.LENGTH_LONG).show();
            //TextView myTextView = (TextView) view.findViewById(R.id.editText);
            monthName = "Sup bitch";
            if (Month == 1) {
                monthName = "01";
            } else if (Month == 2) {
                monthName = "02";
            } else if (Month == 3) {
                monthName = "03";
            } else if (Month == 4) {
                monthName = "04";
            } else if (Month == 5) {
                monthName = "05";
            } else if (Month == 6) {
                monthName = "06";
            } else if (Month == 7) {
                monthName = "07";
            } else if (Month == 8) {
                monthName = "08";
            } else if (Month == 9) {
                monthName = "09";
            } else if (Month == 10) {
                monthName = "10";
            } else if (Month == 11) {
                monthName = "11";
            } else if (Month == 12) {
                monthName = "12";
            }
            day = Day;  year = Year;
            ((TextView) getActivity().findViewById(R.id.editText)).setText(day + " " + monthName + " " + year);
            dateSelected = year + "/" + monthName + "/" + day;
            System.out.println("date selected is :" + dateSelected);
            new Timings().execute();
           /* Intent myIntent = new Intent(view.getContext(), popout.class);
            myIntent.putExtra("day", day);
            myIntent.putExtra("month", monthName);
            myIntent.putExtra("year", Integer.toString(year));
            startActivityForResult(myIntent, 0);*/
        }
    }
    //This class obtains all available timings
    private class Timings extends AsyncTask<Void, Void, Void> {
        String test;
        ArrayList<String> timings = new ArrayList<String>();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PD = new ProgressDialog(getActivity());
            PD.setTitle("Please Wait..");
            PD.setMessage("Loading...");
            PD.setCancelable(false);
            PD.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String currentUrl = "https://mysoc.nus.edu.sg/~calendar/getBooking.cgi?room=DR3&thedate="+dateSelected;
                Document document = Jsoup.connect(currentUrl).get();
                Elements table = document.select("body > div > form > font > table ");
                Elements firstContent = table.select("tbody> tr");
                //If bookings are made the timings are extracted and placed into
                //an arraylist called timings
                System.out.println("at fragment class:" +firstContent.text());
                if (!firstContent.text().equals("No bookings made.")) {
                    for (Element content : firstContent) {
                        String first = content.text();
                        timings.add(first.substring(0, 17));
                    }
                }
                //else arraylist timings contains string of 'No bookings made.'
                else {
                    timings.add("No bookings made.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            PD.dismiss();
            System.out.println("Testing for Date " + day + " " + monthName + " " + year);
            /*for(int k=0 ; k < listOfTimings.size(); k ++) {
                System.out.println( "Current Date is " + listOfDates.get(k) + " and timings for this date is " + listOfTimings.get(k));
            }*/

            //This is where u put in your intent for the listOfTimings to be brought over to popout
            //Intent is shifted here instead
            Intent myIntent = new Intent(getActivity(), popout.class);
            //For ur convenience
            //myIntent.putExtra("listOfDates", listOfDates);    //If u need
            System.out.println("at fragment class: "+ timings.get(0));
            myIntent.putExtra("day", day);
            myIntent.putExtra("month", monthName);
            myIntent.putExtra("year", Integer.toString(year));
            myIntent.putExtra("timings", timings);
            startActivityForResult(myIntent, 0);
        }
    }
}
