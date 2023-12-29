package com.school.StudentService.Exception;

import jakarta.ws.rs.InternalServerErrorException;

public class InternalServerException extends InternalServerErrorException {
    public InternalServerException(String s){
        super(s);
    }

}
