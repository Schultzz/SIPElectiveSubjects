/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import dk.cphbussines.entity.CategoryEnum;
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
        Subject subject11 = new Subject(testTopic11);
        Subject subject12 = new Subject(testTopic12);
        Subject subject21 = new Subject(testTopic21);
        Subject subject22 = new Subject(testTopic22);
        Vote vote = new Vote(subject11, subject12, subject21, subject22);
        Student student1 = new Student(name, vote);
        assertThat(student1.getName(), is(name));
        assertThat(student1.getVote().getFirstPrio().get(0).getTopic(), is(testTopic11));
        assertThat(student1.getVote().getFirstPrio().get(1).getTopic(), is(testTopic12));
        assertThat(student1.getVote().getSecondPrio().get(0).getTopic(), is(testTopic21));
        assertThat(student1.getVote().getSecondPrio().get(1).getTopic(), is(testTopic22));

        assertThat(student1.getTopicA(), is(nullValue()));
        assertThat(student1.getTopicB(), is(nullValue()));

        assertThat(student1.getCategory(), is(CategoryEnum.D));

    }

    @Test
    public void addVoteToStudentTest() {
        String name = "Lars";
        Vote vote = new Vote(null, null, null, null);
        Student student = new Student(name, vote);
        assertThat(student.getVote(), is(vote));

    }

    @Test
    public void assignTopicToStudent() {
        String topicA = "Node";
        String topicB = "Java";
        String name = "Kurt";

        Student student1 = new Student(name, null);

        student1.setTopicA(topicA);
        student1.setTopicB(topicB);

        assertThat(student1.getTopicA(), is(topicA));
        assertThat(student1.getTopicB(), is(topicB));

    }

    @Test
    public void assignCategoryToStudent() {

        String name = "Kurt";

        Student student = new Student(name, null);

        student.setCategory(CategoryEnum.A);
        
        assertThat(student.getCategory(), is(CategoryEnum.A));
        
    }

}
