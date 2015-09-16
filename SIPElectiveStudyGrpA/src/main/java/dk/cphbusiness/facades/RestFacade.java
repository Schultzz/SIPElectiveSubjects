package dk.cphbusiness.facades;

import dk.cphbussines.calculator.CategoryCalculator;
import dk.cphbussines.entity.Student;
import dk.cphbussines.entity.Subject;
import dk.cphbussines.namemeplease.TextReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;

/**
 *
 * @author sholmager
 */
public class RestFacade {

    private ServletContext context;

    public ArrayList<Subject> getSubjectsForPoolSelectionFacadeMethod(String pathFinal) {
        String pathStr = pathFinal;
        pathStr += "/students.txt";
        
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
      
        
        ArrayList<Student> students = TextReader.loadListOfStudents(pathStr);

        CategoryCalculator calc = new CategoryCalculator(students, subjects);
        calc.incrementSubjectVotes();
        System.out.println("uncalculated list:" + subjects.toString());
        System.out.println("calculated list: " + calc.getSubjectList());
        return calc.getSubjectList();
    }

    public ArrayList<Student> studentCalc(String path, String body, ArrayList<Subject> subjects) {

        path += "/students.txt";
        ArrayList<Student> students = TextReader.loadListOfStudents(path);
        System.out.println("studentsSize: " + students.size());
        
        CategoryCalculator calc = new CategoryCalculator(students, subjects);
        students = calc.assignTopicsToStudents2();

        return students;
    }

    public void electedPools(String path, List<Subject> subjectList) {
        path += "/pools.txt";
        TextReader.savePoolsToFile(path, subjectList);
    }

    public ArrayList<Subject> getElectedPools(String path) {
        path += "/pools.txt";
        ArrayList<Subject> subjects = TextReader.loadListOfPools(path);
        return subjects;
    }
}
