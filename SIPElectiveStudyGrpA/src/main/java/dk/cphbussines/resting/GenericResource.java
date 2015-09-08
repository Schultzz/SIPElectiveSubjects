/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbussines.resting;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dk.cphbussines.entity.Subject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author nikolai
 */
@Path("subject")
public class GenericResource {

    @Context
    private UriInfo context;
    private Gson gson;
    

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of dk.cphbussines.resting.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getSubjectsForPoolSelection() {
        //Hardcoded array
        ArrayList<Subject> subjects = new ArrayList();
        subjects.add(new Subject("Android"));
        subjects.add(new Subject("C#"));
        subjects.add(new Subject("COBOL"));
        return gson.toJson(subjects);
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/studentCalc")
    public String studentCalc(String body){
        
        Type type = new TypeToken<List<Subject>>(){}.getType();
        List<Subject> subjects = gson.fromJson(body, type);
        return "";
        
    }
}
