/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subject;

import dk.cphbussines.entity.Subject;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ms
 */
public class SubjectTest {

    @Test
    public void testCreateNewSubject() {
        String topic = "Angular";
        Subject subject = new Subject(topic);

        assertThat(subject.getTopic(), is(topic));
        assertThat(subject.getPool(), is(nullValue()));

    }
    
    @Test
    public void testCreateNewSubjectWithPool(){
        
        String topic = "Android";
        String pool = "A";
        Subject subject = new Subject(topic, pool);
        assertThat(subject.getTopic(), is(topic));
        assertThat(subject.getPool(), is(pool));
        
    }
    
    @Test
    public void setPool(){
        
        Subject subject = new Subject(null);
        String pool = "A";
        subject.setPool(pool);
        assertThat(subject.getPool(), is(pool));
        
    }
    
    @Test
    public void testCreateNewSubjectWithoutPriority(){
        /*
        Denne test tester om et nyt subject object bliver lavet med 0 votes
        på hver prioritets variabel som subject indeholder.
        */
        String topic = "";
        String pool = "";
        Subject subject = new Subject(topic, pool);
        
        assertThat(subject.getPriority1(), is(0));
        assertThat(subject.getPriority2(), is(0));
    }
    
    @Test
    public void testIncrementSubjectPriority(){
        /*
        Tester at subject klassen incrementere variablerne priority1 og
        priority2 med 1 hver gang medtoderne priority1_Increment og
        priority2_Increment bliver kaldt.
        */
        String topic = "";
        String pool = "";
        Subject subject = new Subject(topic, pool);
        
        subject.priority1Increment();
        assertThat(subject.getPriority1(), is(1));
        
        subject.priority2Increment();
        assertThat(subject.getPriority2(), is(1));
        
        subject.priority1Increment();
        assertThat(subject.getPriority1(), is(2));
        
        subject.priority2Increment();
        assertThat(subject.getPriority2(), is(2));
    }
    
    
}
