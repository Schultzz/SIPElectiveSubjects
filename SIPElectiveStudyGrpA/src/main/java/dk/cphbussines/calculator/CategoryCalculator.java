/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbussines.calculator;

import dk.cphbussines.entity.CategoryEnum;
import dk.cphbussines.entity.Student;
import dk.cphbussines.entity.Subject;
import dk.cphbussines.entity.SubjectInterface;
import java.util.ArrayList;

/**
 *
 * @author ms
 */
public class CategoryCalculator {

    private ArrayList<Student> studentList;
    private ArrayList<Subject> subjectList;

    public CategoryCalculator(ArrayList<Student> studentList, ArrayList<Subject> subjectList) {

        this.studentList = studentList;
        this.subjectList = subjectList;

    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public ArrayList<Subject> getSubjectList() {
        return subjectList;
    }

    /*
     Metode til at udregne det antal elever som har henholdvis prioriteret
     hvert subject. Både første og anden prioritet bliver gemt i subjectList
     */
    public void incrementSubjectVotes() {
        // !!!! Mangler optimering til gennemgangen af subjects !!!!
        for (Student student : studentList) {

            SubjectInterface s11 = student.getVote().getFirstPrio().get(0);
            SubjectInterface s12 = student.getVote().getFirstPrio().get(1);
            SubjectInterface s21 = student.getVote().getSecondPrio().get(0);
            SubjectInterface s22 = student.getVote().getSecondPrio().get(1);

            for (Subject subject : subjectList) {

                if (subject.getTopic().equals(s11.getTopic())) {
                    subject.priority1Increment();
                }

                if (subject.getTopic().equals(s12.getTopic())) {
                    subject.priority1Increment();
                }

                if (subject.getTopic().equals(s21.getTopic())) {
                    subject.priority2Increment();
                }

                if (subject.getTopic().equals(s22.getTopic())) {
                    subject.priority2Increment();
                }
            }
        }
    }

    public void setSubjectToStudent(SubjectInterface vote, Student student) {

        //If Topic A is NOT set look through the list and see if there is a match
        if (student.getTopicA() == null) {
            for (Subject subject : subjectList) {
                if (vote.getTopic().equals(subject.getTopic())) {
                    if (subject.getPool().equals("A")) {
                        student.setTopicA(subject.getTopic());
                        //evt return
                    }

                }
            }

        }
        //If Topic B is NOT set look through the list and see if there is a match
        if (student.getTopicB() == null) {
            for (Subject subject : subjectList) {
                if (vote.getTopic().equals(subject.getTopic())) {
                    if (subject.getPool().equals("B")) {
                        student.setTopicB(subject.getTopic());
                        //evt return
                    }

                }

            }
        }

    }

    public ArrayList<Student> assignTopicsToStudents2() {

        for (Student student : studentList) {
            //For each student in the list do stuff:
            // Get votes from student in priority
            SubjectInterface s11 = student.getVote().getFirstPrio().get(0);
            SubjectInterface s12 = student.getVote().getFirstPrio().get(1);
            SubjectInterface s21 = student.getVote().getSecondPrio().get(0);
            SubjectInterface s22 = student.getVote().getSecondPrio().get(1);

            //Trying to assign a student two topics
            //There are two pool, therefor we always need to check the 2 first prios
            setSubjectToStudent(s11, student);
            setSubjectToStudent(s12, student);
            
            //If the subject wasn't in the first prio, we are looking for seconds prio.
            if (student.getTopicA() == null || student.getTopicB() == null) {
                setSubjectToStudent(s21, student);
            }
            if (student.getTopicA() == null || student.getTopicB() == null) {
                setSubjectToStudent(s22, student);
            }

        }
        this.studentList = calculateStudentsCategory(studentList);
        return studentList;
    }

    public ArrayList<Student> calculateStudentsCategory(ArrayList<Student> students) {

        for (Student student : studentList) {

            assignSingleStudent(student);

        }
        return students;
    }

    public void assignSingleStudent(Student student) {
        // Get votes from student in priority
        SubjectInterface s11 = student.getVote().getFirstPrio().get(0);
        SubjectInterface s12 = student.getVote().getFirstPrio().get(1);
        SubjectInterface s21 = student.getVote().getSecondPrio().get(0);
        SubjectInterface s22 = student.getVote().getSecondPrio().get(1);
        
        //Category D cases
        //If only one of a students topic is sat, he is in the D category and
        //we dont need to look further.
        if (student.getTopicA() == null || student.getTopicB() == null) {
            System.out.println(student.getName());
            student.setCategory(CategoryEnum.D);
        } 
        //Category A cases
        //If both topic is sat and equals either one of his first prioity.
        else if ((student.getTopicA().equals(s11.getTopic()) && student.getTopicB().equals(s12.getTopic()))
                || (student.getTopicB().equals(s11.getTopic()) && student.getTopicA().equals(s12.getTopic()))) {
            student.setCategory(CategoryEnum.A);
        } 
        //Category C cases
        //If both topic is sat and equals either one of his second prioity.
        else if ((student.getTopicA().equals(s21.getTopic()) && student.getTopicB().equals(s22.getTopic()))
                || (student.getTopicB().equals(s21.getTopic()) && student.getTopicA().equals(s22.getTopic()))) {
            student.setCategory(CategoryEnum.C);
        } 
        //Category B cases
        //else B
        else if (student.getTopicA() != null && student.getTopicB() != null) {
            student.setCategory(CategoryEnum.B);
        }
    }

}
