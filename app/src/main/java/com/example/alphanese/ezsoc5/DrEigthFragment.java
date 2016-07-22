package com.example.alphanese.ezsoc5;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.PendingIntent;
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
public class DrEigthFragment extends Fragment {
    static String dateSelected;
    static ArrayList<String> listOfDates = new ArrayList<String>();
    static String url = "https://mysoc.nus.edu.sg/~calendar/getBooking.cgi?room=DR8";
    static String userSelectedUrl;
    static ArrayList <ArrayList<String>> listOfTimings = new ArrayList<ArrayList<String>>(); // contains a list of the arraylist of timings
    static ArrayList <String> listOfUrls = new ArrayList<String>();         // Contains the list of urls
    ProgressDialog PD;
    public static int year;
    public static int month;
    public static int day;
    public static String monthName;
    public static DatePicker view;

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

            }
        });
        return view;
    }
    //This class obtains all available timings
    private class Timings extends AsyncTask<Void, Void, Void> {
        String test;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected Void doInBackground(Void... voids) {
            for(int j=0 ; j < listOfUrls.size() ; j++) {
                try {
                    ArrayList<String> timings = new ArrayList<String>();
                    String currentUrl = listOfUrls.get(j);
                    Document document = Jsoup.connect(currentUrl).get();
                    Elements table = document.select("body > div > form > font > table ");
                    Elements firstContent = table.select("tbody> tr");
                    //If bookings are made the timings are extracted and placed into
                    //an arraylist called timings
                    if (!firstContent.text().equals("No bookings made.")) {
                        for (Element content : firstContent) {
                            String first = content.text();
                            timings.add(first.substring(0, 17));
                        }
                        listOfTimings.add(timings);
                    }
                    //else arraylist timings contains string of 'No bookings made.'
                    else {
                        timings.add("No bookings made.");
                        listOfTimings.add(timings);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
            System.out.println("For Testing day 1 : " + listOfTimings.get(0));
            System.out.println("For Testing day 2 : " + listOfTimings.get(1));
            System.out.println("For Testing day 3 : " + listOfTimings.get(2));
            System.out.println("For Testing day 4 : " + listOfTimings.get(3));
            System.out.println("For Testing day 5 : " + listOfTimings.get(4));

            //This is where u put in your intent for the listOfTimings to be brought over to popout
            //Intent is shifted here instead
            Intent myIntent = new Intent(getActivity(), popout.class);
            //For ur convenience
            myIntent.putExtra("listOfTimings", listOfTimings);
            //myIntent.putExtra("listOfDates", listOfDates);    //If u need
            myIntent.putExtra("day", day);
            myIntent.putExtra("month", monthName);
            myIntent.putExtra("year", Integer.toString(year));
            startActivityForResult(myIntent, 0);
        }
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
            PD = new ProgressDialog(getActivity());
            PD.setTitle("Please Wait..");
            PD.setMessage("Loading...");
            PD.setCancelable(false);
            PD.show();
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
            //Add all the url into an arraylist called listOfUrls
            for (int i = 0; i < listOfDates.size(); i++) {
                listOfUrls.add("https://mysoc.nus.edu.sg/~calendar/getBooking.cgi?room=DR8&thedate=" + listOfDates.get(i));
            }
            //After i am done obtaining the 5 urls, execute timings()
            new Timings().execute();

        }
    }

    public class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            month += 1;
            Toast.makeText(getContext(), "Date : " + day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            //TextView myTextView = (TextView) view.findViewById(R.id.editText);
            monthName = "Sup bitch";
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
            dateSelected = year + "/" + monthName + "/" + day;
            new chooseDate().execute();

            //Ur intent code is shifted to timings.execute() postpone
            /*
            Intent myIntent = new Intent(view.getContext(), popout.class);
            myIntent.putExtra("day", day);
            myIntent.putExtra("month", monthName);
            myIntent.putExtra("year", Integer.toString(year));
            startActivityForResult(myIntent, 0);
            */
        }

    }

}
