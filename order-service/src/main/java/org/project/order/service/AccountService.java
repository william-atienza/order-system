package org.project.order.service;

import org.project.order.entity.Account;
import org.project.order.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    final AccountRepository repository;
    AccountService(AccountRepository repository){
        this.repository = repository;
    }

    public Optional<Account> getAccount(String id){
        return repository.findById(id);
    }

}
