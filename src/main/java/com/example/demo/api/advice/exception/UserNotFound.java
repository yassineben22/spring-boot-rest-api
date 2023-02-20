package com.example.demo.api.advice.exception;

public class UserNotFound extends Exception{
    public UserNotFound(String message){
        super(message);
    }
}