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
public interface VoteInterface  {
    
      ArrayList<SubjectInterface> getFirstPrio();
      ArrayList<SubjectInterface> getSecondPrio();
    
    
}
