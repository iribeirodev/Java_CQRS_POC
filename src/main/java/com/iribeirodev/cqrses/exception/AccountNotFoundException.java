package com.iribeirodev.cqrses.exception;

import java.util.UUID;

public class AccountNotFoundException extends Throwable {
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(UUID id) {
		super("Conta # " + id.toString() + " não encontrada." );
	}
}
