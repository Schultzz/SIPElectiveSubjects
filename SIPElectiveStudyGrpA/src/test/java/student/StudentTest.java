/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import dk.cphbussines.entity.Student;
import dk.cphbussines.entity.Subject;
import dk.cphbussines.entity.SubjectInterface;
import dk.cphbussines.entity.Vote;
import dk.cphbussines.namemeplease.TextReader;
import java.util.Scanner;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class StudentTest {

    @Test
    public void createNewPersonTest() {
        //Test for getters ???? plz no

        String name = "Lars";
        String testTopic11 = "Android";
        String testTopic12 = "C#";
        String testTopic21 = "Arduino";
        String testTopic22 = "AI";
        SubjectInterface subject11 = new Subject(testTopic11);
        SubjectInterface subject12 = new Subject(testTopic12);
        SubjectInterface subject21 = new Subject(testTopic21);
        SubjectInterface subject22 = new Subject(testTopic22);
        Vote vote = new Vote(subject11, subject12, subject21, subject22);
        Student student1 = new Student(name, vote);
        assertThat(student1.getName(), is(name));
        assertThat(student1.getVote().getFirstPrio().get(0).getTopic(), is(testTopic11));
        assertThat(student1.getVote().getFirstPrio().get(1).getTopic(), is(testTopic12));
        assertThat(student1.getVote().getSecondPrio().get(0).getTopic(), is(testTopic21));
        assertThat(student1.getVote().getSecondPrio().get(1).getTopic(), is(testTopic22));

    }

    @Test
    public void addVoteToStudentTest() {
        String name = "Lars";
        Vote vote = new Vote(null, null, null, null);
        Student student = new Student(name, vote);
        assertThat(student.getVote(), is(vote));

    }

}
