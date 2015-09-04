/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbussines.namemeplease;

import dk.cphbussines.entity.Student;
import dk.cphbussines.entity.Subject;
import dk.cphbussines.entity.Vote;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Søren
 */
public class TextReader {

    public static Scanner loadStudentWishesFromFile() {
        //Method that creates a scanner object on a given path
        Scanner scan = null;
        try {
            scan = new Scanner(new File("studentswishes.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scan;
    }

    public static String StringFromFile() {
        //Method that takes a scanner object and loops over every line and concatinate them into one string, with newlines.
        Scanner scan = loadStudentWishesFromFile();
        String str = "";
        while (scan.hasNext()) {
            str += scan.nextLine() + "\n";
        }

        return str;
    }

    public static Boolean validateInput(String str) {
        //Method that validates a given string, to ensure that the string contains the right input.
        String[] lines = str.split("\n");
        Boolean validate = true;
        String[] lineParameters;
        for (String line : lines) {

            lineParameters = line.split(",");

            if (lineParameters.length != 5) {
                validate = false;
                break;
            } else {
                for (String lineParameter : lineParameters) {
                    if (lineParameter.length() == 0) {
                        validate = false;
                        break;
                    }
                }

            }

        }
        return validate;
    }

    public static ArrayList<Student> loadListOfStudents() {
        //Method that creates a list of students
        ArrayList<Student> students = new ArrayList<>();
        String str = TextReader.StringFromFile();//Uses the method that returns a complete string with newlines for each studentline.
        if (validateInput(str)) {//Uses the validation method to check that the input is valid.
            String[] lines = str.split("\n");
            String[] lineParameters;
            for (String line : lines) {
                lineParameters = line.split(",");
                Vote vote = new  Vote(new Subject(lineParameters[1]), new Subject(lineParameters[2]),new Subject(lineParameters[3]), new Subject(lineParameters[4]));
                Student newStudent = new Student(lineParameters[0],vote);
                students.add(newStudent);
            }
        }
    
        
    return students;
    }

}
