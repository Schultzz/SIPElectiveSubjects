/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;


import dk.cphbussines.entity.Student;
import dk.cphbussines.namemeplease.TextReader;
import java.util.Scanner;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class StudentTest {
    
    @Test
    public void createNewPersonTest(){
        //Test for getters ???? plz no
        String name = "Per";
        String subject11 = "Tysk";
        String subject12 = "Svensk";
        String subject21 = "Fransk";
        String subject22 = "Dansk";
        Student student1 = new Student(name, subject11, subject12, subject21, subject22);
        assertThat(student1.getName(), is(name));
        assertThat(student1.getSubject11(), is(subject11));
        assertThat(student1.getSubject12(), is(subject12));
        assertThat(student1.getSubject21(), is(subject21));
        assertThat(student1.getSubject22(), is(subject22));
        
        
    }
    
    
    
}
