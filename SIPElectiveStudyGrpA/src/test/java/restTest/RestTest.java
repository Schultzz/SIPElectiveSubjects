/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restTest;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import com.google.gson.*;


import static com.jayway.restassured.RestAssured.get; 
/*import org.json.JSONArray; 
import org.json.JSONException; 
import org.testng.Assert; 
import org.testng.annotations.Test; 
*/
import com.jayway.restassured.response.Response; 
import dk.cphbussines.entity.Subject;
import java.util.List;

/**
 *
 * @author nikolai
 */
public class RestTest {
    

   @Test 
    public void getSubjectsForPoolSelectionTest(){
//        Gson gson =new Gson();
//        Response resp = get("http://localhost:8085/SIPElectiveStudyGrpA/api/subject");     
//        //JSONArray jsonResponse = new JSONArray(resp.asString()); 
//        List<Subject> subjects = (List) gson.fromJson(resp.asString(), Subject.class);
//        assertThat(subjects.get(0).getTopic(),is("Android"));
//        assertThat(subjects.get(1).getTopic(),is("C#"));
//        assertThat(subjects.get(2).getTopic(),is("COBOL"));
        
    }

  
}
