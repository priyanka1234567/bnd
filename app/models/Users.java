package models;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
/**
 * Created by pdevkare on 07/01/17.
 */
@Entity
public class Users {





    @Id
    @Basic
    private String uname;

    @Basic
    private String upwd;

    @Basic
    private String salt ;



    @Basic
    private String Token;





    public Users( String uname, String upwd, String salt, String token, List<Features> features) {

        this.uname = uname;
        this.upwd = upwd;
        this.salt = salt;
        Token = token;

    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}

