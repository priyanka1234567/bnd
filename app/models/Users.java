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

    public Users(int uid, String uname, String upwd) {
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
    }

}

