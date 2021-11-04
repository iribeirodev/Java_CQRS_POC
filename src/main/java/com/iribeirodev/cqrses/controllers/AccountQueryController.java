package com.iribeirodev.cqrses.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iribeirodev.cqrses.entity.BankAccount;
import com.iribeirodev.cqrses.services.AccountQueryService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/accounts")
@Api(value = "Bank Account Queries")
@AllArgsConstructor
public class AccountQueryController {
	private final AccountQueryService accountQueryService;
	
	@GetMapping("/{accountId}")
	public CompletableFuture<BankAccount> findById(
		@PathVariable("accountId") String accountId) {
		return accountQueryService.findById(accountId);
	}
	
	@GetMapping("/{accountId}/events")
	public List<Object> listEventsForAccount(
			@PathVariable("accountId") String accountId) {
		return accountQueryService.listEventsForAccount(accountId);
	}
	
	
}
