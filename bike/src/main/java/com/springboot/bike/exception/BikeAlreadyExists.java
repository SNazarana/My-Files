package com.springboot.bike.exception;

public class BikeAlreadyExists extends RuntimeException{
    private String msg;
    public BikeAlreadyExists(){}
    public BikeAlreadyExists(String message){
        super(message);
        this.msg=message;
    }
}
