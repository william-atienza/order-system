package org.project.order.controller;

import org.project.order.dto.AccountRecord;
import org.project.order.entity.Account;
import org.project.order.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    final AccountRepository repository;

    AccountController(AccountRepository repository){
        this.repository = repository;
    }

    //Supposed to be with user details.
    @PostMapping("/accounts")
    public ResponseEntity<AccountRecord> create(){
        Account account = repository.save(new Account());
        AccountRecord accountRecord = new AccountRecord(account.getId(), account.getCreatedOn());
        return ResponseEntity.ok(accountRecord);
    }
}
