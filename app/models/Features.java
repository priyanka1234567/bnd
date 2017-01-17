package models;

import java.io.File;
import java.sql.Time;
import javax.persistence.*;
import java.util.List;


@Entity
public class Features {

    @Id
    @GeneratedValue
    private int eid;
    @Basic
    private String name;
    @Basic
    private double latitude;
    @Basic
    private double longitude;
    @Basic
    private String landmark;
    @Basic

    private String history;
    @Basic
    private String imagePath;

    public Features() {
    }

    public Features(int eid, String name, double latitude, double longitude, String landmark,  String history, String imagePath) {
        this.eid=eid;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.landmark=landmark;
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
