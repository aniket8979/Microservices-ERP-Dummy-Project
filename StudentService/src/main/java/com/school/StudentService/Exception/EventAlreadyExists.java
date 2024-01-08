package com.school.StudentService.Exception;

public class EventAlreadyExists extends RuntimeException{
    public EventAlreadyExists(String msg){
        super(msg);
    }

}
