package org.practice.pojoClasses.apiPojo;

public class LoginResponse {

    private String token;
//    public LoginResponse(String token){
//        this.token=token;
//    }
    public String getToken(){
        return token;
    }
    public String setToken(String token){
        this.token=token;
        return token;
    }
}
