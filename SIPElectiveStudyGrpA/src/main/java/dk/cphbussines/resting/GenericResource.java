package dk.cphbussines.resting;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dk.cphbussines.calculator.CategoryCalculator;
import dk.cphbussines.entity.Student;
import dk.cphbussines.entity.Subject;
import dk.cphbussines.entity.Vote;
import dk.cphbussines.namemeplease.TextReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author nikolai
 */
@Path("subject")
public class GenericResource {

    @Context
    private ServletContext context;
    private Gson gson;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of
     * dk.cphbussines.resting.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getSubjectsForPoolSelection() {
        //Hardcoded array
        Subject subject12 = new Subject("C#");
        Subject subject21 = new Subject("Python");
        Subject subject22 = new Subject("Android");
        Subject subject31 = new Subject("SW Design");
        Subject subject32 = new Subject("Games");
        Subject subject41 = new Subject("Databases");
        Subject subject42 = new Subject("Test");
        Subject subject11 = new Subject("Arduino");

        ArrayList<Subject> subjects = new ArrayList();
        subjects.add(subject11);
        subjects.add(subject12);
        subjects.add(subject21);
        subjects.add(subject22);
        subjects.add(subject31);
        subjects.add(subject32);
        subjects.add(subject41);
        subjects.add(subject42);

        Vote vote = new Vote(subject11, subject12, subject21, subject22);
        Vote vote2 = new Vote(subject21, subject31, subject11, subject22);
        Vote vote3 = new Vote(subject31, subject32, subject11, subject12);
        Vote vote4 = new Vote(subject21, subject22, subject11, subject22);

        ArrayList<Student> students = new ArrayList();
        Student student1 = new Student("Lars", vote);
        Student student2 = new Student("Peter", vote2);
        Student student3 = new Student("Arne", vote3);
        Student student4 = new Student("Jens", vote4);

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        CategoryCalculator calc = new CategoryCalculator(students, subjects);
        calc.calculateSubjectTotal();
        return gson.toJson(calc.getSubjectList());
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/studentCalc")
    public String studentCalc(String body) {

        String pathStr = context.getRealPath("/WEB-INF/classes");
        pathStr += "/students.txt";

        System.out.println("body: " + body);

        Type type = new TypeToken<List<Subject>>() {
        }.getType();
        ArrayList<Subject> subjects = gson.fromJson(body, type);
        ArrayList<Student> students = TextReader.loadListOfStudents(pathStr);
        System.out.println("studentsSize: " + students.size());
        CategoryCalculator calc = new CategoryCalculator(students, subjects);
        students = calc.assignTopicsToStudents();
        String studentGson = gson.toJson(students);
        return studentGson;

    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/electedPools")
    public String electedPools(String body) {
        
        String pathStr = context.getRealPath("/WEB-INF/classes");
        pathStr += "/pools.txt";
        
        Subject[] subjectArray = gson.fromJson(body, Subject[].class);
        List<Subject> subjectList = Arrays.asList(subjectArray);

        String data = gson.toJson(subjectList);
        System.out.println(subjectList.toString());
        
        TextReader.savePoolsToFile(pathStr, subjectList);
        return data;
    }
}
