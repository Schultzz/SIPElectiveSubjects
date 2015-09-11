/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbussines.namemeplease;

import dk.cphbussines.entity.Student;
import dk.cphbussines.entity.Subject;
import dk.cphbussines.entity.Vote;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Søren
 */
public class TextReader {

    private File root = new File(getClass().getResource("/").getPath() + "students.txt");

    public TextReader() {
    }

    public static Scanner loadStudentWishesFromFile(String fileName) {
        //Method that creates a scanner object on a given path
        Scanner scan = null;
        try {
//            scan = new Scanner(new File(fileName));

            //System.out.println(new File(TextReader.class.getResource("/").getPath() + fileName).toString());
            scan = new Scanner(new File(fileName));
        } catch (FileNotFoundException ex) {
         //   Logger.getLogger(TextReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scan;
    }

    public static String StringFromFile(String fileName) {
        //Method that takes a scanner object and loops over every line and concatinate them into one string, with newlines.
        Scanner scan = loadStudentWishesFromFile(fileName);
        String str = "";
        while (scan.hasNext()) {
            str += scan.nextLine() + "\n";
        }

        return str;
    }

    public static Boolean validateInput(String str) {
        //Method that validates a given string, to ensure that the string contains the right input.
        String[] lines = str.split(";");
        Boolean validate = true;
        String[] lineParameters;
        for (String line : lines) {
            lineParameters = line.split(",");

            if (lineParameters.length != 5) {
                validate = false;
                break;
            }

        }
        return validate;
    }

    public static ArrayList<Student> loadListOfStudents(String fileName) {
        //Method that creates a list of students
        ArrayList<Student> students = new ArrayList<>();
        String str = TextReader.StringFromFile(fileName);//Uses the method that returns a complete string with newlines for each studentline.
        if (validateInput(str)) {//Uses the validation method to check that the input is valid.
            String[] lines = str.split(";");
            String[] lineParameters;
            for (String line : lines) {
                lineParameters = line.split(",");
                Vote vote = new Vote(new Subject(lineParameters[1]), new Subject(lineParameters[2]), new Subject(lineParameters[3]), new Subject(lineParameters[4]));
                Student newStudent = new Student(lineParameters[0], vote);
                students.add(newStudent);
            }
        }
        System.out.println(students.size());
        return students;
    }

    public static Boolean savePoolsToFile(String filePath, List<Subject> array) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Filen eksisterer ikke, vi laver den nu");
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            String result = "";
            for (Subject subject : array) {
                result += subject.toString();       
            }
            System.out.println(result);
            System.out.println(file.getPath());
            bw.write(result);
            bw.close();          
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
