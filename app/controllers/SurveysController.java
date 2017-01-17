package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import models.Surveys;

import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import javax.persistence.TypedQuery;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;

/**
 * Created by welcome on 1/4/2017.
 */
public class SurveysController {



    public SurveysController() {
//        surveys = Lists.newArrayList(
//                new Surveys(1, "Surveys 1", "sdescription1", "date1"),
//                new Surveys(2, "Surveys 2", "sdescription2", "date2"),
//                new Surveys(3, "Surveys 3", "sdescription3", "date3")
//        );
    }
    private JPAApi jpaApi;

    @Inject
    public SurveysController(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    @Transactional
    public Result getAllSurveys(){
        TypedQuery<Surveys> query = jpaApi.em().createQuery("select s from Surveys s", Surveys.class);
        List<Surveys> surveys = query.getResultList();
        Logger.info("surveys",surveys);

        JsonNode json = Json.toJson(surveys);
        return ok(json);
    }

     @Transactional
    public Result getSurveyByID(Integer id){
    Surveys s= jpaApi.em().find(Surveys.class,id);

    JsonNode json=Json.toJson(s);
    return ok(json);


    }


//       @Transactional
//      public Result getSurveyByName(String name){
//      TypedQuery<Surveys> query = jpaApi.em().createQuery("select s from Surveys s where sname like name", Surveys.class);
//      List<Surveys> surveys = query.getResultList();
//      JsonNode json=Json.toJson(surveys);
//      return ok(json);
//
//
//      }
//



@Transactional
    public Result deleteSurveyById(Integer id){
         Surveys s= jpaApi.em().find(Surveys.class,id);
         jpaApi.em().remove(s);
         return ok("survey deleted");
    }
//
//
    @Transactional
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

        jpaApi.em().persist(s);
        return ok(json);
    }
}

