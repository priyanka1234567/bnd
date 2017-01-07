package models;

import java.io.File;
import java.sql.Time;


public class Restaurant {

    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private String cuisine;
    private int cost;
    private Time openTime;
    private Time closeTime;
    private String highlights;
    private String photosPath;
    private int oid;

    public Restaurant() {
    }

    public Restaurant(int id, String name, double latitude, double longitude, String cuisine, int cost, Time openTime, Time closeTime, String highlights, String photosPath, int oid) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cuisine = cuisine;
        this.cost = cost;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.highlights = highlights;
        this.photosPath = photosPath;
        this.oid = oid;
    }


    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
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

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getPhotosPath() {
        return photosPath;
    }

    public void setPhotosPath(String photosPath) {
        this.photosPath = photosPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }
}
