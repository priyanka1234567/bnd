package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import models.Surveys;

import models.Users;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import javax.persistence.TypedQuery;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.*;

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
    public List<Surveys> survey;

    @Inject
    public SurveysController(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    @Transactional
    public Result getAllSurveys(){
        TypedQuery<Surveys> query = jpaApi.em().createQuery("select s from Surveys s ", Surveys.class);
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


    @Transactional
    public Result getSurveyByName(String sname){

        String q = "select s from Surveys s where sname LIKE :sname ";
        TypedQuery<Surveys> query = jpaApi.em().createQuery(q, Surveys.class).setParameter("sname", sname);
        survey = query.getResultList();
        final JsonNode json = Json.toJson(survey);
        return ok(json);


    }



@Transactional
    public Result deleteSurveyById(Integer id1){
    String token = request().getHeader("Authentication");
    String uname = request().getHeader("id");
    Result auth = auth(token, uname);
    Logger.debug(token);
    Logger.debug(uname);
    Logger.debug(auth.toString());
    if(200 == auth.status()) {
        Surveys s = jpaApi.em().find(Surveys.class, id1);
        jpaApi.em().remove(s);

        if (null == s) {
            return badRequest("entry not available");
        }
        return ok("survey deleted");
    }
    return unauthorized("unauthorized user");
    }

    @Transactional
    public Result addNewSurvey(){
        String token = request().getHeader("Authentication");
        String uname = request().getHeader("id");
        Result auth = auth(token, uname);
        Logger.debug(token);
        Logger.debug(uname);
        Logger.debug(auth.toString());
        if(200 == auth.status()) {
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
        return unauthorized("unauthorized user");

    }
    public Result auth(String token, String id) {
        final Users users = jpaApi.em().find(Users.class, id);
        if(null != users) {
            JsonNode json = Json.toJson(users);
            if (token.equals(json.path("token").asText())) {
                return ok();
            }
        }
        return badRequest();

    }

    @Transactional
    public Result updateSurvey(Integer id) {
        String token = request().getHeader("Authentication");
        String uname = request().getHeader("id");
        Result auth = auth(token, uname);
        Logger.debug(token);
        Logger.debug(uname);
        Logger.debug(auth.toString());
        if (200 == auth.status()) {
            final JsonNode json = request().body().asJson();
            if (null == json) {

                return badRequest("json not found");
            }

            if (null == id) {
                return badRequest("id not found");
            }


            Surveys s = Json.fromJson(json, Surveys.class);
            if (null == s) {
                return badRequest("not found");
            }
            Surveys old = jpaApi.em().find(Surveys.class, id);
            old.setSname(s.getSname());
            old.setSdescription(s.getSdescription());
            old.setDate(s.getDate());
            jpaApi.em().merge(old);
            return ok("updated Successfully ");
        }
        return unauthorized("unauthorized user");
    }


}

