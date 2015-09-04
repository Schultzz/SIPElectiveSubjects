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
public class Subject implements SubjectInterface{
    
    private String topic;
    
    public Subject(String topic){
        this.topic = topic;
    }

    @Override
    public String getTopic() {
        return this.topic;
    }
    
}
