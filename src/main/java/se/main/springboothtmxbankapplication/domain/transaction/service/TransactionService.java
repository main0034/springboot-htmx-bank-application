package se.main.springboothtmxbankapplication.domain.transaction.service;

import org.springframework.stereotype.Service;
import se.main.springboothtmxbankapplication.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.domain.transaction.port.TransactionRepository;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.Timestamp;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.TransactionId;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.TransactionType;
import se.main.springboothtmxbankapplication.primitive.AccountId;
import se.main.springboothtmxbankapplication.primitive.Amount;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void createNew(AccountId accountId, TransactionType type, Amount amount) {
        Transaction transaction = new Transaction(
                TransactionId.from(UUID.randomUUID()),
                accountId,
                type,
                amount,
                Timestamp.now(),
                null
        );
        transactionRepository.save(transaction);
    }

    public List<Transaction> getByAccountIdOrderedByTimestampDescending(AccountId accountId) {
        return transactionRepository.findByAccountIdOrderedByTimestampDescending(accountId);
    }
}
