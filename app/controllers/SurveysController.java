package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import models.Surveys;
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
public class SurveysController {

    private List<Surveys> surveys;

    public SurveysController() {
        surveys = Lists.newArrayList(
                new Surveys(1, "Surveys 1", "sdescription1", "date1"),
                new Surveys(2, "Surveys 2", "sdescription2", "date2"),
                new Surveys(3, "Surveys 3", "sdescription3", "date3")
        );
    }

    public Result getAllSurveys(){

        JsonNode json = Json.toJson(surveys);
        return ok(json);
    }

    public Result getSurveyByID(Integer id){

        for(int i=0;i<surveys.size();i++){
            Surveys s=surveys.get(i);
            if(id==s.getSid()){
                JsonNode json=Json.toJson(s);
                return ok(json);
            }
        }
        return notFound("id not found");
    }

    public Result deleteSurvey(Integer id){
        for(int i=0;i<surveys.size();i++){
            Surveys s=surveys.get(i);
            if(id==s.getSid()){
                surveys.remove(s);
                return ok("survey not available");
            }
        }
        return notFound("id not found");
    }


    public Result addNewSurvey(){
        final JsonNode json = request().body().asJson();
        if (null == json) {
            Logger.error("Unable to get json from request");
            return badRequest("Unable to get json from request");
        }

        final Surveys s = Json.fromJson(json, Surveys.class);
        if (null == s) {
            Logger.error("Unable to parse json to Book object");
            return badRequest("Unable to parse json to Surveys object");
        }

        // make sure id and title is not null
       /* if (null == o.getOname()) {
            return badRequest();
        }*/
        surveys.add(s);
        return ok(json);
    }
}

