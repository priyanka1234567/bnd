package services;



        import com.fasterxml.jackson.databind.JsonNode;
        import models.Users;
        import play.db.jpa.JPAApi;
        import play.libs.Json;
        import play.mvc.Result;

        import javax.persistence.TypedQuery;
        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;
        import java.security.SecureRandom;
        import java.util.List;
        import java.util.Random;

        import static play.mvc.Http.Context.Implicit.request;
        import static play.mvc.Results.badRequest;
        import static play.mvc.Results.ok;

/**
 * Created by kakhila on 3/1/17.
 */
public class UserService {

    private JPAApi jpaApi;
    public void createUser(Users user) {

        // TODO Save user in DB

    }

    public Users authenticate(String uname, String upwd) throws NoSuchAlgorithmException {

        // TODO Find username in DB and compare passwords
        // TODO If success, generate random auth_token and save in DB and return it from this method
        String token;
        String q = "select r from Users r where uname LIKE :uname";
        TypedQuery<Users> query = jpaApi.em().createQuery(q, Users.class).setParameter("uname", uname);
        List<Users> users = query.getResultList();
        final Users existingUser = jpaApi.em().find(Users.class, uname);
        JsonNode json = Json.toJson(users);
        if (0 != json.size()) {
            String salt = json.findValue("salt").asText();
            String newPwd = sha256(upwd, salt);
            String existingPwd = json.findValue("password").asText();

            if (newPwd.equals(existingPwd)) {
                token = authToken();
                existingUser.setToken(token);
                jpaApi.em().merge(existingUser);
                query = jpaApi.em().createQuery(q, Users.class).setParameter("uname", uname);
                users = query.getResultList();
                json = Json.toJson(users);
                return existingUser;
            } else {
                return null;
            }
        }
        return null;

    }

    public Users findUserByAuthToken(String token) {

        // TODO Find user in DB using authToken
        // TODO Verify token and expiration
        token = request().getHeader("Authentication");
        String uname = request().getHeader("id");
        Result auth = auth(token, uname);
        if(200 == auth.status()) {
            final Users existingUser = jpaApi.em().find(Users.class, uname);
            return existingUser;
        }
        return null;
    }


    public String sha256(String pwd, String salt) throws NoSuchAlgorithmException {


        String password = salt + pwd;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }


        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String salt(){

        Random ranGen = new SecureRandom();
        byte[] aesKey = new byte[16];
        ranGen.nextBytes(aesKey);
        return aesKey.toString() ;
    }

    public String authToken(){

        Random ranGen = new SecureRandom();
        byte[] aesKey = new byte[64];
        ranGen.nextBytes(aesKey);
        return aesKey.toString() ;
    }
    public Result auth(String token, String id) {
        final Users Users = jpaApi.em().find(Users.class, id);
        if(null != Users) {
            JsonNode json = Json.toJson(Users);
            if (token.equals(json.path("token").asText())) {
                return ok();
            }
        }
        return badRequest();

    }
}

