package com.iribeirodev.cqrses.services;

import java.util.UUID;

public class UtilityService {
	public static UUID formatUUID(String accountId) {
		accountId = accountId.replace("-", "");
		
        return UUID.fromString(String.format(
                accountId.substring(0, 8) + "-" +
                        accountId.substring(8, 12) + "-" +
                        accountId.substring(12, 16) + "-" +
                        accountId.substring(16, 20) + "-" +
                        accountId.substring(20, 32)));		
	}
}
