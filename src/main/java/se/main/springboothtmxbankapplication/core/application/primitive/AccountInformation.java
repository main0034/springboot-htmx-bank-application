package se.main.springboothtmxbankapplication.application.primitive;

import se.main.springboothtmxbankapplication.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.primitive.AccountId;

import java.util.List;

public record AccountInformation(
        AccountId accountId,
        Balance balance,
        List<Transaction> transactions
) {
}
