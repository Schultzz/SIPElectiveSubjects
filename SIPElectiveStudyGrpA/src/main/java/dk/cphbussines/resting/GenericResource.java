package dk.cphbussines.resting;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dk.cphbusiness.facades.RestFacade;
import dk.cphbussines.entity.Student;
import dk.cphbussines.entity.Subject;
import dk.cphbussines.entity.Vote;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author nikolai
 */
@Path("subject")
public class GenericResource {

    @Context
    private ServletContext context;
    private final Gson gson;
    private final RestFacade rf;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        gson = new Gson();
        rf = new RestFacade();
    }

    @GET
    @Produces("application/json")
    public String getSubjectsForPoolSelection() {
        String pathStr = context.getRealPath("/WEB-INF/classes");
        ArrayList<Subject> subjects = rf.getSubjectsForPoolSelectionFacadeMethod(pathStr);

        return gson.toJson(subjects);
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/studentCalc")
    public String studentCalc(String body) {
        String pathStr = context.getRealPath("/WEB-INF/classes");
        Type type = new TypeToken<List<Subject>>() {
        }.getType();

        ArrayList<Subject> subjects = gson.fromJson(body, type);
        ArrayList<Student> students = rf.studentCalc(pathStr, body, subjects);

        return gson.toJson(students);
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/electedPools")
    public String electedPools(String body) {
        String pathStr = context.getRealPath("/WEB-INF/classes");
        Subject[] subjectArray = gson.fromJson(body, Subject[].class);
        List<Subject> subjectList = Arrays.asList(subjectArray);
        rf.electedPools(pathStr, subjectList);

        String data = gson.toJson(subjectList);
        return data;
    }
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/saveStudentVote")
    public String saveStudentVote(String body) {
        String pathStr = context.getRealPath("/WEB-INF/classes");
        JSONObject jsonObj = new JSONObject(body);
        
        String name = jsonObj.getString("name");
        
        Vote vote = new Vote(new Subject(jsonObj.getString("prioOnePoolA")), new Subject(jsonObj.getString("prioOnePoolB")), new Subject(jsonObj.getString("prioTwoPoolB")), new Subject(jsonObj.getString("prioTwoPoolA")));
       
        Student stu = new Student(name, vote);
        rf.saveStudentVote(pathStr, stu);
        return pathStr;
    }

    @GET
    @Produces("application/json")
    @Path("/getElectedPools")
    public String getElectedPools() {
        String pathStr = context.getRealPath("/WEB-INF/classes");
        ArrayList<Subject> subjects = rf.getElectedPools(pathStr);
        return gson.toJson(subjects);
    }
}
