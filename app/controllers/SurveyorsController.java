package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import models.Surveyors;
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
public class SurveyorsController {

    private List<Surveyors> surveyentries;

    public SurveyorsController() {
        Time tOpen= new Time(10,00,00);
        Time tClose=new Time(22,00,00);
        surveyentries= Lists.newArrayList(
                new Surveyors(1,"Surveyors 1",167.21,290.77,"landmark1",tOpen,tClose,"capital of the medieval sultanate of the Qutb Shahi dynasty","file_1"),
                new Surveyors(2,"Surveyors 2",74.2,20.7,"landmark2",tOpen,tClose,"some dynasty",""),
                new Surveyors(3,"Surveyors 3",166.588,21.6,"landmark3",tOpen,tClose,"Second dynasty",""),
                new Surveyors(4,"Surveyors 4",54.789,11.76,"landmark4",tOpen,tClose,"thrid dynasty","")
        );
    }

    public Result getAllSurveyentries(){

        JsonNode json = Json.toJson(surveyentries);
        return ok(json);
    }

    public Result getSurveyEntryByID(Integer id){

        for(int i=0;i<surveyentries.size();i++){
            Surveyors e=surveyentries.get(i);
            if(id==e.getEid()){
                JsonNode json=Json.toJson(e);
                return ok(json);
            }
        }
        return notFound("id not found");
    }

    public Result deleteSurveyEntry(Integer id){
        for(int i=0;i<surveyentries.size();i++){
            Surveyors r=surveyentries.get(i);
            if(id==r.getEid()){
                surveyentries.remove(r);
                return ok("survey entry removed");
            }
        }
        return notFound("id not found");
    }



    public Result addNewSurveyEntry(){
        final JsonNode json = request().body().asJson();
        if (null == json) {
            Logger.error("Unable to get json from request");
            return badRequest();
        }

        final Surveyors srvy = Json.fromJson(json, Surveyors.class);
        if (null == srvy) {
            Logger.error("Unable to parse json to Book object");
            return badRequest();
        }

        // make sure id and title is not null
        if (null == srvy.getName()) {
            return badRequest();
        }
        surveyentries.add(srvy);
        return ok(json);
    }

}
