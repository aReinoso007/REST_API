package com.transaction.devsu.controller;

import com.transaction.devsu.dto.TransactionDTO;
import com.transaction.devsu.service.TransactionService;
import com.transaction.devsu.utils.ResponseHandler;
import com.transaction.devsu.utils.Util;
import com.transaction.devsu.utils.messages.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("")
    public List<TransactionDTO> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @GetMapping(path = "/cuenta/{accountNumber}")
    public List<TransactionDTO> getByAccountNumber(@PathVariable("accountNumber") String accountNumber){
        return transactionService.getTransactionsByAccountNumber(accountNumber);
    }

    @PostMapping()
    public ResponseEntity<?> makeTransaction(@RequestBody TransactionDTO transactionDTO, @PathVariable("numeroCuenta") String numero){
        try{
            return new ResponseEntity<>(transactionService.makeTransaction(transactionDTO), Response.HTTP_STATUS_OK);
        }catch (Exception e){
            String message = Util.getConstraintViolationsFromException(e);
            return ResponseHandler.generateResponse(message, Response.HTTP_STATUS_BAD_REQUEST, null);
        }
    }


}
