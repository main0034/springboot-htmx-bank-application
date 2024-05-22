package se.main.springboothtmxbankapplication;

import se.main.springboothtmxbankapplication.adapter.hibernate.transaction.entity.TransactionEntity;
import se.main.springboothtmxbankapplication.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.Description;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.Timestamp;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.TransactionId;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.TransactionType;
import se.main.springboothtmxbankapplication.primitive.AccountId;
import se.main.springboothtmxbankapplication.primitive.Amount;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

public final class TransactionTestFactory {

    public static Transaction transaction() {
        return new Transaction(
                TransactionId.from(UUID.randomUUID()),
                AccountId.from(UUID.randomUUID()),
                TransactionType.OUTGOING,
                Amount.from(10),
                Timestamp.now(),
                Description.from("Supermarket")
        );
    }

    public static TransactionEntity entity() {
        return new TransactionEntity(
                UUID.randomUUID(),
                UUID.randomUUID(),
                0,
                BigInteger.TWO,
                LocalDateTime.now(),
                "Salary"
        );
    }

    public static TransactionEntity entity(UUID accountId, LocalDateTime timestamp) {
        return new TransactionEntity(
                UUID.randomUUID(),
                accountId,
                0,
                BigInteger.TWO,
                timestamp,
                "Salary"
        );
    }
}
