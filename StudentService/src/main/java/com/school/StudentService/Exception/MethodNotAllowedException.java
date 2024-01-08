package com.school.StudentService.Exception;

import org.springframework.web.HttpRequestMethodNotSupportedException;

public class MethodNotAllowedException extends HttpRequestMethodNotSupportedException {

    public MethodNotAllowedException(String msg){
        super(msg);
    }


}
