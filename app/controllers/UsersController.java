package controllers;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import models.Users;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;
/**
 * Created by pdevkare on 07/01/17.
 */
public class UsersController {
    private List<Users> users;

    public UsersController() {
       users = Lists.newArrayList(
                new Users(1, "Owner 1", "owner1_pwd"),
                new Users(2, "Owner 2", "owner2_pwd"),
                new Users(3, "Owner 3", "owner3_pwd")
        );
    }

    public Result getAllUsers(){

        JsonNode json = Json.toJson(users);
        return ok(json);
    }
    public Result addUser(){
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
        users.add(u);
        return ok(json);
    }

}
