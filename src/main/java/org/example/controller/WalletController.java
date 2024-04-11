package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.example.module.entity.Wallet;
import org.example.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
@Api(tags = "assets API", description = "Operations related to wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/create")
    @ApiOperation(value = "Create a new Wallet", response = Wallet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Wallet created successfully"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet) {
        Wallet createdWallet = walletService.createWallet(wallet);
        return new ResponseEntity<>(createdWallet, HttpStatus.CREATED);
    }

    @GetMapping("/query/by-account/")
    @ApiOperation(value = "query wallet info by the account", response = Wallet.class)
    public ResponseEntity<List<Wallet>> getWalletsByAccountId(@RequestParam("accountId") Long accountId) {
        List<Wallet> wallets = walletService.getWalletsByAccountId(accountId);
        if (wallets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }


    // Other controller methods can be added here

}
