package com.iribeirodev.cqrses.projection;

import java.math.BigDecimal;
import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.iribeirodev.cqrses.entity.BankAccount;
import com.iribeirodev.cqrses.event.AccountCreatedEvent;
import com.iribeirodev.cqrses.event.MoneyCreditedEvent;
import com.iribeirodev.cqrses.event.MoneyDebitedEvent;
import com.iribeirodev.cqrses.exception.AccountNotFoundException;
import com.iribeirodev.cqrses.query.FindBankAccountQuery;
import com.iribeirodev.cqrses.repository.BankAccountRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Class that will match the DB operations for every received event.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BankAccountProjection {
	private final BankAccountRepository repository;
	
	@EventHandler
	public void on(AccountCreatedEvent event) {
		log.debug("Handling a Bank Account creation command {}", event.getId());
		BankAccount bankAccount = new BankAccount(
			event.getId(),
			event.getOwner(),
			event.getInitialBalance()
		);
		this.repository.save(bankAccount);
	}
	
	@EventHandler
	public void on(MoneyCreditedEvent event) throws AccountNotFoundException {
		log.debug("Handling a Bank Account credit++ command {}", event.getId());
		Optional<BankAccount> optBankAccount = repository.findById(event.getId());
		if (optBankAccount.isPresent()) {
			BankAccount bankAccount = optBankAccount.get();
			BigDecimal value = bankAccount.getBalance().add(event.getCreditAmount());
			bankAccount.setBalance(value);
			repository.save(bankAccount);
		} else {
			throw new AccountNotFoundException(event.getId());
		}
	}
	
	@EventHandler
	public void on(MoneyDebitedEvent event) throws AccountNotFoundException {
		log.debug("Handling a Bank Account debit-- command {}", event.getId());
		Optional<BankAccount> optBankAccount = repository.findById(event.getId());
		if (optBankAccount.isPresent()) {
			BankAccount bankAccount = optBankAccount.get();
			BigDecimal value = bankAccount.getBalance().subtract(event.getDebitAmount());
			bankAccount.setBalance(value);
			repository.save(bankAccount);
		} else {
			throw new AccountNotFoundException(event.getId());		
		}
	}
	
	@QueryHandler
	public BankAccount handle(FindBankAccountQuery query) {
		log.debug("Handling FindBankAccountQuery query: {}", query);
		return repository.findById(query.getAccountId()).orElse(null);
	}

}
