package com.transaction.devsu.controller;

import com.transaction.devsu.dto.AccountDTO;
import com.transaction.devsu.service.AccountService;
import com.transaction.devsu.utils.ResponseHandler;
import com.transaction.devsu.utils.Util;
import com.transaction.devsu.utils.messages.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("")
    public List<AccountDTO> getAllAccounts(){
        return accountService.getAllAccount();
    }

    @GetMapping("/cuenta/{accountNumber}")
    public ResponseEntity<?> getByAccountNumber(@PathVariable("accountNumber") String accountNumber){
        try{
            return new ResponseEntity<>(accountService.getByAccountNumber(accountNumber), Response.HTTP_STATUS_OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), Response.HTTP_STATUS_BAD_REQUEST, null);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getByAccountById(@PathVariable("id") String id){
        try{
            return new ResponseEntity<>(accountService.getAccountById(Long.valueOf(id)), Response.HTTP_STATUS_OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), Response.HTTP_STATUS_BAD_REQUEST, null);
        }
    }

    @PostMapping()
    public ResponseEntity<?> addNewAccount(@RequestBody AccountDTO accountDTO){
        try{
            return new ResponseEntity<>(accountService.addAccount(accountDTO), Response.HTTP_STATUS_CREATED);
        }catch (Exception e){
            String message = Util.getConstraintViolationsFromException(e);
            return ResponseHandler.generateResponse(message, Response.HTTP_STATUS_BAD_REQUEST, null);
        }
    }

    @PutMapping()
    public ResponseEntity<?> updateAccount(@RequestBody AccountDTO accountDTO, @RequestParam String accountNumber){
        try{
            return new ResponseEntity<>(accountService.updateAccount(accountDTO, accountNumber), Response.HTTP_STATUS_CREATED);
        }catch (Exception e){
            String message = Util.getConstraintViolationsFromException(e);
            return ResponseHandler.generateResponse(message, Response.HTTP_STATUS_BAD_REQUEST, null);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteAccountById(@PathVariable("id") String id){
        try{
            if(accountService.deleteAccountById(Long.valueOf(id))){
                return new ResponseEntity<>(Response.SUCCESS, HttpStatus.OK);
            }else return ResponseHandler.generateResponse(Response.RESOURCE_NOT_FOUND, Response.HTTP_STATUS_NOT_FOUND, null);
        }catch (Exception e){
            log.error("delete exception "+e.getCause().getCause().getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), Response.HTTP_STATUS_BAD_REQUEST, null);
        }
    }


}
