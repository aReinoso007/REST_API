package com.transaction.devsu.utils;

public class CustomException extends Exception {
    CustomException(String message, Throwable cause){
        super(message, cause);
    }
}
