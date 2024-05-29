package se.main.springboothtmxbankapplication.domain.transaction.service;

import org.springframework.stereotype.Service;
import se.main.springboothtmxbankapplication.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.domain.transaction.port.TransactionRepository;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.TransactionType;
import se.main.springboothtmxbankapplication.primitive.AccountId;
import se.main.springboothtmxbankapplication.primitive.Amount;

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
