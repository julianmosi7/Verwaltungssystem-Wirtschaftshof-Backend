package com.example.backend_verwaltungssoftware.Authentication;

public class JwtTokenResource {
    private final String jwttoken;

    public JwtTokenResource(String jwttoken){
        this.jwttoken = jwttoken;
    }

    public String getToken(){
        return this.jwttoken;
    }
}
