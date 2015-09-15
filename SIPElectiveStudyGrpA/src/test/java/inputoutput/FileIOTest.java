/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputoutput;

import dk.cphbussines.entity.Student;
import dk.cphbussines.entity.Subject;
import dk.cphbussines.namemeplease.TextReader;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Søren
 */
public class FileIOTest {

    @Test
    public void testClass() {
        TextReader textReader = new TextReader();

        assertThat(textReader, is(not(nullValue())));
    }

    @Test
    public void testFileConnection() {
        //Method to test that a file is loaded corretly, so that a valid scanner object is returned
        Scanner scan = TextReader.loadStudentWishesFromFile("students.txt");
        assertThat(scan, is(not(nullValue())));

        scan = TextReader.loadStudentWishesFromFile("notfound.txt");
        assertThat(scan, is(nullValue()));

    }

    @Test
    public void testFileLine() {
        //Method to test that a scanner object has more than one line
        Scanner scan = TextReader.loadStudentWishesFromFile("students.txt");
        assertThat(scan.hasNext(), is(true));

    }

    @Test
    public void testStringHasComma() {
        //Method to test that a the returned string contains commas
        String str = TextReader.StringFromFile("students.txt");
        assertThat(str.contains(","), is(true));
    }

    @Test
    public void testStringHasValidInput() {
        //Method to test the validation method, to ensure that the given file contains the right format in order to create students.
        Boolean validate = TextReader.validateInput(TextReader.StringFromFile("students.txt"));
        assertThat(validate, is(true));

        validate = TextReader.validateInput(TextReader.StringFromFile("studentswishesWrongFormat.txt"));
        assertThat(validate, is(false));
    }

    @Test
    public void testStudentCreation() {
        //Method to test that the studentcreation method returns a list with students
        ArrayList<Student> students = TextReader.loadListOfStudents("students.txt");
        assertThat(students.size(), is(not(0)));
    }

    @Test
    public void testSaveSubjects() {
        Subject subject12 = new Subject("C#", "A");
        Subject subject21 = new Subject("Python", "B");
        Subject subject22 = new Subject("Android", "A");
        Subject subject31 = new Subject("SW Design", "B");
        Subject subject32 = new Subject("Games", "B");
        Subject subject41 = new Subject("Databases", "A");
        Subject subject42 = new Subject("Test", "A");
        Subject subject11 = new Subject("Arduino", "B");

        ArrayList<Subject> subjects = new ArrayList();
        subjects.add(subject11);
        subjects.add(subject12);
        subjects.add(subject21);
        subjects.add(subject22);
        subjects.add(subject31);
        subjects.add(subject32);
        subjects.add(subject41);
        subjects.add(subject42);
        assertThat(TextReader.savePoolsToFile("pools.txt", subjects), is(true));
        assertThat(TextReader.savePoolsToFile("findesIkke.txt", subjects), is(false));
    }
    
    @Test
    public void testStringFromPools()
    {
    String str = TextReader.StringFromPools("pools.txt");
    assertThat(str.contains(","), is(true));
        
    }
    
    
    @Test
    public void testValidateInputFromPool() {
        //Method to test the validation method, to ensure that the given file contains the right format in order to create students.
        Boolean validate = TextReader.validateInputFromPool(TextReader.StringFromPools("pools.txt"));
        assertThat(validate, is(true));

       validate = TextReader.validateInputFromPool(TextReader.StringFromPools("poolsWrongFormat.txt"));
        assertThat(validate, is(false));
    }
    
     @Test
    public void testPoolCreation() {
        //Method to test that the studentcreation method returns a list with students
        ArrayList<Subject> sub = TextReader.loadListOfPools("pools.txt");
        assertThat(sub.size(), is(not(0)));
    }
}
