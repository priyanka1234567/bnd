package controllers;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.inject.Inject;


import models.Surveys;
import models.Users;
import play.Logger;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import javax.persistence.TypedQuery;


import static play.mvc.Controller.request;
import static play.mvc.Http.Context.Implicit.ctx;
import static play.mvc.Results.*;

/**
 * Created by pdevkare on 07/01/17.
 */
public class UsersController {
//    private List<Users> users;

    public UsersController() {

    }

    private JPAApi jpaApi;
    public List<Users> user;
    @Inject
    public UsersController(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    @Transactional
    public Result getAllUsers() {

        TypedQuery<Users> query = jpaApi.em().createQuery("select u from Users u", Users.class);
        user = query.getResultList();
        Logger.debug("hii");
        Logger.debug(user.toString());
        JsonNode json = Json.toJson(user);
        
        return ok(json);
    }

    @Transactional
    public Result getUserByName(String uname){

        String q = "select u from Users u where uname LIKE :uname ";
        TypedQuery<Users> query = jpaApi.em().createQuery(q, Users.class).setParameter("uname", uname);
        user = query.getResultList();
        final JsonNode json = Json.toJson(user);

        return ok(json);


    }

    @Transactional
    public Result getUserByID(Integer id){
        Users u= jpaApi.em().find(Users.class,id);

        JsonNode json=Json.toJson(u);
        return ok(json);


    }


    @Transactional
    public Result addUser() throws NoSuchAlgorithmException {
        final JsonNode json=request().body().asJson();
        String c = json.path("upwd").asText();
        String uname = json.path("uname").asText();
        Logger.debug(c);
        Logger.debug(uname);
        String salt = salt();
        String upwd = sha256(c, salt);
        final Users users=Json.fromJson(json,Users.class);
        users.setSalt(salt);
        users.setUpwd(upwd
        );
        jpaApi.em().persist(users);
            return created();
    }

    @Transactional
    public Result updateUserPassword(Integer id) {
        final JsonNode json = request().body().asJson();
        if (null == json) {

            return badRequest("json not found");
        }


        Users u=Json.fromJson(json,Users.class);
        if(null==u)
        {
            return badRequest("not found");
        }
        Users old=jpaApi.em().find(Users.class,id);
        old.setUpwd(u.getUpwd());
        jpaApi.em().merge(old);
        return ok("updated password for "+old.getUname());
    }


    @Transactional
    public Result validateUser(String uname, String upwd) throws NoSuchAlgorithmException {

        String token;
        String q = "select r from Users r where uname LIKE :uname";
        TypedQuery<Users> query = jpaApi.em().createQuery(q, Users.class).setParameter("uname",uname);
        List<Users> users = query.getResultList();
        final Users existingAdmin=jpaApi.em().find(Users.class,uname);
        JsonNode json = Json.toJson(users);
        if(0 != json.size() ) {

            String existingPwd = json.findValue("upwd").asText();
            String salt = json.findValue("salt").asText();
            String newPwd = sha256(upwd,salt);
            if(newPwd.equals(existingPwd)){
                token=authToken();
                existingAdmin.setToken(token);
                jpaApi.em().merge(existingAdmin);
                query = jpaApi.em().createQuery(q, Users.class).setParameter("uname",uname);
                users = query.getResultList();
                json = Json.toJson(users);
                return ok(json);
            }
            else{
                return notFound("invalid details");
            }
        }
        return notFound("not registered");
    }



    @Transactional

    public Result getCurrentUser() {


        /*
        final String authorized = request().getHeader("Authorization");
        Logger.debug("Authorized header: {}", authorized);
        if (!authorized.startsWith("Bearer ")) {
            return unauthorized();
        }
        final String authToken = authorized.substring(7);
        if (authorized.isEmpty()) {
            return unauthorized();
        }
        Logger.debug("auth_token: {}", authToken);
        final User user = userService.findUserByAuthToken(authToken);
        if (null == user) {
            return unauthorized();
        }
        Logger.debug("user: {}", user);
        */

        final Users user = (Users) ctx().args.get("user");

        /*
        if (user.getRole() != User.Role.ADMIN) {
            return forbidden();
        }
        */


        if(null==user)
            return unauthorized();
        else
            return ok();
        //return ok(Json.toJson(user));
    }
    @Transactional
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


}

