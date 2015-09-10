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

}
