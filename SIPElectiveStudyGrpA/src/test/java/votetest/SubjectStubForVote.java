/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votetest;

import dk.cphbussines.entity.SubjectInterface;

/**
 *
 * @author Søren
 */
public class SubjectStubForVote implements SubjectInterface{

    private String topic = "Android";
    
    @Override
    public String getTopic() {
        return topic;
    }
    
}
