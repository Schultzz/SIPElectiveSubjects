/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbussines.entity;

/**
 *
 * @author Søren
 */
public class Student {
    
    private String name;
    private String subject11;
    private String subject12;
    private String subject21;
    private String subject22;
    
    public Student(String name, String subject11, String subject12, String subject21, String subject22){
        this.name = name;
        this.subject11 = subject11;
        this.subject12 = subject12;
        this.subject21 = subject21;
        this.subject22 = subject22;
    }

    public String getName() {
        return name;
    }

    public String getSubject11() {
        return subject11;
    }

    public String getSubject12() {
        return subject12;
    }

    public String getSubject21() {
        return subject21;
    }

    public String getSubject22() {
        return subject22;
    }
    
    
    
}
