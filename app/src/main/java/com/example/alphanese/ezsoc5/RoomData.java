package com.example.alphanese.ezsoc5;

/**
 * Created by Dylan on 22/7/2016.
 */

//stores 3 variables drawn from the extracted data, for output to user
public class RoomData {
    private String time;
    private String info;

    public RoomData(String time, String info){
        this.time = time;
        this.info = info;
    }
    public String getTime(){
        return time;
    }
    public String getInfo(){
        return info;
    }

    public void setData(String tempTime, String tempInfo){
        time = tempTime;
        info = tempInfo;
    }
}
