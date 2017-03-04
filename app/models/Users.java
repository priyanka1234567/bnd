package models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by pdevkare on 07/01/17.
 */
@Entity
public class Users {



    @Id
    @GeneratedValue

    private int uid;

    @Basic
    private String uname;
    @Basic
    private String upwd;

    @Basic
    private  String salt;

    @Basic
    private  String num;

//    @OneToMany
//    @JoinColumn(name="u_id", referencedColumnName = "uid")
//    private List<Surveys> survey1;

//    @OneToMany(mappedBy = "Users", cascade=CascadeType.ALL)
//    public List<Surveys>  survey = new ArrayList<Surveys>();


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public Users() {
    }

    public Users(int uid, String uname, String upwd,String salt,String  num) {
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
        this.salt=salt;
        this.num=num;

    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}

