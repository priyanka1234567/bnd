package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import models.Owner;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;

/**
 * Created by welcome on 1/4/2017.
 */
public class OwnerController {
    private List<Owner> owners;

    public OwnerController() {
        owners = Lists.newArrayList(
                new Owner(1, "Owner 1", "owner1_pwd", "owner1@gmail.com"),
                new Owner(2, "Owner 2", "owner2_pwd", "owner2@gmail.com"),
                new Owner(3, "Owner 3", "owner3_pwd", "owner3@gmail.com")
        );
    }

    public Result getAllOwners(){

        JsonNode json = Json.toJson(owners);
        return ok(json);
    }

    public Result getOwnerByID(Integer id){

        for(int i=0;i<owners.size();i++){
            Owner o=owners.get(i);
            if(id==o.getOid()){
                JsonNode json=Json.toJson(o);
                return ok(json);
            }
        }
        return notFound("id not found");
    }

    public Result deleteOwner(Integer id){
        for(int i=0;i<owners.size();i++){
            Owner o=owners.get(i);
            if(id==o.getOid()){
                owners.remove(o);
                return ok("restaurant removed");
            }
        }
        return notFound("id not found");
    }

    public Result updateOwner(Integer id){
        final JsonNode json=request().body().asJson();
        if(null==json){
            return badRequest("not found");
        }
        Owner o=Json.fromJson(json,Owner.class);
        if(null==o){
            return badRequest("not found");
        }
        if (null == o.getOname()) {
            return badRequest();
        }

        for(int i=0;i<owners.size();i++){
            Owner ow=owners.get(i);
            if(id==ow.getOid()){
                ow.setOname(ow.getOname());
                return ok("restaurant updated");
            }
        }

        return ok();
    }

    public Result addOwner(){
        final JsonNode json = request().body().asJson();
        if (null == json) {
            Logger.error("Unable to get json from request");
            return badRequest("Unable to get json from request");
        }

        final Owner o = Json.fromJson(json, Owner.class);
        if (null == o) {
            Logger.error("Unable to parse json to Book object");
            return badRequest("Unable to parse json to Owner object");
        }

        // make sure id and title is not null
       /* if (null == o.getOname()) {
            return badRequest();
        }*/
        owners.add(o);
        return ok(json);
    }
}

