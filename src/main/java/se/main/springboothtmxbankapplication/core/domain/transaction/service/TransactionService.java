package se.main.springboothtmxbankapplication.core.domain.transaction.service;

import org.springframework.stereotype.Service;
import se.main.springboothtmxbankapplication.core.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.core.domain.transaction.port.TransactionRepository;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.TransactionType;
import se.main.springboothtmxbankapplication.core.primitive.AccountId;
import se.main.springboothtmxbankapplication.core.primitive.Amount;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void create(AccountId accountId, TransactionType type, Amount amount) {
        Transaction transaction = Transaction.createNew(accountId, type, amount);
        transactionRepository.save(transaction);
    }

    // TODO Should be paginated
    public List<Transaction> getByAccountIdOrderedByTimestampDescending(AccountId accountId) {
        return transactionRepository.findByAccountIdOrderedByTimestampDescending(accountId);
    }
}
