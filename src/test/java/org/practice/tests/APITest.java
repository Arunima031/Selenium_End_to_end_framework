package org.practice.tests;


import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.practice.base.ApiBaseTest;
import org.practice.pojoClasses.*;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class APITest extends ApiBaseTest {
    private static final Logger LoggerUtil = LogManager.getLogger(APITest.class);


    CreateUserRequest userRequest=new CreateUserRequest();


    @Test(groups="API")
    public void testGETUser(){

        given().header("Autherization","Bearer : "+token).when().get("/users/2")
                .then().statusCode(200)
                .body("data.email", containsString("@reqres.in"));
   }

   @Test
    public void testCreateUser(){
     userRequest.setName("Arunima");
     userRequest.setJob("QA");
       UserResponse userRes = given().header("Autherization", "Bearer : " + token).contentType(ContentType.JSON)
               .body(userRequest)
               .when().post("/users").then().statusCode(201).extract().as(UserResponse.class);
       assertEquals(userRes.getName(), userRequest.getName());
       assertEquals(userRes.getJob(), userRequest.getJob());
       assertNotNull(userRes.getId());
       assertNotNull(userRes.getCreatedAt());

   }

   @Test
    public void complexJsonTest(){
       Address address= new Address();
       address.setStreet("221B Baker Street");
       address.setCity("London");
       address.setZipCode("NW1");

       UserProfileRequest userProfileRequest=new UserProfileRequest();
       userProfileRequest.setAddress(address);
       userProfileRequest.setName("Arunima");
       userProfileRequest.setJob("QA");
       userProfileRequest.setSkills(Arrays.asList("Java", "Selenium", "Rest Assured"));

       UserProfileResponse res = given().contentType(ContentType.JSON).body(userProfileRequest).when()
               .post("https://your-api.com/api/users").then().statusCode(201).extract().as(UserProfileResponse.class);
       assertEquals(res.getAddress(),userProfileRequest.getAddress());
       assertEquals(res.getJob(),userProfileRequest.getJob());
    }

}
