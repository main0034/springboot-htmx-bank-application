package se.main.springboothtmxbankapplication.account.event;

import se.main.springboothtmxbankapplication.account.domain.AccountId;
import se.main.springboothtmxbankapplication.account.domain.Balance;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

public record AccountCreatedEvent(
        AccountId accountId,
        CustomerId customerId,
        Balance balance
) {
}
