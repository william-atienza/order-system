package org.project.service;

import org.project.entity.Account;
import org.project.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    final AccountRepository repository;
    AccountService(AccountRepository repository){
        this.repository = repository;
    }

    public Optional<Account> getAccount(Long id){
        return repository.findById(id);
    }

}
