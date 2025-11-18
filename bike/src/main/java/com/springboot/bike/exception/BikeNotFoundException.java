package com.springboot.bike.exception;

public class BikeNotFoundException extends RuntimeException{
    private String msg;
    public BikeNotFoundException(){}
    public BikeNotFoundException(String message){
        super(message);
        this.msg= message;
    }
}
