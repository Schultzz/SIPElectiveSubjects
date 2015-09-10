/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
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
    private UriInfo context;
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
        ArrayList<Subject> subjects = new ArrayList();
        subjects.add(new Subject("Android"));
        subjects.add(new Subject("C#"));
        subjects.add(new Subject("COBOL"));
        subjects.add(new Subject("C++"));
        subjects.add(new Subject("Angular"));
//        subjects.add(new Subject("NodeJS"));
//        subjects.add(new Subject("Haskell"));
//        subjects.add(new Subject("Java"));
//        subjects.add(new Subject("Python"));
//        subjects.add(new Subject("Arduino"));
//        subjects.add(new Subject("Ruby"));
//        subjects.add(new Subject(".NET"));
//        subjects.add(new Subject("ALGOL60"));

        return gson.toJson(subjects);
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/studentCalc")
    public String studentCalc(String body) {

        System.out.println("body: " + body);
        
        Type type = new TypeToken<List<Subject>>() {
        }.getType();
        ArrayList<Subject> subjects = gson.fromJson(body, type);
//        ArrayList<Student> students = TextReader.loadListOfStudents("students.txt");
        
        ArrayList<Student> students = new ArrayList();
        
        String name = "Lars";
        String testTopic11 = "Android";
        String testTopic12 = "C#";
        String testTopic21 = "COBOL";
        String testTopic22 = "C++";
        String testTopic31 = "Angular";
        String testTopic32 = "NodeJS";
        Subject subject11 = new Subject(testTopic11);
        Subject subject12 = new Subject(testTopic12);
        Subject subject21 = new Subject(testTopic21);
        Subject subject22 = new Subject(testTopic22);
        Subject subject31 = new Subject(testTopic31);
        Subject subject32 = new Subject(testTopic32);
        Vote vote = new Vote(subject11, subject12, subject21, subject22);
        Vote vote2 = new Vote(subject21, subject31, subject11, subject22);
        Vote vote3 = new Vote(subject31, subject32, subject11, subject12);
        Vote vote4 = new Vote(subject21, subject22, subject11, subject22);
        Student student1 = new Student(name, vote);
        Student student2 = new Student("Peter", vote2);
        Student student3 = new Student("Arne", vote3);
        Student student4 = new Student("Jens", vote4);
        
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        
        
        CategoryCalculator calc = new CategoryCalculator(students, subjects);
        students = calc.assignTopicsToStudents();
        String studentGson = gson.toJson(students);
        return studentGson;

    }
}
