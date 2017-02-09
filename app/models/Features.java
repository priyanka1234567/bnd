package models;

import javax.persistence.*;


@Entity
public class Features  {

    @Id
    @GeneratedValue
    private int fid;
    @Basic
    private String name;

    @Basic
    private double latitude;
    @Basic
    private double longitude;
    @Basic
    private String country;

    @Basic
    private String state;
    @Basic
    private String district;
    @Basic
    private String deities;
    @Basic
    private String festivals;
    @Basic
    private String archstyle;
    @Basic
    private String datebuilt;
    @Basic
    private String creator;
    @Basic
    private String guides;
    @Basic
    private String eateries;
    public Features(){}

    public Features(int fid, String name, double latitude, double longitude, String country, String state, String district, String deities, String festivals, String archstyle, String datebuilt, String creator, String tourists, String guides, String eateries) {
        this.fid = fid;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.state = state;
        this.district = district;
        this.deities = deities;
        this.festivals = festivals;
        this.archstyle = archstyle;
        this.datebuilt = datebuilt;
        this.creator = creator;
        this.guides = guides;
        this.eateries = eateries;
    }


    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDeities() {
        return deities;
    }

    public void setDeities(String deities) {
        this.deities = deities;
    }

    public String getFestivals() {
        return festivals;
    }

    public void setFestivals(String festivals) {
        this.festivals = festivals;
    }

    public String getArchstyle() {
        return archstyle;
    }

    public void setArchstyle(String archstyle) {
        this.archstyle = archstyle;
    }

    public String getDatebuilt() {
        return datebuilt;
    }

    public void setDatebuilt(String datebuilt) {
        this.datebuilt = datebuilt;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEateries() {
        return eateries;
    }

    public void setEateries(String eateries) {
        this.eateries = eateries;
    }

    public String getGuides() {
        return guides;
    }

    public void setGuides(String guides) {
        this.guides = guides;
    }
}
