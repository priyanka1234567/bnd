package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import models.Owner;
import models.Restaurant;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;

import java.sql.Time;
import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;

/**
 * Created by Sivani on 29/12/16.
 */
public class RestaurantController {

    private List<Restaurant> restaurants;

    public RestaurantController() {
        Time tOpen= new Time(12,00,00);
        Time tClose=new Time(22,30,00);
        restaurants= Lists.newArrayList(
                new Restaurant(1,"Restaurant 1",13.2,20.6,"chinese",500,tOpen,tClose,"ambience","file_1",111),
                new Restaurant(2,"Restaurant 2",40.2,25.6,"Mexican",800,tOpen,tClose,"Churos and tacos","file_2",222),
                new Restaurant(3,"Restaurant 3",14.5,21.6,"North Indian",700,tOpen,tClose,"Special Thali","file_3",333),
                new Restaurant(4,"Restaurant 4",13.7,20.1,"South Indian",650,tOpen,tClose,"Sambar Idly","file_4",444)
        );
    }

    public Result getAllRestaurants(){

        JsonNode json = Json.toJson(restaurants);
        return ok(json);
    }

    public Result getRestaurantByID(Integer id){

        for(int i=0;i<restaurants.size();i++){
            Restaurant r=restaurants.get(i);
            if(id==r.getId()){
                JsonNode json=Json.toJson(r);
                return ok(json);
            }
        }
        return notFound("id not found");
    }

    public Result deleteRestaurant(Integer id){
        for(int i=0;i<restaurants.size();i++){
            Restaurant r=restaurants.get(i);
            if(id==r.getId()){
                restaurants.remove(r);
                return ok("restaurant removed");
            }
        }
        return notFound("id not found");
    }

    public Result updateRestaurant(Integer id){
        final JsonNode json=request().body().asJson();
        if(null==json){
            return badRequest("not found");
        }
        final Restaurant r = Json.fromJson(json,Restaurant.class);
        if(null==r){
            return badRequest("not found");
        }
        if (null == r.getName()) {
            return badRequest();
        }

        for(int i=0;i<restaurants.size();i++){
            Restaurant re=restaurants.get(i);
            if(id==re.getId()){
                re.setName(r.getName());
                return ok("restaurant updated");
            }
        }

        return ok();
    }

    public Result addRestaurant(){
        final JsonNode json = request().body().asJson();
        if (null == json) {
            Logger.error("Unable to get json from request");
            return badRequest();
        }

        final Restaurant res = Json.fromJson(json, Restaurant.class);
        if (null == res) {
            Logger.error("Unable to parse json to Book object");
            return badRequest();
        }

        // make sure id and title is not null
        if (null == res.getName()) {
            return badRequest();
        }
        restaurants.add(res);
        return ok(json);
    }

}
