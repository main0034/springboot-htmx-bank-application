package se.main.springboothtmxbankapplication.adapter.out.hibernate.transaction.repository;


import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.TransactionTestFactory;
import se.main.springboothtmxbankapplication.adapter.out.hibernate.transaction.converter.TransactionEntityConverter;
import se.main.springboothtmxbankapplication.adapter.out.hibernate.transaction.entity.TransactionEntity;
import se.main.springboothtmxbankapplication.core.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.core.primitive.AccountId;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionRepositoryAdapterTest {

    private final TransactionJpaRepository jpaRepository = mock();
    private final TransactionEntityConverter converter = mock();
    private final TransactionRepositoryAdapter repositoryAdapter = new TransactionRepositoryAdapter(jpaRepository,
            converter);

    @Test
    void save_calls_repository_with_converted_transaction() {
        TransactionEntity entity = mock();
        when(converter.toEntity(any())).thenReturn(entity);

        Transaction transaction = TransactionTestFactory.transaction();
        repositoryAdapter.save(transaction);

        verify(jpaRepository).save(entity);
        verify(converter).toEntity(transaction);
    }

    @Test
    void findByAccountIdOrderedByTimestampDescending_calls_repository_and_converts_to_transactions() {
        TransactionEntity entity1 = mock();
        TransactionEntity entity2 = mock();
        when(jpaRepository.findAllByAccountIdOrderByTimestampDesc(any())).thenReturn(List.of(entity1, entity2));

        Transaction transaction1 = mock();
        Transaction transaction2 = mock();
        when(converter.toDomain(entity1)).thenReturn(transaction1);
        when(converter.toDomain(entity2)).thenReturn(transaction2);

        AccountId accountId = AccountId.from(UUID.randomUUID());
        List<Transaction> transactions = repositoryAdapter.findByAccountIdOrderedByTimestampDescending(accountId);

        assertThat(transactions).containsExactly(transaction1, transaction2);
        verify(jpaRepository).findAllByAccountIdOrderByTimestampDesc(accountId.getId());
        verify(converter).toDomain(entity1);
        verify(converter).toDomain(entity2);
    }
}