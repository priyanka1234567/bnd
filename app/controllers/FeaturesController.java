package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import models.Features;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import com.google.inject.Inject;

import java.sql.Time;
import java.util.List;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import javax.persistence.TypedQuery;

import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;

/**
 *
 */
public class FeaturesController {


    public FeaturesController() {


    }
    private JPAApi jpaApi;

    @Inject
    public FeaturesController(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    @Transactional
    public Result addNewSurveyEntry(){
        final JsonNode json = request().body().asJson();
        if (null == json) {
            Logger.error("Unable to get json from request");
            return badRequest();
        }

        final Features srvy = Json.fromJson(json, Features.class);
        if (null == srvy) {
            Logger.error("Unable to parse json to Book object");
            return badRequest();
        }

        // make sure id and title is not null
        if (null == srvy.getName()) {
            return badRequest();
        }
        jpaApi.em().persist(srvy);
        return ok(json);
    }

    @Transactional

    public Result getAllSurveyentries(){
        TypedQuery<Features> query = jpaApi.em().createQuery("select s from Features s", Features.class);
        List<Features> features = query.getResultList();
        Logger.info("features",features);
        JsonNode json = Json.toJson(features);
        return ok(json);
    }
    @Transactional
    public Result getSurveyEntryByID(Integer id){

        Features f= jpaApi.em().find(Features.class,id);
                JsonNode json=Json.toJson(f);
                return ok(json);

    }
@Transactional
    public Result deleteSurveyEntryById(Integer id){
    Features f= jpaApi.em().find(Features.class,id);
    jpaApi.em().remove(f);

    return ok("feature deleted");
    }





}
