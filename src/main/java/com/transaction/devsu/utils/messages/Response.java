package com.transaction.devsu.utils.messages;

import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

public class Response {

    public static final String CLIENT_EXISTS = "CLIENT ALREADY REGISTERED WITH PROVIDED CEDULA";
    public static final String CLIENT_NOT_FOUND ="CLIENT NOT REGISTERED";

    /*200*/
    public static final HttpStatus HTTP_STATUS_OK = HttpStatus.OK;
    /*201*/
    public static final HttpStatus HTTP_STATUS_CREATED = HttpStatus.CREATED;
    /*400*/
    public static final HttpStatus HTTP_STATUS_BAD_REQUEST = HttpStatus.BAD_REQUEST;
    /*500*/
    public static final HttpStatus HTTP_STATUS_INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;


    public static final BigDecimal


}
