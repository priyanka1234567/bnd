package models;

/**
 * Created by welcome on 1/4/2017.
 */
public class Owner {
    private int oid;
    private String oname;
    private String pwd;
    private String eid;


    public Owner() {
    }

    public Owner(int oid, String oname, String pwd, String eid) {
        this.oid = oid;
        this.oname = oname;
        this.pwd = pwd;
        this.eid = eid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }
}
