package com.transaction.devsu.utils;

import jakarta.validation.ConstraintViolationException;

public class Util {

    public static final String getConstraintViolationsFromException(Exception e){
        String message = "";
        if(e.getCause()!=null){
            ConstraintViolationException cons = (ConstraintViolationException) e.getCause().getCause();
            message = cons.getConstraintViolations().stream().iterator().next().getMessageTemplate();
        }else message= e.getMessage();
        return message;
    }
}
