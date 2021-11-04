package com.iribeirodev.cqrses.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iribeirodev.cqrses.controllers.request.AccountCreationRequest;
import com.iribeirodev.cqrses.controllers.request.MoneyAmountRequest;
import com.iribeirodev.cqrses.entity.BankAccount;
import com.iribeirodev.cqrses.services.AccountCommandService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/accounts")
@Api(value = "Bank Account Commands")
@AllArgsConstructor
public class AccountCommandController {
	private final AccountCommandService accountCommandService;
	
	@PostMapping
	@ResponseStatus(value = CREATED)
	public CompletableFuture<BankAccount> createAccount(
		@RequestBody AccountCreationRequest accountCreationRequest) {
		return accountCommandService.createAccount(accountCreationRequest);
	}
	
	@PutMapping(value = "/credit/{accountId}")
	public CompletableFuture<String> creditMoneyToAccount(
		@PathVariable(value="accountId") String accountId,
		@RequestBody MoneyAmountRequest moneyAmountRequest) {
		return accountCommandService.creditMoneyToAccount(accountId, moneyAmountRequest);
	}
	
	@PutMapping(value = "/debit/{accountId}")
	public CompletableFuture<String> debitMoneyFromAccount(
		@PathVariable(value="accountId") String accountId,
		@RequestBody MoneyAmountRequest moneyAmountRequest) {
		return accountCommandService.debitMoneyFromAccount(accountId, moneyAmountRequest);
	}
	
	
}
