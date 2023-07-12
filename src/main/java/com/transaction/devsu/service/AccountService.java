package com.transaction.devsu.service;

import com.transaction.devsu.dto.AccountDTO;
import com.transaction.devsu.dto.mappers.AccountMapper;
import com.transaction.devsu.entities.Account;
import com.transaction.devsu.entities.Client;
import com.transaction.devsu.repository.AccountRepository;
import com.transaction.devsu.repository.ClienteRepository;
import com.transaction.devsu.utils.BeanNullPropChecker;
import com.transaction.devsu.utils.CustomException;
import com.transaction.devsu.utils.messages.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final ClienteRepository clienteRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                           AccountMapper accountMapper,
                           ClienteRepository clienteRepository){
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.clienteRepository = clienteRepository;
    }

    public List<AccountDTO> getAllAccount(){
        try{
            Optional<List<Account>> accounts = Optional.of(accountRepository.findAll());
            return accountMapper.toAccountDTOs(accounts.orElseGet(ArrayList::new));
        }catch (Exception e){
            throw new CustomException(e.getMessage(), e.getCause());
        }
    }

    public AccountDTO addAccount(AccountDTO accountDTO, String identification){
        try{
            Optional<Client> client = clienteRepository.findByIdentification(identification);
            if(!client.isPresent()) throw new CustomException(Response.CLIENT_NOT_FOUND);
            Account account = accountMapper.toAccount(accountDTO);
            account.setClient(client.get());
            return accountMapper.toAccountDTO(accountRepository.save(account));
        }catch (Exception e){
            throw new CustomException(e.getMessage(), e.getCause());
        }
    }

    public AccountDTO updateAccount(AccountDTO accountDTO, String accountNumber){
        try{
            Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(()->
                    new CustomException(Response.RESOURCE_NOT_FOUND));

            Client client = clienteRepository.findByIdentification(account.getClient().getIdentification())
                    .orElseThrow(()-> new CustomException(Response.CLIENT_NOT_FOUND));
            BeanUtilsBean beanUtilsBean = new BeanNullPropChecker();
            beanUtilsBean.copyProperties(account, accountMapper.toAccount(accountDTO));
            return accountMapper.toAccountDTO(accountRepository.save(account));

        }catch (Exception e){
            log.error("Error updating account at AccountService ", e.getMessage());
            throw new CustomException(e.getMessage(), e.getCause());
        }
    }

    public AccountDTO getByAccountNumber(String accountNumber){
        try{
            Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
            return accountMapper.toAccountDTO(account.orElseThrow(()-> new CustomException(Response.RESOURCE_NOT_FOUND)));
        }catch (Exception e){
            throw new CustomException(e.getMessage(), e.getCause());
        }
    }

    public Boolean deleteAccountById(Long id){
        try{
            if(accountRepository.findById(id).isEmpty()) return false;
            clienteRepository.deleteById(id);
            return true;

        }catch (Exception e){
            log.error("Unexpected exception at deleteAccountById "+e);
            throw new CustomException(e.getMessage(), e.getCause());
        }
    }

}
