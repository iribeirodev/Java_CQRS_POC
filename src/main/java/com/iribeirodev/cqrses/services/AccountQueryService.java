package com.iribeirodev.cqrses.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import com.iribeirodev.cqrses.entity.BankAccount;
import com.iribeirodev.cqrses.query.FindBankAccountQuery;
import lombok.AllArgsConstructor;
import static com.iribeirodev.cqrses.services.UtilityService.formatUUID;

@Service
@AllArgsConstructor
public class AccountQueryService {
	private final QueryGateway queryGateway;
	private final EventStore eventStore;
	
	public CompletableFuture<BankAccount> findById(String accountId) {
		return this.queryGateway.query(
				new FindBankAccountQuery(formatUUID(accountId)),
				ResponseTypes.instanceOf(BankAccount.class));
	}
	
	public List<Object> listEventsForAccount(String accountId) {
		return this.eventStore
				.readEvents(formatUUID(accountId).toString())
				.asStream()
				.map(Message::getPayload)
				.collect(Collectors.toList());
	}
}
