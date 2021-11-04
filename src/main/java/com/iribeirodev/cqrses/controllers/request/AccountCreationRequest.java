package com.iribeirodev.cqrses.controllers.request;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class AccountCreationRequest {
	private final BigDecimal initialBalance;
	private final String owner;
}
