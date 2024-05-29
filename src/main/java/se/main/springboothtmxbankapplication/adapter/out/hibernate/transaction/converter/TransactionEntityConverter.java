package se.main.springboothtmxbankapplication.adapter.out.hibernate.transaction.converter;

import org.springframework.stereotype.Component;
import se.main.springboothtmxbankapplication.adapter.out.hibernate.ConverterHelper;
import se.main.springboothtmxbankapplication.adapter.out.hibernate.transaction.entity.TransactionEntity;
import se.main.springboothtmxbankapplication.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.Description;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.Timestamp;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.TransactionId;
import se.main.springboothtmxbankapplication.primitive.AccountId;
import se.main.springboothtmxbankapplication.primitive.Amount;

@Component
public class TransactionEntityConverter {

    private final TransactionTypeConverter transactionTypeConverter;

    public TransactionEntityConverter(TransactionTypeConverter transactionTypeConverter) {
        this.transactionTypeConverter = transactionTypeConverter;
    }

    public TransactionEntity toEntity(Transaction transaction) {
        return new TransactionEntity(
                transaction.id().getId(),
                transaction.accountId().getId(),
                transactionTypeConverter.toDbId(transaction.type()),
                transaction.amount().value(),
                transaction.timestamp().localDateTime(),
                transaction.description().map(Description::value).orElse(null)
        );
    }

    public Transaction toDomain(TransactionEntity entity) {
        return new Transaction(
                TransactionId.from(entity.getId()),
                AccountId.from(entity.getAccountId()),
                transactionTypeConverter.toTransactionType(entity.getType()),
                Amount.from(entity.getAmount()),
                Timestamp.from(entity.getTimestamp()),
                ConverterHelper.createIfExists(entity.getDescription(), Description::from)
        );
    }
}
