package models;

/**
 * Created by welcome on 1/4/2017.
 */
public class Surveys {
    private int sid;
    private String sname;
    private String sdescription;
    private String date;


    public Surveys() {
    }

    public Surveys(int sid, String sname, String sdescription, String date) {
        this.sid = sid;
        this.sname = sname;
        this.sdescription= sdescription;
        this.date = date;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSdescription() {
        return sdescription;
    }

    public void setSdescription(String sdescription) {
        this.sdescription= sdescription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
