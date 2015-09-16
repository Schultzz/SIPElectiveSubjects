/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbussines.entity;

import java.util.ArrayList;

/**
 *
 * @author Søren
 */
public class Vote implements VoteInterface{

    private ArrayList<SubjectInterface> firstPrio = new ArrayList<>();
    private ArrayList<SubjectInterface> secondPrio = new ArrayList<>();

    public Vote(SubjectInterface subject11, SubjectInterface subject12, SubjectInterface subject21, SubjectInterface subject22) {

        this.firstPrio.add(subject11);
        this.firstPrio.add(subject12);
        this.secondPrio.add(subject21);
        this.secondPrio.add(subject22);
    }

    @Override
    public ArrayList<SubjectInterface> getFirstPrio() {
     return firstPrio;
    }

    @Override
    public ArrayList<SubjectInterface> getSecondPrio() {
     return  secondPrio;
    }
    
    
    

  

}
