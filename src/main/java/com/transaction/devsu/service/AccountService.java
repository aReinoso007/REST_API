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

import java.math.BigDecimal;
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
            return accountMapper.accountListToAccountDTOList(accounts.orElseGet(ArrayList::new));
        }catch (Exception e){
            throw new CustomException(e.getMessage(), e.getCause());
        }
    }

    public AccountDTO addAccount(AccountDTO accountDTO){
        try{
            Optional<Client> client = clienteRepository.findByIdentification(accountDTO.getClienteCedula());
            if(!client.isPresent()) throw new CustomException(Response.CLIENT_NOT_FOUND);
            Account account = accountMapper.accountDTOToAccount(accountDTO);
            account.setClient(client.get());
            return accountMapper.accontToAccountDTO(accountRepository.save(account));
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
            beanUtilsBean.copyProperties(account, accountMapper.accountDTOToAccount(accountDTO));
            return accountMapper.accontToAccountDTO(accountRepository.save(account));

        }catch (Exception e){
            log.error("Error updating account at AccountService ", e.getMessage());
            throw new CustomException(e.getMessage(), e.getCause());
        }
    }

    public AccountDTO getByAccountNumber(String accountNumber){
        try{
            Account account = accountRepository.findAccountByAccountNumber(accountNumber);
            if(account == null) throw new CustomException(Response.RESOURCE_NOT_FOUND);
            return accountMapper.accontToAccountDTO(account);
        }catch (Exception e){
            throw new CustomException(e.getMessage(), e.getCause());
        }
    }

    public AccountDTO getAccountById(Long id){
        try{
            Optional<Account> account = accountRepository.findById(id);
            if(account.isEmpty()) throw new CustomException(Response.RESOURCE_NOT_FOUND);
            return accountMapper.accontToAccountDTO(account.get());
        }catch (Exception e){
            throw new CustomException(e.getMessage(), e.getCause());
        }
    }

    public Boolean deleteAccountById(Long id){
        try{
            Optional<Account> account = accountRepository.findById(id);
            log.info("account "+account.get().getAccountNumber());
            if(accountRepository.findById(id).isEmpty()) return false;
            clienteRepository.deleteById(id);
            return true;

        }catch (Exception e){
            log.error("Unexpected exception at deleteAccountById "+e);
            throw new CustomException(e.getMessage(), e.getCause());
        }
    }

    public Boolean deleteAcccountByAccountNumber(String accountNumber){
        try{
            if(accountRepository.findByAccountNumber(accountNumber).isEmpty()) return false;
            accountRepository.deleteAccountByAccountNumber(accountNumber);
            return true;
        }catch (Exception e){
            log.error("Error at deletingAccountByAccountNumber "+e);
            throw new CustomException(e.getMessage(), e.getCause());
        }
    }

    public void setCurrentAvailableBalanceToAccount(Account account, BigDecimal availableBalance){
        account.setInitialBalance(availableBalance);
        log.info("setting new balanace to account");
        accountRepository.save(account);
    }

}
