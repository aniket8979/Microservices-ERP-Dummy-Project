package com.school.LoginService.Exception;

public class BadRequestException  extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }


}
