package com.transaction.devsu.utils.messages;

import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

public class Response {

    /* GENERAL RESPONSES*/
    public static final String CLIENT_EXISTS = "CLIENT ALREADY REGISTERED WITH PROVIDED CEDULA";
    public static final String CLIENT_NOT_FOUND ="CLIENT NOT FOUND IN RECORDS";
    public static final String SUCCESS = "TRANSACTION COMPLETED SUCCESSFULLY";
    public static final String RESOURCE_NOT_FOUND="RESOURCE NOT FOUND";

    /*200*/
    public static final HttpStatus HTTP_STATUS_OK = HttpStatus.OK;
    /*201*/
    public static final HttpStatus HTTP_STATUS_CREATED = HttpStatus.CREATED;
    /*400*/
    public static final HttpStatus HTTP_STATUS_BAD_REQUEST = HttpStatus.BAD_REQUEST;
    /*404*/
    public static final HttpStatus HTTP_STATUS_NOT_FOUND = HttpStatus.NOT_FOUND;
    /*500*/
    public static final HttpStatus HTTP_STATUS_INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;

    /* RELATED TO TRANSACTION */
    public static final BigDecimal MAX_DAILY_DEBIT = new BigDecimal("1000.00");
    public static final String NO_FUNDS_AVAILABLE = "SALDO NO DISPONIBLE";
    public static final String DAILY_LIMIT_EXCEEDED = "CUPO DIARIO EXCEDIDO";
    public static final String INVALID_TRANSACTION_DEBIT = "DEBITO DEBE SER DE VALORES NEGATIVOS";
    public static final String INVALID_TRANSACTION_DEPOSIT ="DEPOSITO DEBE SER DE VALORES POSITIVOS";
    public static final Boolean TRANSACTION_OK = true;

    /*TRANSACTION MESSAGES */
    public static final String KEY_MESSAGE_TRANSACTION = "MESSAGE_TRANSACTION";
    public static final String KEY_STATUS_TRANSACTION = "STATUS_TRANSACTION";
    public static final String KEY_DIFFERENCE_TRANSACTION = "DIFFERENCE_TRANSACTION";

}
