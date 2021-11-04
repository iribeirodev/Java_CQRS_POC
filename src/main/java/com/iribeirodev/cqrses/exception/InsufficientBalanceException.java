package com.iribeirodev.cqrses.exception;

import java.math.BigDecimal;
import java.util.UUID;

public class InsufficientBalanceException extends Throwable {
	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException(UUID accountId, BigDecimal debitAmount) {
		super("Saldo insuficiente, não foi possivel debitar valor de "+ debitAmount 
				+ " da conta # " + accountId.toString() );
	}
}
