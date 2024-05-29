package se.main.springboothtmxbankapplication.core.domain.transaction.port;

import se.main.springboothtmxbankapplication.core.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.core.primitive.AccountId;

import java.util.List;

public interface TransactionRepository {

    void save(Transaction transaction);

    // TODO Should be paginated
    List<Transaction> findByAccountIdOrderedByTimestampDescending(AccountId accountId);

    // TODO: More queries could be supported depening on the use case e.g.
    // List<Transaction> findByAccountIdAndDateIntervalAccountId accountId, DateInterval);
}
