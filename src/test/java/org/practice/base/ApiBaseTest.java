package org.practice.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.practice.pojoClasses.LoginRequest;
import org.practice.pojoClasses.LoginResponse;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class ApiBaseTest {

    protected final String baseUrl = "https://reqres.in/api";
    protected String token;
    LoginRequest longinRequest = new LoginRequest();
    LoginResponse longinResponse = new LoginResponse();

            @BeforeClass
            public void setUp(){

                RestAssured.baseURI=baseUrl;
                longinRequest.setEmail("eve.holt@reqres.in");
                longinRequest.setPassword("cityslicka");
                Response res = given().contentType(ContentType.JSON).body(longinRequest)
                        .when().post("/login").then().statusCode(200).extract().response();
//                token=longinResponse.setToken(res.path("token"));
                res.jsonPath().getString("token");
            }
        }



