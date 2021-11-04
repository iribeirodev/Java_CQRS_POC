package com.iribeirodev.cqrses.services;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.iribeirodev.cqrses.command.CreateAccountCommand;
import com.iribeirodev.cqrses.command.CreditMoneyCommand;
import com.iribeirodev.cqrses.command.DebitMoneyCommand;
import com.iribeirodev.cqrses.controllers.request.AccountCreationRequest;
import com.iribeirodev.cqrses.controllers.request.MoneyAmountRequest;
import com.iribeirodev.cqrses.entity.BankAccount;
import lombok.AllArgsConstructor;
import static com.iribeirodev.cqrses.services.UtilityService.formatUUID;

@Service
@AllArgsConstructor
public class AccountCommandService {
	private final CommandGateway commandGateway;
	
	public CompletableFuture<BankAccount> createAccount(
		AccountCreationRequest accountCreationRequest) {
		return this.commandGateway.send(new CreateAccountCommand(
			UUID.randomUUID(),
			accountCreationRequest.getInitialBalance(),
			accountCreationRequest.getOwner()
		));
	}
	
	public CompletableFuture<String> creditMoneyToAccount(
		String accountId,
		MoneyAmountRequest moneyAmountRequest) {
		return this.commandGateway.send(new CreditMoneyCommand(
			formatUUID(accountId),
			moneyAmountRequest.getAmount()
		));
	}
	
	public CompletableFuture<String> debitMoneyFromAccount(
			String accountId,
			MoneyAmountRequest moneyAmountRequest) {
		return this.commandGateway.send(new DebitMoneyCommand(
			formatUUID(accountId),
			moneyAmountRequest.getAmount()
		));
	}
}
