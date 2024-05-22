package se.main.springboothtmxbankapplication.domain.transaction.port;

import se.main.springboothtmxbankapplication.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.primitive.AccountId;

import java.util.List;

public interface TransactionRepository {

    void save(Transaction transaction);

    List<Transaction> findByAccountIdOrderedByTimestampDescending(AccountId accountId);

    // TODO: More queries could be supported depening on the use case e.g.
    // List<Transaction> findByAccountIdAndDateIntervalAccountId accountId, DateInterval);
}
