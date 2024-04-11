package org.example.controller;

import org.example.module.entity.Account;
import org.example.module.dto.AccountUpdateRequest;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/accounts")
@Api(tags = "Account API", description = "Operations related to accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    @ApiOperation(value = "Create a new account", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Account created successfully"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/account")
    public ResponseEntity<Account> getAccountDetails(@RequestParam("id") Long accountId) {
        Account account = accountService.getAccountById(accountId);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateAccountStatus(@RequestBody AccountUpdateRequest request) {
        boolean updated = accountService.updateAccountStatus(request.getAccountId(), request.getStatus());
        if (updated) {
            return ResponseEntity.ok("Account status updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update account status");
        }
    }

}
