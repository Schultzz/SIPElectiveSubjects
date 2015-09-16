/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbussines.namemeplease;

import dk.cphbussines.entity.Student;
import dk.cphbussines.entity.Subject;
import dk.cphbussines.entity.Vote;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    private File root = new File(getClass().getResource("/").getPath() + "students.csv");

    public TextReader() {
    }

    // rename den her 
    public static Scanner loadStudentWishesFromFile(String fileName) {
        //Method that creates a scanner object on a given path
        Scanner scan = null;
        try {
//            scan = new Scanner(new File(fileName));

            scan = new Scanner(new File(fileName));
        } catch (FileNotFoundException ex) {
            //   Logger.getLogger(TextReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scan;
    }

    public static String StringFromFile(String fileName) {
        System.out.println(fileName);
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
        String[] lines = str.split("\n");
       // System.out.println("lines array:"+lines.length);
        Boolean validate = true;
        String[] lineParameters;
        int countFor =0;
        for (String line : lines) {
            if(countFor > 1){
                lineParameters = line.split(",");

                if (lineParameters.length != 5) {
                    validate = false;
                    break;
                }
            }
            countFor++;
            

        }
        return validate;
    }
 

    public static ArrayList<Student> loadListOfStudents(String fileName) {
        //Method that creates a list of students
        ArrayList<Student> students = new ArrayList<>();
        String str = TextReader.StringFromFile(fileName);//Uses the method that returns a complete string with newlines for each studentline.
        if (validateInput(str)) {//Uses the validation method to check that the input is valid.
            String[] lines = str.split("\n");
            String[] lineParameters;
            int countFor = 0;
            for (String line : lines) {
                if(countFor > 1){
                    lineParameters = line.split(",");
                    Vote vote = new Vote(new Subject(lineParameters[1]), new Subject(lineParameters[2]), new Subject(lineParameters[3]), new Subject(lineParameters[4]));
                    Student newStudent = new Student(lineParameters[0], vote);
                    students.add(newStudent);
                }
                countFor++;
            }
        }

        return students;
    }

    public static boolean savePoolsToFile(String filePath, List<Subject> array) {
        try {
            File file = new File(filePath);
            
            /*if (!file.exists()) {
                System.out.println("Filen eksisterer ikke, vi laver den nu");
                file.createNewFile();
            }
*/
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            String result = "sep=, \n";
            result += "Fag,Pool \n";
            for (Subject subject : array) {
                result += subject.toString();
            }

            bw.write(result.substring(0, result.length() - 1));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static String StringFromPools(String fileName) {
        Scanner scan = loadStudentWishesFromFile(fileName);
        String str = "";
       while (scan.hasNext()) {
            str += scan.nextLine()+"\n";
       }
        return str;

    }
    
     public static Boolean validateInputFromPool(String str) {
        //Method that validates a given string, to ensure that the string contains the right input.
        String[] lines = str.split("\n");
        Boolean validate = true;
        String[] lineParameters;
        int countFor =0;
        for (String line : lines) {
            if(countFor > 1){
                lineParameters = line.split(",");
                if (lineParameters.length != 2) {
                    validate = false;
                    break;
                }
            }
            
            countFor++;
        }
        return validate;
    }
    
    public static ArrayList<Subject> loadListOfPools(String fileName) {
        //Method that creates a list of students
        ArrayList<Subject> subject = new ArrayList<>();
        String str = TextReader.StringFromPools(fileName);//Uses the method that returns a complete string with newlines for each studentline.
        if (validateInputFromPool(str)) {//Uses the validation method to check that the input is valid.
            String[] lines = str.split("\n");
            String[] lineParameters;
            int countFor = 0;
            for (String line : lines) {
                if(countFor > 1){
                    lineParameters = line.split(",");
                    Subject sub = new Subject(lineParameters[0],lineParameters[1]);
                    subject.add(sub);
                }
                countFor++;
            }
        }

        return subject;
    }
    
    public boolean savingVoteSecondRound(Student stu,String filename)
    {
       try {
            File file = new File(filename);
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
          
           // System.out.println("stu.getVote().getFirstPrio().get(0)"+ stu.getVote().getFirstPrio().get(0).getTopic());
            
            String result = stu.getName()+","+stu.getVote().getFirstPrio().get(0).getTopic()+","+stu.getVote().getSecondPrio().get(0).getTopic();
            result += ","+stu.getVote().getFirstPrio().get(1).getTopic()+","+stu.getVote().getSecondPrio().get(1).getTopic() +"\n";
            bw.write(result);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
