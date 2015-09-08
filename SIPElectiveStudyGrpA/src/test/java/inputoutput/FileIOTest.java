/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputoutput;

import dk.cphbussines.entity.Student;
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
    public void testClass(){
        TextReader textReader = new TextReader();
        
        assertThat(textReader, is(not(nullValue())));
    }

    @Test
    public void testFileConnection() {
        //Method to test that a file is loaded corretly, so that a valid scanner object is returned
        Scanner scan = TextReader.loadStudentWishesFromFile("studentswishes.txt");
        assertThat(scan, is(not(nullValue())));

        scan = TextReader.loadStudentWishesFromFile("notfound.txt");
        assertThat(scan, is(nullValue()));

    }

    @Test
    public void testFileLine() {
        //Method to test that a scanner object has more than one line
        Scanner scan = TextReader.loadStudentWishesFromFile("studentswishes.txt");
        assertThat(scan.hasNext(), is(true));

    }

    @Test
    public void testStringHasComma() {
        //Method to test that a the returned string contains commas
        String str = TextReader.StringFromFile("studentswishes.txt");
        assertThat(str.contains(","), is(true));
    }

    @Test
    public void testStringHasValidInput() {
        //Method to test the validation method, to ensure that the given file contains the right format in order to create students.
        Boolean validate = TextReader.validateInput(TextReader.StringFromFile("studentswishes.txt"));
        assertThat(validate, is(true));

        validate = TextReader.validateInput(TextReader.StringFromFile("studentswishesWrongFormat.txt"));
        assertThat(validate, is(false));
    }

    @Test
    public void testStudentCreation() {
        //Method to test that the studentcreation method returns a list with students
        ArrayList<Student> students = TextReader.loadListOfStudents("studentswishes.txt");
        assertThat(students.size(), is(not(0)));

    }

}
