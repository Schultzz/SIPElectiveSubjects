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

    private Vote vote;

    public Student(String name, Vote vote) {
        this.name = name;
        this.vote = vote;
    }

    public String getName() {
        return name;
    }

    public Vote getVote() {
        return vote;
    }


}
