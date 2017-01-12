package models;

import java.io.File;
import java.sql.Time;


public class Surveyors {


    private int eid;

    private String name;
    private double latitude;
    private double longitude;
    private String landmark;
    private Time openTime;
    private Time closeTime;
    private String history;
    private String imagePath;

    public Surveyors() {
    }

    public Surveyors(int eid, String name, double latitude, double longitude, String landmark, Time openTime, Time closeTime,String history, String imagePath) {
        this.eid=eid;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
       this.landmark=landmark;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.history = history;
        this.imagePath = imagePath;

    }



    public int getEid() {
        return eid;
    }

    public void setEid(String name) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public Time getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }


    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }


    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public  String getHistory(){ return history;}

    public void setHistory(String history){this.history=history;}


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }




}
