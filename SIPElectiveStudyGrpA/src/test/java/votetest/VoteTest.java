/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votetest;

import dk.cphbussines.entity.Subject;
import dk.cphbussines.entity.SubjectInterface;
import dk.cphbussines.entity.Vote;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import org.junit.Test;
import static org.junit.Assert.*;
//import org.jmock.Expectations;
//import org.jmock.Mockery;
//import org.jmock.integration.junit4.JUnitRuleMockery;

/**
 *
 * @author Søren
 */
public class VoteTest {
    
    
    @Test
    public void testCreateNewVote(){
       
        SubjectInterface subject11 = new SubjectStubForVote();
        SubjectInterface subject12 = new SubjectStubForVote();
        SubjectInterface subject21 = new SubjectStubForVote();
        SubjectInterface subject22 = new SubjectStubForVote();
        Vote vote = new Vote(subject11, subject12, subject21, subject22);
        assertThat(vote.getFirstPrio().get(0), is(subject11));
        assertThat(vote.getFirstPrio().get(1), is(subject12));
        assertThat(vote.getSecondPrio().get(0), is(subject21));
        assertThat(vote.getSecondPrio().get(1), is(subject22));
        
    }
    
    @Test
    public void testSetSubjectTopicWithStub(){
        
        SubjectInterface subject11 = new SubjectStubForVote();
        SubjectInterface subject12 = new SubjectStubForVote();
        SubjectInterface subject21 = new SubjectStubForVote();
        SubjectInterface subject22 = new SubjectStubForVote();
        Vote vote = new Vote(subject11, subject12, subject21, subject22);
        String testTopic = "Android";
        assertThat(vote.getFirstPrio().get(0).getTopic(), is(testTopic));
        assertThat(vote.getFirstPrio().get(1).getTopic(), is(testTopic));
        assertThat(vote.getSecondPrio().get(0).getTopic(), is(testTopic));
        assertThat(vote.getSecondPrio().get(1).getTopic(), is(testTopic)); 
        
    }
    
    @Test
    public void testTopicAmount(){
        
        SubjectInterface subject11 = new SubjectStubForVote();
        SubjectInterface subject12 = new SubjectStubForVote();
        SubjectInterface subject21 = new SubjectStubForVote();
        SubjectInterface subject22 = new SubjectStubForVote();
        Vote vote = new Vote(subject11, subject12, subject21, subject22);
        assertThat(vote.getFirstPrio().size(), is(2));
        assertThat(vote.getSecondPrio().size(), is(2));
        assertThat(vote.getFirstPrio().get(0), is(not(nullValue())));
        assertThat(vote.getFirstPrio().get(1), is(not(nullValue())));
        assertThat(vote.getSecondPrio().get(0), is(not(nullValue())));
        assertThat(vote.getSecondPrio().get(1), is(not(nullValue())));
        
        
    }
    
    @Test
    public void testTopicNames(){
        
        String testTopic11 = "Android";
        String testTopic12 = "C#";
        String testTopic21 = "Arduino";
        String testTopic22 = "AI";
        SubjectInterface subject11 = new Subject(testTopic11);
        SubjectInterface subject12 = new Subject(testTopic12);
        SubjectInterface subject21 = new Subject(testTopic21);
        SubjectInterface subject22 = new Subject(testTopic22);
        Vote vote = new Vote(subject11, subject12, subject21, subject22);
        
        assertThat(vote.getFirstPrio().get(0).getTopic(), is(testTopic11));
        assertThat(vote.getFirstPrio().get(1).getTopic(), is(testTopic12));
        assertThat(vote.getSecondPrio().get(0).getTopic(), is(testTopic21));
        assertThat(vote.getSecondPrio().get(1).getTopic(), is(testTopic22)); 
        
    }
    
    
}
