package dk.cphbussunes.rest;

import com.google.gson.Gson;
import dk.cphbussines.entity.Subject;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

@Path("subject")
public class Rest {

    @Context
    private UriInfo context;
    private Gson gson;

    /**
     * Creates a new instance of Rest
     */
    public Rest() {
        
        gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of dk.cphbussunes.rest.Rest
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        
//        //Hardcoded arraylist for subjects
//        ArrayList<Subject> subjects = new ArrayList();
//        subjects.add(new Subject("Android"));
//        subjects.add(new Subject("C#"));
//        subjects.add(new Subject("COBOL"));
//        String gsonString = gson.toJson(subjects);
//        return gsonString;
        return "per";
    }
    
    @GET
    @Path("/studentCalc")//Possible rename
    @Produces("application/json")
    @Consumes("application")
    public String getStudentCalc(String body){
        
//        List<Subject> subjects = (List) gson.fromJson(body, Subject.class);
//        
//        
        return "";
    }
    
}
