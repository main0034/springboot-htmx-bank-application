package se.main.springboothtmxbankapplication.adapter.hibernate.transaction.converter;


import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.TransactionTestFactory;
import se.main.springboothtmxbankapplication.adapter.hibernate.transaction.entity.TransactionEntity;
import se.main.springboothtmxbankapplication.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.Description;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.TransactionType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionEntityConverterTest {

    private final TransactionTypeConverter transactionTypeConverter = mock();
    private final TransactionEntityConverter converter = new TransactionEntityConverter(transactionTypeConverter);

    @Test
    void toEntity() {
        int dbId = 0;
        when(transactionTypeConverter.toDbId(any())).thenReturn(dbId);

        Transaction transaction = TransactionTestFactory.transaction();
        var entity = converter.toEntity(transaction);

        assertThat(entity.getId()).isEqualTo(transaction.id().getId());
        assertThat(entity.getAccountId()).isEqualTo(transaction.accountId().getId());
        assertThat(entity.getType()).isEqualTo(dbId);
        assertThat(entity.getAmount()).isEqualTo(transaction.amount().value());
        assertThat(entity.getTimestamp()).isEqualTo(transaction.timestamp().localDateTime());
        assertThat(entity.getDescription()).isEqualTo(transaction.description().map(Description::value).orElseThrow());

        verify(transactionTypeConverter).toDbId(transaction.type());
    }

    @Test
    void toDomain() {
        TransactionType type = TransactionType.OUTGOING;
        when(transactionTypeConverter.toTransactionType(anyInt())).thenReturn(type);

        TransactionEntity entity = TransactionTestFactory.entity();
        var domain = converter.toDomain(entity);

        assertThat(domain.id().getId()).isEqualTo(entity.getId());
        assertThat(domain.accountId().getId()).isEqualTo(entity.getAccountId());
        assertThat(domain.type()).isEqualTo(type);
        assertThat(domain.amount().value()).isEqualTo(entity.getAmount());
        assertThat(domain.timestamp().localDateTime()).isEqualTo(entity.getTimestamp());
        assertThat(domain.description().map(Description::value)).hasValue(entity.getDescription());
    }
}