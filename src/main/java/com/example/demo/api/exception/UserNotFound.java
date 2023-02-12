package com.example.demo.api.exception;

public class UserNotFound extends Exception{
    public UserNotFound(String message){
        super(message);
    }
}