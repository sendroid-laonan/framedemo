package com.sendroids.framedemo.exception;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@Component
public class ExceptionAdvice {


    @ExceptionHandler({RuntimeException.class})
    public String handlerRuntimeException(Exception e){
        e.printStackTrace();
        return "error";
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public String handlerEntityNotFoundException(Exception e){
        e.printStackTrace();
        return "nullpointer";
    }
}
