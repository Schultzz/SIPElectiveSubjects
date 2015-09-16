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
    private int priority1;
    private int priority2;
       
    public Subject(String topic){
        this.topic = topic;
        this.pool = null;
    }

    public Subject(String topic, String pool) {
        this.topic = topic;
        this.pool = pool;
        this.priority1 = 0;
        this.priority2 = 0;
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

    public int getPriority1() {
        return priority1;
    }

    public int getPriority2() {
        return priority2;
    }
    
    public void priority1Increment(){
        priority1++;
    }
    
    public void priority2Increment(){
        priority2++;
    }

    @Override
    public String toString() {
        return topic+","+pool+"\n";
    }
    
    
}
