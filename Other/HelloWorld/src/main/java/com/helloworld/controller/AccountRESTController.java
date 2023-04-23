package com.helloworld.controller;

import com.helloworld.model.Account;
import com.helloworld.model.Student;
import com.helloworld.respository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountRESTController {
    private AccountRepository accountRepository;

    @Autowired
    public AccountRESTController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Account findAccount(@PathVariable("id") long id) {
        return accountRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        accountRepository.save(account);
        return new ResponseEntity<Account>(account, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Account> deleteAll() {
        accountRepository.deleteAll();
        return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable("id") long id) {
        Account account = accountRepository.findById(id);
        if (account == null) {
            System.out.println("Team not found");
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }
        accountRepository.deleteById(id);
        return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Account> updateAll(@RequestBody List<Account> accounts) {
        accountRepository.deleteAll();
        accountRepository.saveAll(accounts);
        return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
    }
/*
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Account> updatePartOfAccount(@RequestBody Map<String, Object> updates, @PathVariable("id") long id) {
        Account account = accountRepository.findById(id);
        if (account == null) {
            System.out.println("Account not found.");
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }
        partialUpdate(account, updates);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void partialUpdate(Account account, Map<String, Object> updates) {
        if (updates.containsKey("accountName")) {
            account.setAccountName((String) updates.get("accountName"));
        }
        if (updates.containsKey("student")) {
            account.setStudent((Student) updates.get("student"));
        }
        accountRepository.save(account);
    }

 */
}
