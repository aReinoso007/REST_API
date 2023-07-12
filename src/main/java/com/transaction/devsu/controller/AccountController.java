package com.transaction.devsu.controller;

import com.transaction.devsu.dto.AccountDTO;
import com.transaction.devsu.service.AccountService;
import com.transaction.devsu.utils.ResponseHandler;
import com.transaction.devsu.utils.Util;
import com.transaction.devsu.utils.messages.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
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

    @PostMapping()
    public ResponseEntity<?> addNewAccount(@RequestBody AccountDTO accountDTO, @RequestParam String identificacion){
        try{
            return new ResponseEntity<>(accountService.addAccount(accountDTO, identificacion), Response.HTTP_STATUS_CREATED);
        }catch (Exception e){
            String message = Util.getConstraintViolationsFromException(e);
            return ResponseHandler.generateResponse(message, Response.HTTP_STATUS_BAD_REQUEST, null);
        }
    }


}
