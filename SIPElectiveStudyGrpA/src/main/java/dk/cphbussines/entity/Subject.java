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
    private String pool;
       
    public Subject(String topic){
        this.topic = topic;
        this.pool = null;
    }

    public Subject(String topic, String pool) {
        this.topic = topic;
        this.pool = pool;
    }
    
    @Override
    public String getTopic() {
        return this.topic;
    }
    
    public String getPool(){
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }
    
    
}
