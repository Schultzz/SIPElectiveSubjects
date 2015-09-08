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
import com.google.gson.reflect.TypeToken;


import static com.jayway.restassured.RestAssured.get; 
/*import org.json.JSONArray; 
import org.json.JSONException; 
import org.testng.Assert; 
import org.testng.annotations.Test; 
*/
import com.jayway.restassured.response.Response; 
import dk.cphbussines.entity.Subject;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author nikolai
 */
public class RestTest {
    

   @Test 
    public void getSubjectsForPoolSelectionTest(){
        Gson gson =new Gson();
        Response resp = get("http://localhost:8080/SIPElectiveStudyGrpA/api/subject");     
        
        Type type = new TypeToken<List<Subject>>(){}.getType();
        List<Subject> subjects = gson.fromJson(resp.asString(), type);
        
        assertThat(subjects.get(0).getTopic(),is("Android"));
        assertThat(subjects.get(1).getTopic(),is("C#"));
        assertThat(subjects.get(2).getTopic(),is("COBOL"));
        
    }

  
}
