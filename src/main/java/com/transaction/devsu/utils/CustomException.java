package com.transaction.devsu.utils;

public class CustomException extends Exception {
    public CustomException(String message, Throwable cause){
        super(message, cause);
    }
    public CustomException(String message){super(message);}
}
