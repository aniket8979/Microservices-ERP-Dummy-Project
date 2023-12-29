package com.school.StudentService.Exception;

public class BadRequestException  extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }


}
