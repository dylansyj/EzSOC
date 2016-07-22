package com.example.alphanese.ezsoc5;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dylan on 13/7/2016.
 */
public class MyDate {

    private static MyDate myDate = new MyDate();
    private MyDate(){}

    private int day;
    private int month;
    private int year;

    public static MyDate getInstance() {
        return myDate;
    }
    public int getDay(){
        return day;
    }
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }
    public void setDay (int day1){
        day = day1;
    }
    public void setMonth (int month1){
        month = month1;
    }
    public void setYear(int year1){
        year = year1;
    }

    //methods
    public void previousDay(){
        if(day>0)
            day -= 1;
        else if(day==0 && month >1){ // January == 0
            Calendar c = Calendar.getInstance();
            c.set(year,month-2,1);
            c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
            Date date = c.getTime();
            DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
            String temp = DATE_FORMAT.format(date);
            int day1 = Integer.parseInt(temp.substring(0,1));
            day = day1;
        }
        else if(month>1){
            month -= 1;
        }
        else{
            year -= 1;
        }
    }
    public void minusDate(){
        day -= 1;
    }
    public void nextDay(){
        String dateString;
        if(month<10) {
            dateString = day + "/" + "0" + month + "/" + year;
        }
        else {
            dateString = day + "/" + month + "/" + year;
        }
    }
    public String printDate() {
        String date = day + "/" + month + "/" + year;
        return date;
    }
}
