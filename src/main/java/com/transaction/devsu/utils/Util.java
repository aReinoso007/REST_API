package com.transaction.devsu.utils;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Util {

    public static final String getConstraintViolationsFromException(Exception e){
        String message = "";
        try{
            if(e.getCause()!=null){
                ConstraintViolationException cons = (ConstraintViolationException) e.getCause().getCause();
                message = cons.getConstraintViolations().stream().iterator().next().getMessageTemplate();
            }else message= e.getMessage();
        }catch (Exception ex){
            log.info("exception util "+e.getCause().getCause().getMessage());
            message = e.getCause().getCause().getMessage();
        }

        return message;
    }
}
