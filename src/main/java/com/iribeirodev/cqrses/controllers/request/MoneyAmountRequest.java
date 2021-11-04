package com.iribeirodev.cqrses.controllers.request;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoneyAmountRequest {
	private BigDecimal amount;
}
