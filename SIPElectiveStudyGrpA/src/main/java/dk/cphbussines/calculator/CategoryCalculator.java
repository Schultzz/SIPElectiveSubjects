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

    private final ArrayList<Student> studentList;
    private final ArrayList<Subject> subjectList;

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
    
    public ArrayList<Student> assignTopicsToStudents() {

        for (Student student : studentList) {
            //For each student in the list do stuff:
            int studentScore = 0;

            SubjectInterface s11 = student.getVote().getFirstPrio().get(0);
            SubjectInterface s12 = student.getVote().getFirstPrio().get(1);
            SubjectInterface s21 = student.getVote().getSecondPrio().get(0);
            SubjectInterface s22 = student.getVote().getSecondPrio().get(1);

            int poolCounter = 0;
            //Testing for subject11
            while (student.getTopicA() == null && student.getTopicB() == null && poolCounter < subjectList.size()) {

                if (s11.getTopic().equals(subjectList.get(poolCounter).getTopic())) {
                    Subject tempSubject = subjectList.get(poolCounter);

                    if (tempSubject.getPool().equals("A")) {
                        student.setTopicA(subjectList.get(poolCounter).getTopic());
                        studentScore += 5;
                    } else {
                        student.setTopicB(subjectList.get(poolCounter).getTopic());
                        studentScore += 5;
                    }

                }
                poolCounter++;
            }
            //Subject11 done

            //Subject12 start
            poolCounter = 0;
            if (student.getTopicA() == null) {
                //do topic A stuff
                while (student.getTopicA() == null && poolCounter < subjectList.size()) {

                    if (s12.getTopic().equals(subjectList.get(poolCounter).getTopic())) {
                        Subject tempSubject = subjectList.get(poolCounter);

                        if (tempSubject.getPool().equals("A")) {
                            student.setTopicA(subjectList.get(poolCounter).getTopic());
                            studentScore += 5;
                        }

                    }
                    poolCounter++;
                }
                poolCounter = 0;
            } else {
                //else do topic B stuff
                while (student.getTopicB() == null && poolCounter < subjectList.size()) {

                    if (s12.getTopic().equals(subjectList.get(poolCounter).getTopic())) {
                        Subject tempSubject = subjectList.get(poolCounter);

                        if (tempSubject.getPool().equals("B")) {
                            student.setTopicB(subjectList.get(poolCounter).getTopic());
                            studentScore += 5;
                        }

                    }
                    poolCounter++;
                }
            }
            //Subject12 done

            //Second prios starting
            //Subject21 start
            if (student.getTopicA() == null || student.getTopicB() == null) {
                //do stuff
                poolCounter = 0;
                if (student.getTopicA() == null) {
                    while (student.getTopicA() == null && poolCounter < subjectList.size()) {
                        if (s21.getTopic().equals(subjectList.get(poolCounter).getTopic())) {
                            Subject tempSubject = subjectList.get(poolCounter);

                            if (tempSubject.getPool().equals("A")) {
                                student.setTopicA(subjectList.get(poolCounter).getTopic());
                                studentScore += 3;
                            }
                        }
                        poolCounter++;
                    }
                } 
                poolCounter = 0;
                if (student.getTopicB() == null) {
                    while (student.getTopicB() == null && poolCounter < subjectList.size()) {
                        if (s21.getTopic().equals(subjectList.get(poolCounter).getTopic())) {

                            Subject tempSubject = subjectList.get(poolCounter);

                            if (tempSubject.getPool().equals("B")) {
                                student.setTopicB(subjectList.get(poolCounter).getTopic());
                                studentScore += 3;
                            }
                        }
                        poolCounter++;
                    }
                }
            }
            //
            //Subject21 end

            //Subject 22 start
            if (student.getTopicA() == null || student.getTopicB() == null) {

                poolCounter = 0;
                if (student.getTopicA() == null) {
                    while (student.getTopicA() == null && poolCounter < subjectList.size()) {
                        if (s22.getTopic().equals(subjectList.get(poolCounter).getTopic())) {
                            Subject tempSubject = subjectList.get(poolCounter);

                            if (tempSubject.getPool().equals("A")) {
                                student.setTopicA(subjectList.get(poolCounter).getTopic());
                                studentScore += 3;
                            }
                        }
                        poolCounter++;
                    }
                    
                } 
                poolCounter = 0;
                if (student.getTopicB() == null) {
                    while (student.getTopicB() == null && poolCounter < subjectList.size()) {
                        if (s22.getTopic().equals(subjectList.get(poolCounter).getTopic())) {
                            Subject tempSubject = subjectList.get(poolCounter);

                            if (tempSubject.getPool().equals("B")) {
                                student.setTopicB(subjectList.get(poolCounter).getTopic());
                                studentScore += 3;
                            }
                        }
                        poolCounter++;
                    }
                }
                //Subject 22 end
            }
            System.out.println("Student" + student.getName() + " - score " + studentScore);
            // Calculates the studens Category from the studentscore
            if (studentScore == 10) {
                student.setCategory(CategoryEnum.A);
            } else if (studentScore == 8) {
                student.setCategory(CategoryEnum.B);
            } else if (studentScore == 6) {
                student.setCategory(CategoryEnum.C);
            } else {
                student.setCategory(CategoryEnum.D);
            }

        }

        return studentList;
    }
}
