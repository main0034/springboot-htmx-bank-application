package se.main.springboothtmxbankapplication.account.events;

import se.main.springboothtmxbankapplication.account.domain.AccountId;
import se.main.springboothtmxbankapplication.account.domain.Balance;
import se.main.springboothtmxbankapplication.primitives.CustomerId;

public record AccountCreatedEvent(
        AccountId accountId,
        CustomerId customerId,
        Balance balance
) {
}
