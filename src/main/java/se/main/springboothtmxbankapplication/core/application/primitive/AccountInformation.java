package se.main.springboothtmxbankapplication.core.application.primitive;

import se.main.springboothtmxbankapplication.core.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.core.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.core.primitive.AccountId;

import java.util.List;

public record AccountInformation(
        AccountId accountId,
        Balance balance,
        List<Transaction> transactions
) {
}
