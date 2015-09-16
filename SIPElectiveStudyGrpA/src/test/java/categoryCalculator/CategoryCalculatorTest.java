/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package categoryCalculator;

import dk.cphbussines.calculator.CategoryCalculator;
import dk.cphbussines.entity.CategoryEnum;
import dk.cphbussines.entity.Student;
import dk.cphbussines.entity.Subject;
import dk.cphbussines.entity.SubjectInterface;
import dk.cphbussines.entity.Vote;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ms
 */
public class CategoryCalculatorTest {

    @Test
    public void createNewCalculator() {

        ArrayList<Student> studentList = new ArrayList();
        ArrayList<Subject> subjectList = new ArrayList();
        CategoryCalculator calc = new CategoryCalculator(studentList, subjectList);
        assertThat(calc, is(not(nullValue())));
        assertThat(calc.getSubjectList(), is(not(nullValue())));
        assertThat(calc.getStudentList(), is(not(nullValue())));

    }

//    public void calculateMethodTest(){
//        
//        ArrayList<Student> studentList = new ArrayList();
//        ArrayList<Subject> subjectList = new ArrayList();
//        CategoryCalculator calc = new CategoryCalculator(studentList, subjectList);
//        
//    }
    @Test
    public void assignStudentsToTopic() {
        SubjectInterface subject1 = new Subject("Android");
        Subject subject2 = new Subject("C#");
        Subject subject3 = new Subject("Algol60");
        Subject subject4 = new Subject("COBOL");

        Vote vote = new Vote(subject1, subject2, subject3, subject4);
        Student student = new Student("Per", vote);
        assertThat(student.getTopicA(), is(nullValue()));
        assertThat(student.getTopicB(), is(nullValue()));
        ArrayList<Subject> subjectList = new ArrayList();
        Subject subjectForList1 = new Subject("C#");
        subjectForList1.setPool("A");
        Subject subjectForList2 = new Subject("COBOL");
        subjectForList2.setPool("B");
        subjectList.add(subjectForList1);
        subjectList.add(subjectForList2);

        ArrayList<Student> studentList = new ArrayList();
        studentList.add(student);

        CategoryCalculator calc = new CategoryCalculator(studentList, subjectList);

        ArrayList<Student> assignedStudents = calc.assignTopicsToStudents();
        //Topics er strings, burde måske være på subjects, not sure
        assertThat(assignedStudents.isEmpty(), is(not(true)));
        System.out.println(assignedStudents.get(0).getTopicA());
        System.out.println(assignedStudents.get(0).getTopicB());
        assertThat(assignedStudents.get(0).getTopicA(), is(subjectForList1.getTopic()));
        assertThat(assignedStudents.get(0).getTopicB(), is(subjectForList2.getTopic()));

    }

    @Test
    public void setCategoryStudent() {

        Subject subject1 = new Subject("Android");
        Subject subject2 = new Subject("C#");
        Subject subject3 = new Subject("Algol60");
        Subject subject4 = new Subject("COBOL");
        Subject subject5 = new Subject("Haskel");
        Subject subject6 = new Subject("PHP");

        Vote vote = new Vote(subject2, subject4, subject3, subject4);
        Vote vote1 = new Vote(subject4, subject1, subject3, subject2);
        Vote vote2 = new Vote(subject1, subject3, subject4, subject2);
        Vote vote3 = new Vote(subject1, subject3, subject2, subject6);

        Student studentA = new Student("Per", vote);
        Student studentB = new Student("Per1", vote1);
        Student studentC = new Student("Per2", vote2);
        Student studentD = new Student("Per3", vote3);
        ArrayList<Subject> subjectList = new ArrayList();
        Subject subjectForList1 = new Subject("C#");
        subjectForList1.setPool("A");
        Subject subjectForList2 = new Subject("COBOL");
        subjectForList2.setPool("B");
        subjectList.add(subjectForList1);
        subjectList.add(subjectForList2);

        ArrayList<Student> studentList = new ArrayList();
        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        CategoryCalculator calc = new CategoryCalculator(studentList, subjectList);

        calc.assignTopicsToStudents();

        assertThat(studentA.getCategory(), is(CategoryEnum.A));
        assertThat(studentB.getCategory(), is(CategoryEnum.B));
        assertThat(studentC.getCategory(), is(CategoryEnum.C));
        assertThat(studentD.getCategory(), is(CategoryEnum.D));
    }

    @Test
    public void IncrementSubjectsWithVoteList() {

        Subject subject1 = new Subject("Android");
        Subject subject2 = new Subject("C#");
        Subject subject3 = new Subject("Algol60");
        Subject subject4 = new Subject("COBOL");
        Subject subject5 = new Subject("Haskel");
        Subject subject6 = new Subject("PHP");

        Vote vote = new Vote(subject2, subject4, subject3, subject5);
        Vote vote1 = new Vote(subject4, subject1, subject3, subject2);
        Vote vote2 = new Vote(subject1, subject3, subject4, subject2);
        Vote vote3 = new Vote(subject1, subject3, subject2, subject6);

        Student studentA = new Student("Per", vote);
        Student studentB = new Student("Per1", vote1);
        Student studentC = new Student("Per2", vote2);
        Student studentD = new Student("Per3", vote3);
        ArrayList<Subject> subjectList = new ArrayList();
        Subject subjectForList1 = new Subject("C#");
        subjectForList1.setPool("A");
        Subject subjectForList2 = new Subject("COBOL");
        subjectForList2.setPool("B");
        subjectList.add(subjectForList1);
        subjectList.add(subjectForList2);

        ArrayList<Student> studentList = new ArrayList();
        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        CategoryCalculator ca = new CategoryCalculator(studentList, subjectList);

        ca.incrementSubjectVotes();

        assertThat(ca.getSubjectList().get(0).getPriority1(), is(1));
        assertThat(ca.getSubjectList().get(0).getPriority2(), is(3));
        assertThat(ca.getSubjectList().get(1).getPriority1(), is(2));
        assertThat(ca.getSubjectList().get(1).getPriority2(), is(1));
    }
}
