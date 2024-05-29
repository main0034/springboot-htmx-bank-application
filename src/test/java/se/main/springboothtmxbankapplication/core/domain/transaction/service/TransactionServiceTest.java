package se.main.springboothtmxbankapplication.core.domain.transaction.service;


import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.core.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.core.domain.transaction.port.TransactionRepository;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.TransactionType;
import se.main.springboothtmxbankapplication.core.primitive.AccountId;
import se.main.springboothtmxbankapplication.core.primitive.Amount;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    private final TransactionRepository repository = mock();

    private final TransactionService transactionService = new TransactionService(repository);

    @Test
    void create_calls_repository_with_new_transaction() {
        AccountId accountId = AccountId.from(UUID.randomUUID());
        TransactionType transactionType = TransactionType.INCOMING;
        Amount amount = Amount.from(BigInteger.TWO);
        transactionService.create(accountId, transactionType, amount);

        verify(repository).save(assertArg(transaction -> {
            assertThat(transaction.accountId()).isEqualTo(accountId);
            assertThat(transaction.type()).isEqualTo(transactionType);
            assertThat(transaction.amount()).isEqualTo(amount);
            assertThat(transaction.timestamp().localDateTime()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
            assertThat(transaction.description()).isEmpty();
        }));
    }

    @Test
    void getByAccountIdOrderedByTimestampDescending_returns_response_from_respository() {
        List<Transaction> transactions = mock();
        when(repository.findByAccountIdOrderedByTimestampDescending(any())).thenReturn(transactions);

        AccountId accountId = AccountId.from(UUID.randomUUID());
        List<Transaction> result = transactionService.getByAccountIdOrderedByTimestampDescending(accountId);

        assertThat(result).isEqualTo(transactions);
        verify(repository).findByAccountIdOrderedByTimestampDescending(accountId);
    }
}