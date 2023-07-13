package com.transaction.devsu.utils;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static Date getTodaysDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
    }

    public static Date formatDateInputs(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(new SimpleDateFormat("dd-MM-yyyy").format(date));
    }
}
