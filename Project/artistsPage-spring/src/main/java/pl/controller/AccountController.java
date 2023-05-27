package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.model.Account;
import pl.repository.AccountRepository;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody Account account) {
        account.setId(id);
        accountRepository.save(account);
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }
/*
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Account> updateAccountPartially(@PathVariable("id") long id, @RequestBody Account account) {
        Account currentAccount = accountRepository.findById(id);
        if (currentAccount == null) {
            System.out.println("Team not found");
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }
        if(account.getAccountName() != null) {
            currentAccount.setAccountName(account.getAccountName());
        }
        if(account.getArtist() != null) {
            currentAccount.setArtist(account.getArtist());
        }
        accountRepository.save(currentAccount);
        return new ResponseEntity<Account>(currentAccount, HttpStatus.OK);
    }

 */
}
