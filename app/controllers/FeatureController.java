package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.Features;
import play.Logger;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import javax.persistence.TypedQuery;
import java.util.List;
import play.libs.Json;
import play.mvc.Result;
import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
/**
 * Created by Admin on 2/9/2017.
 */
public class FeatureController {

    public FeatureController(){}



    private JPAApi jpaApi;
    public List<Features> feature;

    @Inject
    public FeatureController(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    @Transactional
    public Result getAllFeatures(){
        TypedQuery<Features> query = jpaApi.em().createQuery("select f from Features f ", Features.class);
        List<Features> features = query.getResultList();
        Logger.info("features",features);

        JsonNode json = Json.toJson(features);
        return  ok(json);
    }

    @Transactional
    public Result getFeatureByID(Integer id){
        Features f= jpaApi.em().find(Features.class,id);

        JsonNode json=Json.toJson(f);
        return ok(json);


    }


    @Transactional
    public Result getFeatureByName(String name){

        String q = "select f from Features s where name LIKE :name ";
        TypedQuery<Features> query = jpaApi.em().createQuery(q, Features.class).setParameter("name", name);
         feature= query.getResultList();
        final JsonNode json = Json.toJson(feature);
        return ok(json);


    }



    @Transactional
    public Result deleteFeatureById(Integer id){
        Features f= jpaApi.em().find(Features.class,id);
        jpaApi.em().remove(f);

        if(null == f){
            return badRequest("entry not available");
        }
        return ok("faeture entry deleted");
    }

    @Transactional
    public Result addNewFeature(){
        final JsonNode json = request().body().asJson();
        if (null == json) {
            Logger.error("Unable to get json from request");
            return badRequest("Unable to get json from request");
        }

        final Features f = Json.fromJson(json, Features.class);
        if (null == f) {
            Logger.error("Unable to parse json to Book object");
            return badRequest("Unable to parse json to Surveys object");
        }

        jpaApi.em().persist(f);
        return ok(json);
    }


    @Transactional
    public Result updateFeature(Integer id) {
        final JsonNode json = request().body().asJson();
        if (null == json) {

            return badRequest("json not found");
        }

        if(null == id){
            return  badRequest("id not found");
        }


        Features f=Json.fromJson(json,Features.class);
        if(null==f)
        {
            return badRequest("not found");
        }
        Features old=jpaApi.em().find(Features.class,id);
        old.setName(f.getName());
        old.setLatitude(f.getLatitude());
        old.setLongitude(f.getLongitude());
        old.setCountry(f.getCountry());
        old.setState(f.getState());
        old.setDistrict(f.getDistrict());
        old.setArchstyle(f.getArchstyle());
        old.setCreator(f.getCreator());
        old.setDeities(f.getDeities());
        old.setDatebuilt(f.getDatebuilt());
        old.setEateries(f.getEateries());
        old.setGuides(f.getGuides());
        old.setFestivals(f.getFestivals());
        jpaApi.em().merge(old);
        return ok("updated Successfully ");
    }
}
