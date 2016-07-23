package com.example.alphanese.ezsoc5;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by alphanese on 23/6/2016.
 */

public class SrFirstFragment extends Fragment {
    //Following Url is for comparison purpose onli
    static int periodValue;
    static String periodString;
    String defaultUrl1 = "http://wrbtf.nus.edu.sg:8000/reporting/master?objectclass=location&idtype=id&identifier=39/" +
            "COM1-0206&t=SWSCUST+location+master&days=1-7&weeks=";
    String defaultUrl2="&periods=1-34&template=SWSCUST+location+master";
    static Date userSelectedDate = new Date();
    static DateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
    ArrayList<String> monday = new ArrayList<String>();
    ArrayList<String>  tuesday = new ArrayList<String>();
    ArrayList<String>  wednesday = new ArrayList<String>();
    ArrayList<String>  thursday = new ArrayList<String>();
    ArrayList<String>  friday = new ArrayList<String>();
    ArrayList<String>  saturday = new ArrayList<String>();
    ArrayList<String>  sunday = new ArrayList<String>();
    ArrayList<ArrayList<String>> week = new ArrayList<ArrayList<String>>();
    ArrayList<String> timings = new ArrayList<>();
    public static int globalYear;
    public static int globalMonth;
    public static int globalDay;
    public static String globalMonthName;
    Calendar theDate = Calendar.getInstance();
    int dayOfWeek;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sr_layout, container, false);
        Button myButton = (Button) view.findViewById(R.id.button);

        timings.add("6:00");
        timings.add("6:30");
        timings.add("7:00");
        timings.add("7:30");
        timings.add("8:00");
        timings.add("8:30");
        timings.add("9:00");
        timings.add("9:30");
        timings.add("10:00");
        timings.add("10:30");
        timings.add("11:00");
        timings.add("11:30");
        timings.add("12:00");
        timings.add("12:30");
        timings.add("13:00");
        timings.add("13:30");
        timings.add("14:00");
        timings.add("14:30");
        timings.add("15:00");
        timings.add("15:30");
        timings.add("16:00");
        timings.add("16:30");
        timings.add("17:00");
        timings.add("17:30");
        timings.add("18:00");
        timings.add("18:30");
        timings.add("19:00");
        timings.add("19:30");
        timings.add("20:00");
        timings.add("20:30");
        timings.add("21:00");
        timings.add("21:30");
        timings.add("22:00");
        timings.add("22:30");

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
            globalDay = day; globalMonthName = monthName; globalYear = year;
            ((TextView) getActivity().findViewById(R.id.editText)).setText(day + " " + monthName + " " + year);
            String selectedDate = year + "/" + monthName + "/" + day;
            System.out.println("selectedDate is:" + selectedDate);
            try {
                userSelectedDate = ft.parse(selectedDate) ;
                theDate.setTime(userSelectedDate);
                dayOfWeek= theDate.get(Calendar.DAY_OF_WEEK);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("numeric value of day of week is :" + dayOfWeek);
            //Do not erase the following: yyyy , mm , dd
            //              After               Before
            //First week: 2016, 07, 31      2016, 08, 08
            //1st   week: 2016, 08, 07      2016, 08, 15
            //2nd   week: 2016, 08, 14      2016, 08, 22
            //3rd   week: 2016, 08, 21      2016, 08, 29
            //4th   week: 2016, 08, 28      2016, 09, 05
            //5th   week: 2016, 09, 04      2016, 09, 12
            //6th   week: 2016, 09, 11      2016, 09, 19
            //7th   week: 2016, 09, 18      2016, 09, 26
            //8th   week: 2016, 09, 25      2016, 10, 03
            //9th   week: 2016, 10, 02      2016, 10, 10
            //10th  week: 2016, 10, 09      2016, 10, 17
            //11th  week: 2016, 10, 16      2016, 10, 24
            //12th  week: 2016, 10, 23      2016, 10, 31
            //13th  week: 2016, 10, 30      2016, 11, 07
            //14th  week: 2016, 11, 06      2016, 11, 14
            //15th  week: 2016, 11, 14      2016, 11, 21
            //16th  week: 2016, 11, 21      2016, 11, 28
            //17th  week: 2016, 11, 27      2016, 12, 05
            //18th  week: 2016, 12, 04      2016, 12, 12
            //19th  week: 2016, 12, 11      2016, 12, 19
            //20th  week: 2016, 12, 18      2016, 12, 26
            //21st  week: 2016, 12, 25      2016, 01, 03

            Date initialAfter = null;
            try {
                initialAfter = ft.parse("2016/07/31");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date initialBefore = null;
            try {
                initialBefore = ft.parse("2016/08/08");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar beforeI = Calendar.getInstance();
            Calendar afterI = Calendar.getInstance();
            beforeI.setTime(initialBefore);
            afterI.setTime(initialAfter);
            Calendar chosenDate = Calendar.getInstance();
            chosenDate.setTime(userSelectedDate);
            periodValue=0;
            System.out.println(beforeI.getTime());
            System.out.println(afterI.getTime());
            System.out.println(chosenDate.getTime());
            //System.out.println(chosenDate);
            while(chosenDate.after(afterI)) {
                if(chosenDate.before(beforeI)) {
                    periodValue++;
                    break;
                }
                else {
                    beforeI.add(Calendar.DATE, 7);
                   // afterI.add(Calendar.DATE, 7);
                    periodValue++;
                }
            }
            System.out.println("periodValue is :" + periodValue);
            periodString = Integer.toString(periodValue);
            new Testing().execute();
        }
    }
    private class Testing extends AsyncTask<Void, Void, Void> {
        String test;
        ProgressDialog PD;
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
                System.out.println(defaultUrl1+periodString+defaultUrl2);
                Document document = Jsoup.connect(defaultUrl1+periodString+defaultUrl2).get();
                Elements first = document.select("body > table:nth-child(2) > tbody > tr:nth-child(2)");
                Elements second = document.select("body > table:nth-child(2) > tbody > tr");

                Elements testing = second.select("td");
                monday.clear(); tuesday.clear(); wednesday.clear(); thursday.clear(); friday.clear();
                saturday.clear(); sunday.clear();
                week.add(monday);
                week.add(tuesday);
                week.add(wednesday);
                week.add(thursday);
                week.add(friday);
                week.add(saturday);
                week.add(sunday);
                int count = 0;
                //247 is where 39/..... is always at so i should start off from 248
                //Use col span from html
                //E.g. 396
                //System.out.println(testing.get(396).attr("colspan"));
                // System.out.println(testing.get(396).text());

                System.out.println("Size is:" + testing.size());
                int dayNavigator = 0;
                int timeNavigator = 0;
                for (int j = 248; j < testing.size(); j++) {
                    // System.out.println("j is:" + j + " stuff is: "+ testing.get(j));
                    //248 is 6 am for monday
                    //I need a new variable to move ard my timings, i have 34 stuffs stored timings
                    //Need to think of idea to shift ard my timings
                    if (testing.get(j).text().equals(" ")) { //If what i find is #nbsp
                        while (testing.get(j).text().equals(" ")) {
                            timeNavigator++;
                            j++;
                            //System.out.println("time navigator is:" + timeNavigator);
                            //  System.out.println("j is:" + j);
                            if (timeNavigator >= 33) {
                                timeNavigator = 0;
                                dayNavigator++;
                                break;
                            }
                            if (j >= testing.size() - 1) {
                                break;
                            }
                        }
                        if (!testing.get(j).text().equals(" ")) { //Means a description is found
                            if (testing.get(j).attr("colspan").equals("2")) {
                                if (timeNavigator + 2 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 2);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 2;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("3")) {
                                if (timeNavigator + 3 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 3);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 3;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("4")) {
                                if (timeNavigator + 4 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 4);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 4;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("5")) {
                                if (timeNavigator + 5 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 5);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 5;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("6")) {
                                if (timeNavigator + 6 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 6);
                                    //  System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 6;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("7")) {
                                if (timeNavigator + 7 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 7);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 7;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("8")) {
                                if (timeNavigator + 8 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 8);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 8;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("9")) {
                                if (timeNavigator + 9 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 9);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 9;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("10")) {
                                if (timeNavigator + 10 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 10);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 10;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("11")) {
                                if (timeNavigator + 11 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 11);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 11;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("12")) {
                                if (timeNavigator + 12 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 12);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 12;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("13")) {
                                if (timeNavigator + 13 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 13);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 13;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("14")) {
                                if (timeNavigator + 14 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 14);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 14;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("15")) {
                                if (timeNavigator + 15 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 15);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 15;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("16")) {
                                if (timeNavigator + 16 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 16);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 16;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("17")) {
                                if (timeNavigator + 17 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 17);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 17;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("18")) {
                                if (timeNavigator + 18 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 18);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 18;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("19")) {
                                if (timeNavigator + 19 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 19);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 19;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("20")) {
                                if (timeNavigator + 20 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 20);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 20;
                                    j = j + 4;    //to move the get(j)
                                    System.out.println("at 20:" + timings.get(timeNavigator));
                                    System.out.println("at 20:" + timings.get(timeNavigator + 20));
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("21")) {
                                if (timeNavigator + 21 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 21);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 21;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("22")) {
                                if (timeNavigator + 22 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 22);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 22;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("23")) {
                                if (timeNavigator + 23 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 23);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 23;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("24")) {
                                if (timeNavigator + 24 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 24);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 24;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("25")) {
                                if (timeNavigator + 25 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 25);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 25;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("26")) {
                                if (timeNavigator + 26 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 26);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 26;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("27")) {
                                if (timeNavigator + 27 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 27);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 27;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("28")) {
                                if (timeNavigator + 28 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 28);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 28;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("29")) {
                                if (timeNavigator + 29 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 29);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 29;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("30")) {
                                if (timeNavigator + 30 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 30);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 30;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("31")) {
                                if (timeNavigator + 31 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 31);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 31;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            } else if (testing.get(j).attr("colspan").equals("32")) {
                                if (timeNavigator + 32 < 34) {
                                    String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 32);
                                    // System.out.println("String added:" + requiredTimings);
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = timeNavigator + 32;
                                    j = j + 4;    //to move the get(j)
                                } else {
                                    String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                    week.get(dayNavigator).add(requiredTimings);
                                    timeNavigator = 0;
                                    j = j + 4;
                                    dayNavigator++;
                                }
                            }
                        }
                    } else { //It has sth inside
                        if (testing.get(j).attr("colspan").equals("2")) {
                            if (timeNavigator + 2 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 2);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 2;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("3")) {
                            if (timeNavigator + 3 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 3);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 3;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("4")) {
                            if (timeNavigator + 4 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 4);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 4;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("5")) {
                            if (timeNavigator + 5 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 5);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 5;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("6")) {
                            if (timeNavigator + 6 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 6);
                                //  System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 6;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("7")) {
                            if (timeNavigator + 7 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 7);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 7;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("8")) {
                            if (timeNavigator + 8 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 8);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 8;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("9")) {
                            if (timeNavigator + 9 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 9);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 9;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("10")) {
                            if (timeNavigator + 10 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 10);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 10;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("11")) {
                            if (timeNavigator + 11 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 11);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 11;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("12")) {
                            if (timeNavigator + 12 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 12);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 12;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("13")) {
                            if (timeNavigator + 13 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 13);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 13;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("14")) {
                            if (timeNavigator + 14 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 14);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 14;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("15")) {
                            if (timeNavigator + 15 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 15);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 15;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("16")) {
                            if (timeNavigator + 16 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 16);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 16;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("17")) {
                            if (timeNavigator + 17 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 17);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 17;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("18")) {
                            if (timeNavigator + 18 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 18);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 18;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("19")) {
                            if (timeNavigator + 19 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 19);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 19;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("20")) {
                            if (timeNavigator + 20 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 20);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 20;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("21")) {
                            if (timeNavigator + 21 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 21);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 21;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("22")) {
                            if (timeNavigator + 22 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 22);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 22;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("23")) {
                            if (timeNavigator + 23 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 23);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 23;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("24")) {
                            if (timeNavigator + 24 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 24);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 24;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("25")) {
                            if (timeNavigator + 25 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 25);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 25;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("26")) {
                            if (timeNavigator + 26 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 26);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 26;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("27")) {
                            if (timeNavigator + 27 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 27);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 27;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("28")) {
                            if (timeNavigator + 28 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 28);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 28;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("29")) {
                            if (timeNavigator + 29 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 29);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 29;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("30")) {
                            if (timeNavigator + 30 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 30);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 30;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("31")) {
                            if (timeNavigator + 31 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 31);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 31;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        } else if (testing.get(j).attr("colspan").equals("32")) {
                            if (timeNavigator + 32 < 34) {
                                String requiredTimings = timings.get(timeNavigator) + " " + timings.get(timeNavigator + 32);
                                // System.out.println("String added:" + requiredTimings);
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = timeNavigator + 32;
                                j = j + 4;    //to move the get(j)
                            } else {
                                String requiredTimings = timings.get(timeNavigator) + " " + "0:00";
                                week.get(dayNavigator).add(requiredTimings);
                                timeNavigator = 0;
                                j = j + 4;
                                dayNavigator++;
                            }
                        }
                    }
                    if (timeNavigator >= 34) {
                        dayNavigator++;
                        timeNavigator = 0;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            PD.dismiss();
            System.out.println("Monday: " + week.get(0));
            System.out.println("Tuesday: " + week.get(1));
            System.out.println("Wednesday: " + week.get(2));
            System.out.println("Thursday: " + week.get(3));
            System.out.println("Friday: " + week.get(4));
            System.out.println("Saturday: " + week.get(5));
            System.out.println("Sunday: " + week.get(6));
            Intent myIntent = new Intent(getActivity(), popout.class);
           // System.out.println()
            //dayOfWeek, sunday is 1, monday is 2 and so on till saturday is 7
            if(dayOfWeek==1) { //sunday
                myIntent.putExtra("timings", week.get(6));
            }
            else if(dayOfWeek==2) { //monday
                myIntent.putExtra("timings", week.get(0));
            }
            else if (dayOfWeek==3){
                myIntent.putExtra("timings", week.get(1));
            }
            else if(dayOfWeek==4) {
                myIntent.putExtra("timings", week.get(2));
            }
            else if(dayOfWeek==5) {
                myIntent.putExtra("timings", week.get(3));
            }
            else if(dayOfWeek==6) {
                myIntent.putExtra("timings", week.get(4));
            }
            else if(dayOfWeek==7) {
                myIntent.putExtra("timings", week.get(5));
            }
            System.out.println("Dates are: " + globalDay + " " + globalMonthName + " " + globalYear);
            myIntent.putExtra("day", globalDay);
            myIntent.putExtra("month", globalMonthName);
            myIntent.putExtra("year", Integer.toString(globalYear));
            startActivityForResult(myIntent, 0);

        }
    }
}
