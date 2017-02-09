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

import java.util.List;
import javax.persistence.TypedQuery;


import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;
/**
 * Created by pdevkare on 07/01/17.
 */
public class UsersController {
//    private List<Users> users;

    public UsersController() {
//       users = Lists.newArrayList(
//                new Users(1, "Surveys 1", "owner1_pwd"),
//                new Users(2, "Surveys 2", "owner2_pwd"),
//                new Users(3, "Surveys 3", "owner3_pwd")
//        );
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
        List<Users> users = query.getResultList();
        Logger.info("users", users);

        JsonNode json = Json.toJson(users);
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
    public Result addUser() {
        final JsonNode json = request().body().asJson();
        if (null == json) {
            Logger.error("Unable to get json from request");
            return badRequest("Unable to get json from request");
        }

        final Users u = Json.fromJson(json, Users.class);
        if (null == u) {
            Logger.error("Unable to parse json to User object");
            return badRequest("Unable to parse json to User object");
        }

        // make sure id and title is not null
       /* if (null == o.getOname()) {
            return badRequest();
        }*/
        jpaApi.em().persist(u);
        return ok(json);
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

}

